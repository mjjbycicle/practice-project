package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * An incomplete class for a shooter.
 * The shooter will have a wheel to launch balls and a hood to adjust the angle.
 * The wheel will be powered by 1 motor, and the hood will be powered by 1 motor.
 */
public class ShooterSubsystem extends SubsystemBase {
    private final TalonFX speedMotor;
    private final TalonFX hoodMotor;
    private ShooterConstants.ShooterState currentState;
    
    public ShooterSubsystem() {
        speedMotor = new TalonFX(ShooterConstants.IDs.SpeedMotorID);
        hoodMotor = new TalonFX(ShooterConstants.IDs.HoodMotorID);
        currentState = ShooterConstants.ShooterState.IDLE;
        speedMotor.set(currentState.speed);
        hoodMotor.setPosition(currentState.position);
    }
    
    public void setState(ShooterConstants.ShooterState newState) {
        currentState = newState;
        setSpeed(newState.speed);
        setPosition(newState.position);
    }
    
    public void setSpeed(double speed) {
        speedMotor.set(speed);
    }
    
    public void setPosition(double position) {
        hoodMotor.setPosition(position);
    }
    
    public Command setShooterStateCommand(ShooterConstants.ShooterState newState) {
        return this.runOnce(
                () -> {
                    this.setState(newState);
                }
        );
    }
    
    @Override
    public void periodic() {
        if (currentState.useTrackingSpeed) {
            setSpeed(currentState.speedSupplier.getAsDouble());
            SmartDashboard.putNumber("Shooter Speed", currentState.speedSupplier.getAsDouble());
        } else {
            SmartDashboard.putNumber("Shooter Speed", currentState.speed);
        }
        SmartDashboard.putNumber("Shooter Position", currentState.position);
    }
}
