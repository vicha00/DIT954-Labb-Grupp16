import java.util.Deque;

public class Trailer<T> implements HasStorage<T> {
    private boolean storageOpen;
    private Deque<T> storage;

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
        storage.push(toStore);
    }

    @Override
    public T removeThing() {
        return storage.poll();
    }

    @Override
    public int countThings() {
        return storage.size();
    }

    public Deque<T> getStorage() {
        return storage;
    }
}
