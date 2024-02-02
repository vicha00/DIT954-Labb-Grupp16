import java.util.*;

public class Trailer<T> extends Storage<T> {

    @Override
    public void storeThing(T toStore) {
        if (isStorageOpen()) {
            storage.addFirst(toStore);
        }
    }
}
