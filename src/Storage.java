import java.util.*;
public abstract class Storage<T> implements HasStorage<T> {

    private boolean storageOpen = false;
    protected List<T> storage = new ArrayList<>();

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
    public abstract void storeThing(T toStore);

    @Override
    public T removeThing() {
        if (!isStorageOpen()) {
            throw new IllegalAccessError("Cant remove a vehicle from a closed storage");
        }
        return storage.removeFirst();
    }
    @Override
    public int countThings() {
        return storage.size();
    }
}
