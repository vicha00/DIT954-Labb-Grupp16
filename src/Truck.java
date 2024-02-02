import java.awt.Color;
import java.util.*;

public class Truck<T> extends GroundVehicle implements HasStorage<T> {

    public static final double TOURQUE_FACTOR = 0.35;
    private final Storage<T> storage;

    public Truck(double enginePower, Color color, String modelName) {
        super(2, enginePower, color, modelName);
        this.storage = new Storage<>();
    }
    @Override
    public void openStorage() {storage.openStorage();}

    @Override
    public void closeStorage() {storage.closeStorage();}

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

}
