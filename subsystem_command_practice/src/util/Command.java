package util;

public abstract class Command {
    public abstract void execute();
    
    public abstract void initialize();
    
    public abstract void end(Boolean interrupted);
    
    public abstract Boolean isFinished();
}
