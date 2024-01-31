import java.awt.*;
import java.awt.geom.Point2D;

public class Saab95 implements IsVehicle {

    private final Vehicle parent;
    private boolean turboOn;

    public Saab95() {
        parent = new Vehicle(2, 125, Color.red,"Saab95");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
        setSpeedFactor();
    }

    public void setTurboOff() {
        turboOn = false;
        setSpeedFactor();
    }

    private void setSpeedFactor() {
        double turbo = 1;
        if (turboOn) {
            turbo = 1.3;
        }
        parent.setSpeedFactor(getEnginePower() * 0.01 * turbo);
    }

    @Override
    public String getModel() {
        return parent.getModel();
    }

    @Override
    public int getNrDoors() {
        return parent.getNrDoors();
    }

    @Override
    public double getEnginePower() {
        return parent.getEnginePower();
    }

    @Override
    public double getCurrentSpeed() {
        return parent.getCurrentSpeed();
    }

    @Override
    public Color getColor() {
        return parent.getColor();
    }

    @Override
    public void setColor(Color clr) {
        parent.setColor(clr);
    }

    @Override
    public void startEngine() {
        parent.startEngine();
    }

    @Override
    public void stopEngine() {
        parent.stopEngine();
    }
    
    @Override
    public boolean isEngineOn() {
        return parent.isEngineOn();
    }

    @Override
    public void gas(double amount) {
        parent.gas(amount);
    }

    @Override
    public void brake(double amount) {
        parent.brake(amount);
    }

    @Override
    public void move() {
        parent.move();
    }

    @Override
    public void turnLeft(double angle) {
        parent.turnLeft(angle);
    }

    @Override
    public void turnRight(double angle) {
        parent.turnRight(angle);
    }

    @Override
    public double getDirection() {
        return parent.getDirection();
    }

    @Override
    public Point2D.Double getPosition() {
        return parent.getPosition();
    }

}
