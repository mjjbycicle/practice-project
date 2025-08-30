package ShooterCommands;

import ShooterSubsystem.*;
import util.Command;

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
    public void end(Boolean interrupted) {

    }

    @Override
    public Boolean isFinished() {
        return true;
    }
}
