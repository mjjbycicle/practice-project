package DummyMotors;

/**
 * A fake class that represents the interface of a real motor fairly accurately
 */
public class Motor {
    private final int motorID;
    private double speed, position;

    /**
     * @param motorID ID of the motor. This helps to uniquely identify the specific motor and prevent sending
     *                conflicting commands to a single motor.
     */
    public Motor(int motorID) {
        this.motorID = motorID;
    }

    /**
     * @param speed Speed to set the motor to. We can assume that the motor instantly goes up to the requested speed.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @param position Position for the motor to track. We can assume that the motor instantly goes to the requested
     *                position.
     */
    public void setPosition(double position) {
        this.position = position;
        this.speed = 0;
    }

    public int getMotorID() {
        return motorID;
    }

    public double getSpeed() {
        return speed;
    }
    
    public double getPosition() {
        return position;
    }
}
