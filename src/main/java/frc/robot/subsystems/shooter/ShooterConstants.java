package frc.robot.subsystems.shooter;

import java.util.function.DoubleSupplier;

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
        public DoubleSupplier speedSupplier;
        public boolean useTrackingSpeed = false;
        
        ShooterState(double speed, double position) {
            this.speed = speed;
            this.position = position;
        }
        
        public void withSpeeds(DoubleSupplier speedSupplier) {
            this.speedSupplier = speedSupplier;
            useTrackingSpeed = true;
        }
        
        public void cancelTrackingSpeeds() {
            this.speedSupplier = null;
            useTrackingSpeed = false;
        }
    }
}
