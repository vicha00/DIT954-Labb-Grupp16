import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 implements IsVehicle, Movable {

    private final static double TRIMFACTOR = 1.25;

    private final Vehicle car;

    public Volvo240() {
        car = new Vehicle(4, 100, Color.black, "Volvo240");
        car.setSpeedFactor(getEnginePower() * 0.01 * TRIMFACTOR);
    }


    @Override
    public String getModel() {
        return car.getModel();
    }

    @Override
    public int getNrDoors() {
        return car.getNrDoors();
    }

    @Override
    public double getEnginePower() {
        return car.getEnginePower();
    }

    @Override
    public double getCurrentSpeed() {
        return car.getCurrentSpeed();
    }

    @Override
    public Color getColor() {
        return car.getColor();
    }

    @Override
    public void setColor(Color clr) {
        car.setColor(clr);
    }

    @Override
    public void startEngine() {
        car.startEngine();
    }

    @Override
    public void stopEngine() {
        car.stopEngine();
    }

    @Override
    public void gas(double amount) {
        car.gas(amount);
    }

    @Override
    public void brake(double amount) {
        car.brake(amount);
    }

    @Override
    public void move() {
        car.move();
    }

    @Override
    public void turnLeft() {
        car.turnLeft();
    }

    @Override
    public void turnRight() {
        car.turnRight();
    }

    @Override
    public double getDirection() {
        return car.getDirection();
    }

    @Override
    public Point2D.Double getPosition() {
        return car.getPosition();
    }

}
