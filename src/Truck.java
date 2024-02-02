import java.awt.Color;

public class Truck<T> extends GroundVehicle implements HasStorage<T> {

    public static final double TOURQUE_FACTOR = 0.35;
    private final Trailer<T> storage;

    public Truck(double enginePower, Color color, String modelName) {
        super(2, enginePower, color, modelName);
        this.storage = new Trailer<>(10);
    }

    @Override
    public void openStorage() {
        if (Math.abs(getCurrentSpeed()) > 0.01) {
            return;
        }
        storage.openStorage();
    }

    @Override
    public void closeStorage() {
        if (Math.abs(getCurrentSpeed()) > 0.01) {
            return;
        }
        storage.closeStorage();
    }

    @Override
    public boolean isStorageOpen() {
        return storage.isStorageOpen();
    }

    @Override
    public void storeThing(T toStore) {
        storage.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        if(!storage.isStorageOpen()) {
            throw new IllegalAccessError("Cant remove a thing from a closed trailer");
        }
        return storage.removeThing();
    }

    @Override
    public int countThings() {
        return storage.countThings();
    }

    @Override
    public double speedFactor() {
        return super.speedFactor() * TOURQUE_FACTOR;
    }

    @Override
    public void gas(double amount) {
        if (storage.isStorageOpen()) {
            return;
        }
        super.gas(amount);
    }

}
