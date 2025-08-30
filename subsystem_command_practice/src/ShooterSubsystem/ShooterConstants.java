package ShooterSubsystem;

public class ShooterConstants {
    public static class IDs {
        public static final int SpeedMotorID = 0;
        public static final int HoodMotorID = 1;
    }
    
    public enum ShooterState {
        IDLE(1.0 ,0.0),
        SHOOTING(2.0, 2.0),
        SPINUP(3.0, 3.0);
        
        public final double speed, position;
        
        ShooterState(double speed, double position) {
            this.speed = speed;
            this.position = position;
        }
    }
}
