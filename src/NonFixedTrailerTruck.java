import java.awt.Color;

public class NonFixedTrailerTruck<T> extends Vehicle implements Tippable<T>{
    private Storage<T> storage;
    private double trailerAngle;

    public NonFixedTrailerTruck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
        storage = new Storage<T>();
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
        storage.openStorage();
    }

    @Override
    public void closeStorage() {
        storage.closeStorage();
    }

    @Override
    public boolean isStorageOpen() {
        return storage.isStorageOpen();
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
        storage.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return storage.removeThing();
    }

    @Override
    public int countThings() {
        return storage.countThings();
    }

    @Override
    public void emptyStorage() {
        storage = new Storage<>();
    }

}
