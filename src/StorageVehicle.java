import java.awt.Color;
import java.awt.geom.Point2D;

public class StorageVehicle implements IsVehicle, HasStorage {
    private final Vehicle parent;
    private boolean storageOpen;

    public StorageVehicle(int nrDoors, double enginePower, Color color, String modelName) {
        parent = new Vehicle(nrDoors, enginePower, color, modelName);
        storageOpen = false;
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
        parent.turnLeft(angle);
    }

    @Override
    public double getDirection() {
        return parent.getDirection();
    }

    @Override
    public Point2D.Double getPosition() {
        return parent.getPosition();
    }

    @Override
    public void openStorage() {
        storageOpen = true;
    }

    @Override
    public void closeStorage() {
        storageOpen = false;
    }
}