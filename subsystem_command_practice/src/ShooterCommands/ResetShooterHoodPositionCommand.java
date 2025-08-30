package ShooterCommands;

import ShooterSubsystem.*;

public class ResetShooterHoodPositionCommand extends SetShooterHoodPositionCommand {
    public ResetShooterHoodPositionCommand(ShooterSubsystem shooterSubsystem) {
        super(shooterSubsystem, ShooterConstants.IdlePosition);
    }
}
