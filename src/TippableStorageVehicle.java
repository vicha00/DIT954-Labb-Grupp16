import java.awt.Color;
import java.awt.List;
import java.awt.geom.Point2D;
import java.util.Stack;

public class TippableStorageVehicle<T> implements IsVehicle, Tippable<T>{
    private final Vehicle parent;
    private double trailerAngle;

    public TippableStorageVehicle(int nrDoors, double enginePower, Color color, String modelName) {
        parent = new Vehicle(nrDoors, enginePower, color, modelName);
        trailerAngle = 0.0;
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
        if(Math.abs(trailerAngle) <= 0.001) {
            parent.gas(amount);
        }
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
    }

    @Override
    public void closeStorage() {
    }

    @Override
    public boolean isStorageOpen() {
    }

    @Override
    public double getStorageAngle() {
        return this.trailerAngle;
    }

    private void setStorageAngle(double angle) {
        this.trailerAngle = Math.clamp(angle, 0.0, 70.0);
    }

    @Override
    public void lowerStorage(double angle) {
        setStorageAngle(this.trailerAngle - angle);
    }

    @Override
    public void raiseStorage(double angle) {
        if(Math.abs(parent.getCurrentSpeed()) <= 0.001) {
            setStorageAngle(this.trailerAngle + angle);
        }
    }

    public void setSpeedFactor(double amount) {
        parent.setSpeedFactor(amount);
    }

    @Override
    public void storeThing(T toStore) {
    }

    @Override
    public T removeThing() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeThing'");
    }

    @Override
    public int countThings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countThings'");
    }

    public Stack<T> getStorage(){
        return (Stack<T>) parent.getStorage(); // source: trust me bro
    }

    @Override
    public void emptyStorage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emptyStorage'");
    }

}
