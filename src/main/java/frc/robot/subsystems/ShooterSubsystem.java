package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

/**
 * An incomplete class for a shooter.
 * The shooter will have a wheel to launch balls and a hood to adjust the angle.
 * The wheel will be powered by 1 motor, and the hood will be powered by 1 motor.
 */
public class ShooterSubsystem {
    private final TalonFX speedMotor;
    private final TalonFX hoodMotor;
    private double currentSpeed;
    private double currentPosition;
    private ShooterConstants.ShooterState currentState;
    
    public ShooterSubsystem() {
        speedMotor = new TalonFX(ShooterConstants.IDs.SpeedMotorID);
        hoodMotor = new TalonFX(ShooterConstants.IDs.HoodMotorID);
        currentSpeed = ShooterConstants.ShooterState.IDLE.speed;
        currentPosition = ShooterConstants.ShooterState.IDLE.position;
        currentState = ShooterConstants.ShooterState.IDLE;
        speedMotor.set(currentSpeed);
        hoodMotor.set(currentPosition);
    }
    
    public void setState(ShooterConstants.ShooterState newState) {
        currentState = newState;
        currentPosition = newState.position;
        currentSpeed = newState.speed;
    }
    
    public void setSpeed(double speed) {
        currentSpeed = speed;
        speedMotor.set(speed);
    }
    
    public void setPosition(double position) {
        currentPosition = position;
        hoodMotor.setPosition(position);
    }
}
