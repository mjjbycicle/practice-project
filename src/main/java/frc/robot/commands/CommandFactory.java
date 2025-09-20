package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.indexer.IndexerConstants;
import frc.robot.subsystems.indexer.IndexerSubsystem;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.ShooterConstants;
import frc.robot.subsystems.shooter.ShooterSubsystem;

public class CommandFactory {
    private final ShooterSubsystem shooterSubsystem;
    private final IndexerSubsystem indexerSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    
    public CommandFactory(ShooterSubsystem shooter, IndexerSubsystem indexer, IntakeSubsystem intake) {
        this.shooterSubsystem = shooter;
        this.indexerSubsystem = indexer;
        this.intakeSubsystem = intake;
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
}
