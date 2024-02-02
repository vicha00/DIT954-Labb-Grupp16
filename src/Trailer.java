import java.util.Stack;

public class Trailer<T> implements HasStorage<T> {

    private boolean storageOpen = false;
    private final Stack<T> storage = new Stack<>();

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
        if (storageOpen){
            storage.push(toStore);
        }
    }

    @Override
    public T removeThing() {
        if (!storageOpen) {
            throw new IllegalAccessError("No Storage Access: open storag before removing things");
        }
        return storage.pop();
    }

    @Override
    public int countThings() {
        return storage.size();
    }
}
