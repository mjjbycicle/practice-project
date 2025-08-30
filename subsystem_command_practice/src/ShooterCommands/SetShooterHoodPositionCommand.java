package ShooterCommands;

import ShooterSubsystem.ShooterSubsystem;
import util.Command;

public class SetShooterHoodPositionCommand extends Command {
    private final ShooterSubsystem shooterSubsystem;
    private final double hoodPosition;
    
    public SetShooterHoodPositionCommand(ShooterSubsystem shooterSubsystem, double hoodPosition) {
        this.shooterSubsystem = shooterSubsystem;
        this.hoodPosition = hoodPosition;
    }
    
    @Override
    public void execute() {
        
    }

    @Override
    public void initialize() {
        shooterSubsystem.setPosition(hoodPosition);
    }

    @Override
    public void end(Boolean interrupted) {

    }

    @Override
    public Boolean isFinished() {
        return true;
    }
}
