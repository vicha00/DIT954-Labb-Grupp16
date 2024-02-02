public class Workshop<T extends IsVehicle> implements HasStorage<T> {

    private final Trailer<T> vehicles;
    Workshop() {
        vehicles = new Trailer<>();
    }

    @Override
    public void openStorage() {
        vehicles.openStorage();
        System.out.println("Were open!!!");
    }

    @Override
    public void closeStorage() {
        vehicles.closeStorage();
        System.out.println("Were closed.");
    }

    @Override
    public boolean isStorageOpen() {
        return vehicles.isStorageOpen();
    }

    @Override
    public void storeThing(T toStore) {
        vehicles.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return vehicles.removeThing();
    }

    @Override
    public int countThings() {
        return vehicles.countThings();
    }
}
