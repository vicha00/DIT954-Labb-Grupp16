import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements isCar, Movable {

    protected Point2D.Double position; // the position of the car
    protected double directionAngle; // the current angle of the car in space
    protected double[] direction; // cos and sin of directionAngle (in that order)
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.directionAngle = 0.0;
        this.direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
        this.position = new Point2D.Double(0, 0);
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    private void setCurrentSpeed(double newSpeed) {
        currentSpeed = Math.clamp(newSpeed, 0, enginePower);
    }

    public void gas(double amount) {
        if (amount < 0 || 1 < amount) {
            throw new IllegalArgumentException();
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || 1 < amount) {
            throw new IllegalArgumentException();
        }
        decrementSpeed(amount);
    }

    public void move() {
        position.setLocation(position.getX() + currentSpeed * direction[0],
                position.getY() + currentSpeed * direction[1]);
    }

    public double getDirection() {
        return directionAngle;
    }

    public void turnLeft() {
        directionAngle++;
        direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
    }

    public void turnRight() {
        directionAngle--;
        direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
    }

    public Point2D.Double getPosition() {
        return new Point2D.Double(this.position.x, this.position.y);
    }

}
