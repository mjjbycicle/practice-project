package ShooterSubsystem;

import DummyMotors.Motor;

/**
 * An incomplete class for a shooter.
 * The shooter will have a wheel to launch balls and a hood to adjust the angle.
 * The wheel will be powered by 1 motor, and the hood will be powered by 1 motor.
 * Use the existing {@link DummyMotors.Motor} class to represent motors.
 */
public class ShooterSubsystem {
    private final Motor speedMotor;
    private final Motor hoodMotor;
    private double currentSpeed;
    private double currentPosition;
    private ShooterConstants.ShooterState currentState;
    
    public ShooterSubsystem() {
        speedMotor = new Motor(ShooterConstants.IDs.SpeedMotorID);
        hoodMotor = new Motor(ShooterConstants.IDs.HoodMotorID);
        currentSpeed = ShooterConstants.ShooterState.IDLE.speed;
        currentPosition = ShooterConstants.ShooterState.IDLE.position;
        currentState = ShooterConstants.ShooterState.IDLE;
        speedMotor.setSpeed(currentSpeed);
        hoodMotor.setSpeed(currentPosition);
    }
    
    public void setState(ShooterConstants.ShooterState newState) {
        currentState = newState;
        currentPosition = newState.position;
        currentSpeed = newState.speed;
    }
    
    public void setSpeed(double speed) {
        currentSpeed = speed;
        speedMotor.setSpeed(speed);
    }
    
    public void setPosition(double position) {
        currentPosition = position;
        hoodMotor.setPosition(position);
    }
}
