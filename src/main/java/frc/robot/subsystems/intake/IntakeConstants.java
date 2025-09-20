package frc.robot.subsystems.intake;

public class IntakeConstants {
    public static class IDs {
        public static final int ROLLERS = 6;
        public static final int PIVOT = 7;
    }
    
    public enum IntakeState {
        IDLE(0, 0),
        INTAKING(1, 1);
        
        public final double pivotAngle, rollerSpeed;
        
        IntakeState(double pivotAngle, double rollerSpeed) {
            this.pivotAngle = pivotAngle;
            this.rollerSpeed = rollerSpeed;
        }
    }
}
