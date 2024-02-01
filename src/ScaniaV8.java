import java.awt.*;
import java.awt.geom.Point2D;

public class ScaniaV8<T> implements IsVehicle, Tippable<T> {

    public static final double TOURQE_FACTOR = 0.35;
    private final NonFixedTrailerTruck<T> parent;

    public ScaniaV8() {

        parent = new NonFixedTrailerTruck<T>(2, 200, Color.white, "ScaniaV8");
        parent.setSpeedFactor(getEnginePower() * TOURQE_FACTOR * 0.01);
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
    public double getStorageAngle() {
        return parent.getStorageAngle();
    }

    @Override
    public void lowerStorage(double angle) {
        parent.lowerStorage(angle);
    }

    @Override
    public void raiseStorage(double angle) {
        parent.raiseStorage(angle);
    }

    @Override
    public void storeThing(T toStore) {
        parent.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return parent.removeThing();
    }

    @Override
    public int countThings() {
        return parent.countThings();
    }

    @Override
    public void emptyStorage() {
        parent.emptyStorage();
    }

}
