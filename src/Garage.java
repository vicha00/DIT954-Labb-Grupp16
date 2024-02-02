import java.util.List;
import java.util.Queue;

public class Garage<T extends IsVehicle> extends Storage<T>{

    @Override
    public void storeThing(T toStore) {
        if(isStorageOpen()) {
            storage.add(toStore);
        }
    }

}
