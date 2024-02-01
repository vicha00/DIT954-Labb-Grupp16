import java.awt.Color;

public class NonFixedTrailerTruck<T> extends Vehicle implements Tippable<T>{
    private Trailer<T> trailer;
    private double trailerAngle;

    public NonFixedTrailerTruck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
        trailer = new Trailer<T>();
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
        trailer.openStorage();
    }

    @Override
    public void closeStorage() {
        trailer.closeStorage();
    }

    @Override
    public boolean isStorageOpen() {
        return trailer.isStorageOpen();
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
        trailer.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return trailer.removeThing();
    }

    @Override
    public int countThings() {
        return trailer.countThings();
    }

    @Override
    public void emptyStorage() {
        trailer = new Trailer<>();
    }

}
