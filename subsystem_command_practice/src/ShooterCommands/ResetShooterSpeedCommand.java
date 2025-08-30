package ShooterCommands;

import ShooterSubsystem.*;

public class ResetShooterSpeedCommand extends SetShooterSpeedCommand{
    public ResetShooterSpeedCommand(ShooterSubsystem shooterSubsystem) {
        super(shooterSubsystem, ShooterConstants.IdleSpeed);
    }
}
