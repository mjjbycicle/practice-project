package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.indexer.IndexerConstants.*;

public class IndexerSubsystem extends SubsystemBase {
    private final TalonFX indexerMotor;
    
    private IndexerState indexerState;
    
    private final DigitalInput beamBreak;
    private final Trigger hasBallTrigger;
    
    public IndexerSubsystem() {
        indexerMotor = new TalonFX(IndexerConstants.IDs.IndexerID);
        indexerState = IndexerState.IDLE;
        indexerMotor.set(indexerState.speed);
        beamBreak = new DigitalInput(IDs.BeamBreakID);
        hasBallTrigger = new Trigger(this::hasBallRaw).debounce(0.25);
    }
    
    public void setIndexerState(IndexerState newState) {
        indexerState = newState;
        setIndexerSpeed(newState.speed);
    }
    
    public void setIndexerSpeed(double speed) {
        indexerMotor.set(speed);
    }
    
    public boolean hasBallRaw() {
        return beamBreak.get();
    }
    
    public boolean hasBall() {
        return hasBallTrigger.getAsBoolean();
    }
    
    public Trigger getHasBallTrigger() {
        return hasBallTrigger;
    }
    
    public Command setIndexerStateCommand(IndexerState newState) {
        return this.runOnce(
                () -> {
                    this.setIndexerState(newState);
                }
        );
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Indexer Speed", indexerMotor.getVelocity().getValueAsDouble());
        SmartDashboard.putBoolean("Has Ball Raw", hasBallRaw());
        SmartDashboard.putBoolean("Has Ball", hasBall());
    }
}
