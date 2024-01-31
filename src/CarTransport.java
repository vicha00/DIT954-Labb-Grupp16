import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Stack;

public class CarTransport implements IsVehicle, HasStorage<Vehicle> {
    // TODO : change vehicle to car
    private final StorageVehicle<Vehicle> parent;

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        Stack<Vehicle> storage = new Stack<>();
        parent = new StorageVehicle<>(nrDoors, enginePower, color, modelName, storage);
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

    @Override
    public void openStorage() {
        parent.openStorage();
    }

    @Override
    public void closeStorage() {
        parent.closeStorage();
    }

    @Override
    public boolean isStorageOpen() {
        return parent.isStorageOpen();
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
}
