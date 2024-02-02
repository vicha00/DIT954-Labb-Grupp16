public class Workshop<T extends IsVehicle> implements HasStorage<T> {

    private final Garage<T> garage;

    private int max_things;
    Workshop() {
        garage = new Garage<>();
    }

    @Override
    public void openStorage() {
        garage.openStorage();
        System.out.println("Were open!!!");
    }

    @Override
    public void closeStorage() {
        garage.closeStorage();
        System.out.println("Were closed.");
    }

    @Override
    public boolean isStorageOpen() {
        return garage.isStorageOpen();
    }

    @Override
    public void storeThing(T toStore) {
        garage.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return garage.removeThing();
    }

    @Override
    public int countThings() {
        return garage.countThings();
    }
}
