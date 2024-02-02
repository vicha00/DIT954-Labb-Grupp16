import java.awt.Color;
import java.util.*;

public class FixedTrailerTruck<T> extends Vehicle implements HasStorage<T>{
    private final Storage<T> storage;

    public FixedTrailerTruck(double enginePower, Color color, String modelName) {
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
        if (!storage.isStorageOpen()) {
            throw new IllegalAccessError("No Storage Access: open storage before removing things");
        }
        Deque<T> storage = this.storage.getStorage();
        return storage.pollFirst();
    }

    @Override
    public int countThings() {
        return storage.countThings();
    }

    public void setSpeedFactor(double amount) {
        super.setSpeedFactor(amount);
    }
}
