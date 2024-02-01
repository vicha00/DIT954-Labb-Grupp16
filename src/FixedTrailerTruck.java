import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.*;

public class FixedTrailerTruck<T> extends Vehicle implements HasStorage<T>{
    private boolean storageOpen;
    private final Trailer<T> trailer;

    public FixedTrailerTruck(double enginePower, Color color, String modelName) {
        super(2, enginePower, color, modelName);
        this.trailer = new Trailer<>();
        storageOpen = false;
    }


    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public int getNrDoors() {
        return super.getNrDoors();
    }

    @Override
    public double getEnginePower() {
        return super.getEnginePower();
    }

    @Override
    public double getCurrentSpeed() {
        return super.getCurrentSpeed();
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(Color clr) {
        super.setColor(clr);
    }

    @Override
    public void startEngine() {
        super.startEngine();
    }

    @Override
    public void stopEngine() {
        super.stopEngine();
    }

    @Override
    public boolean isEngineOn() {
        return super.isEngineOn();
    }

    @Override
    public void gas(double amount) {
        super.gas(amount);
    }

    @Override
    public void brake(double amount) {
        super.brake(amount);
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void turnLeft(double angle) {
        super.turnLeft(angle);

    }

    @Override
    public void turnRight(double angle) {
        super.turnLeft(angle);
    }

    @Override
    public double getDirection() {
        return super.getDirection();
    }

    @Override
    public Point2D.Double getPosition() {
        return super.getPosition();
    }

    @Override
    public void openStorage() {
        storageOpen = true;
    }

    @Override
    public void closeStorage() {
        storageOpen = false;
    }

    @Override
    public boolean isStorageOpen() {
        return storageOpen;
    }

    @Override
    public void storeThing(T toStore) {
        trailer.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        Deque<T> storage = trailer.getStorage();
        return storage.pollFirst();
    }

    @Override
    public int countThings() {
        return trailer.countThings();
    }

    public void setSpeedFactor(double amount) {
        super.setSpeedFactor(amount);
    }
}
