package frc.robot.subsystems.indexer;

public class IndexerConstants {
    public static class IDs {
        public static final int IndexerID = 5;
        public static final int BeamBreakID = 10;
    }
    
    public enum IndexerState {
        IDLE(0),
        KICK(1);
        
        public final double speed;
        
        IndexerState(double speed) {
            this.speed = speed;
        }
    }
}
