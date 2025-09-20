package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class SetShooterStateCommand extends Command {
    private final ShooterSubsystem shooterSubsystem;
    private final ShooterConstants.ShooterState shooterState;
    
    public SetShooterStateCommand(ShooterSubsystem shooterSubsystem, ShooterConstants.ShooterState shooterState) {
        this.shooterSubsystem = shooterSubsystem;
        this.shooterState = shooterState;
    }
    
    @Override
    public void execute() {
        
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(shooterState);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
