import java.awt.*;
import java.awt.geom.Point2D;

public class Vehicle implements IsVehicle, Movable {

    private Point2D.Double position; // the position of the car
    private double directionAngle; // the current angle of the car in space
    private double[] direction; // cos and sin of directionAngle (in that order)
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    private double speedFactor;

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.directionAngle = 0.0;
        this.direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
        this.position = new Point2D.Double(0, 0);
        setSpeedFactor(enginePower*0.01);
        stopEngine();
    }
    
    @Override
    public String getModel() {
        return modelName;
    }

    @Override
    public int getNrDoors() {
        return nrDoors;
    }

    @Override
    public double getEnginePower() {
        return enginePower;
    }

    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color clr) {
        color = clr;
    }

    @Override
    public void startEngine() {
        currentSpeed = 0.1;
    }

    @Override
    public void stopEngine() {
        currentSpeed = 0;
    }
    
    // @Override
    public double getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }

    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor * amount);
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor * amount);
    }

    private void setCurrentSpeed(double newSpeed) {
        currentSpeed = Math.clamp(newSpeed, 0, enginePower);
    }

    @Override
    public void gas(double amount) {
        if (amount < 0 || 1 < amount) {
            throw new IllegalArgumentException();
        }
        incrementSpeed(amount);
    }

    @Override
    public void brake(double amount) {
        if (amount < 0 || 1 < amount) {
            throw new IllegalArgumentException();
        }
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        position.setLocation(position.getX() + currentSpeed * direction[0],
                position.getY() + currentSpeed * direction[1]);
    }

    @Override
    public double getDirection() {
        return directionAngle;
    }

    @Override
    public void turnLeft() {
        directionAngle++;
        direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
    }

    @Override
    public void turnRight() {
        directionAngle--;
        direction = new double[] { Math.cos(directionAngle), Math.sin(directionAngle) };
    }

    @Override
    public Point2D.Double getPosition() {
        return new Point2D.Double(this.position.x, this.position.y);
    }

}
