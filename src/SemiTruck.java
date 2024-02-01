import java.awt.Color;
import java.util.ArrayDeque;

public class SemiTruck<T> extends Vehicle implements Tippable<T>{
    private final Trailer<T> parent;
    private double trailerAngle;

    public SemiTruck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
        parent = new Trailer<T>(new ArrayDeque<T>());
        trailerAngle = 0.0;
    }

    @Override
    public void gas(double amount) {
        if(Math.abs(trailerAngle) <= 0.001) {
            super.gas(amount);
        }
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
        if(Math.abs(super.getCurrentSpeed()) <= 0.001) {
            setStorageAngle(this.trailerAngle + angle);
        }
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
