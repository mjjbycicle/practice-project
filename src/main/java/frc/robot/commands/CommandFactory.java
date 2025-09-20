package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.indexer.IndexerConstants;
import frc.robot.subsystems.indexer.IndexerSubsystem;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.ShooterConstants;
import frc.robot.subsystems.shooter.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class CommandFactory {
    private final ShooterSubsystem shooterSubsystem;
    private final IndexerSubsystem indexerSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final CommandSwerveDrivetrain swerve;

    public CommandFactory(ShooterSubsystem shooter, IndexerSubsystem indexer, IntakeSubsystem intake,
                          CommandSwerveDrivetrain swerve) {
        this.shooterSubsystem = shooter;
        this.indexerSubsystem = indexer;
        this.intakeSubsystem = intake;
        this.swerve = swerve;
    }

    public Command ShootSequenceCommand() {
        return shooterSubsystem.setShooterStateCommand(ShooterConstants.ShooterState.SPINUP)
                .andThen(Commands.waitSeconds(2))
                .andThen(indexerSubsystem.setIndexerStateCommand(IndexerConstants.IndexerState.KICK))
                .andThen(shooterSubsystem.setShooterStateCommand(ShooterConstants.ShooterState.SHOOTING))
                .andThen(Commands.waitSeconds(2))
                .andThen(shooterSubsystem.setShooterStateCommand(ShooterConstants.ShooterState.IDLE))
                .andThen(indexerSubsystem.setIndexerStateCommand(IndexerConstants.IndexerState.IDLE));
    }

    public Command IntakeSequenceCommand() {
        return indexerSubsystem.setIndexerStateCommand(IndexerConstants.IndexerState.IDLE)
                .andThen(intakeSubsystem.setIntakeStateCommand(IntakeConstants.IntakeState.INTAKING))
                .andThen(Commands.waitSeconds(0.5))
                .andThen(Commands.waitUntil(indexerSubsystem.getHasBallTrigger()))
                .andThen(Commands.waitSeconds(0.5))
                .andThen(intakeSubsystem.setIntakeStateCommand(IntakeConstants.IntakeState.IDLE));
    }

    public Command driveToCommand(Pose2d targetPose) {
        return swerve.applyRequest(
                () -> {
                    Pose2d currentPose = swerve.getState().Pose;
                    Translation2d poseDifference = targetPose.getTranslation().minus(currentPose.getTranslation());
                    return new SwerveRequest.FieldCentricFacingAngle()
                            .withTargetDirection(targetPose.getRotation())
                            .withVelocityX(-poseDifference.getX())
                            .withVelocityY(-poseDifference.getY());
                }
        );
    }

    public Command driveThenShootCommand() {
        return driveToCommand(Constants.shootPose)
                .withDeadline(
                        Commands.waitUntil(
                                () -> {
                                    Pose2d currentPose = swerve.getState().Pose;
                                    Translation2d poseDifference = Constants.shootPose.getTranslation().minus(currentPose.getTranslation());
                                    return poseDifference.getNorm() <= 0.25;
                                }
                        ).andThen(
                                ShootSequenceCommand()
                        )
                );
    }

    public Command driveFacingCenterCommand(DoubleSupplier x, DoubleSupplier y) {
        return swerve.applyRequest(
                () -> {
                    Pose2d currentPose = swerve.getState().Pose;
                    Pose2d centerPose = Constants.centerPose;
                    Translation2d poseDifference = centerPose.getTranslation().minus(currentPose.getTranslation());
                    Rotation2d targetDirection = poseDifference.getAngle();
                    double velocityX = x.getAsDouble();
                    double velocityY = y.getAsDouble();
                    double tangentVelocity = velocityX * targetDirection.getSin() + velocityY * targetDirection.getCos();
                    double angularVelocity = tangentVelocity / poseDifference.getNorm();
                    SmartDashboard.putNumber("target angle", targetDirection.getDegrees());
                    SmartDashboard.putNumber("angular velocity", angularVelocity);
                    SmartDashboard.putNumber("tangent velocity", tangentVelocity);
                    SmartDashboard.putNumber("x velocity", x.getAsDouble());
                    SmartDashboard.putNumber("y velocity", y.getAsDouble());
                    SmartDashboard.putNumber("x sin", velocityX * targetDirection.getSin());
                    SmartDashboard.putNumber("y cos", velocityY * targetDirection.getCos());
                    return new SwerveRequest.FieldCentricFacingAngle()
                            .withVelocityX(x.getAsDouble())
                            .withVelocityY(y.getAsDouble())
                            .withTargetDirection(targetDirection.plus(Rotation2d.k180deg).plus(Rotation2d.fromRadians(angularVelocity / 3)))
                            .withHeadingPID(10, 0, 0);
                }
        );
    }
}
