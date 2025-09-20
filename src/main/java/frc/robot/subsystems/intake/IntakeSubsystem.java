package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.intake.IntakeConstants.*;

public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX pivotMotor;
    private final TalonFX intakeMotor;
    
    private IntakeState intakeState = IntakeState.IDLE;
    
    public IntakeSubsystem() {
        pivotMotor = new TalonFX(IntakeConstants.IDs.PIVOT);
        intakeMotor = new TalonFX(IntakeConstants.IDs.ROLLERS);
        pivotMotor.setPosition(intakeState.pivotAngle);
        intakeMotor.set(intakeState.rollerSpeed);
    }
    
    public void setIntakeState(IntakeState intakeState) {
        this.intakeState = intakeState;
        setPivotAngle(intakeState.pivotAngle);
        setRollerSpeed(intakeState.rollerSpeed);
    }
    
    public void setPivotAngle(double angle) {
        pivotMotor.setPosition(angle);
    }
    
    public void setRollerSpeed(double speed) {
        intakeMotor.set(speed);
    }
    
    public Command setIntakeStateCommand(IntakeState intakeState) {
        return this.runOnce(
                () -> {
                    setIntakeState(intakeState);
                }
        );
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pivot Angle", pivotMotor.getPosition().getValueAsDouble());
        SmartDashboard.putNumber("Roller Speed", intakeMotor.getVelocity().getValueAsDouble());
    }
}
