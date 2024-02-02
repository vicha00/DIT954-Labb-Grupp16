import java.awt.Color;
import java.awt.geom.Point2D;

public class VolvoFL implements IsVehicle, HasStorage<NormalCar> {

    public static final double TOURQUE_FACTOR = 0.4;
    private static Truck<NormalCar> parent;
    public VolvoFL() {
        parent = new Truck<>(200,Color.BLUE,"VolvoFL");
        parent.setSpeedFactor(parent.getEnginePower() * TOURQUE_FACTOR * 0.01);
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
    public void storeThing(NormalCar toStore) {
        parent.storeThing(toStore);
    }

    @Override
    public NormalCar removeThing() {
        return parent.removeThing();
    }

    @Override
    public int countThings() {
        return parent.countThings();
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
