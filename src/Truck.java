import java.awt.Color;

public class Truck<T> extends GroundVehicle implements HasStorage<T> {

    public static final double TOURQUE_FACTOR = 0.35;
    private final Trailer<T> trailer;

    public Truck(double enginePower, Color color, String modelName) {
        super(2, enginePower, color, modelName);
        this.trailer = new Trailer<>();
    }

    @Override
    public void openStorage() {
        trailer.openStorage();
    }

    @Override
    public void closeStorage() {
        trailer.closeStorage();
    }

    @Override
    public boolean isStorageOpen() {
        return trailer.isStorageOpen();
    }

    @Override
    public void storeThing(T toStore) {
        trailer.storeThing(toStore);
    }

    @Override
    public T removeThing() {
        return trailer.removeThing();
    }

    @Override
    public int countThings() {
        return trailer.countThings();
    }

    @Override
    public double speedFactor() {
        return super.speedFactor() * TOURQUE_FACTOR;
    }

}
