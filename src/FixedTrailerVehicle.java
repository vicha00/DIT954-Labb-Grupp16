import java.awt.Color;
import java.util.Stack;

public class FixedTrailerVehicle extends Vehicle implements  HasStorage<T> {
    // TODO : change vehicle to car

    public FixedTrailerVehicle(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
        Stack<T> storage = new Stack<>();
    }

    @Override
    public void openStorage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openStorage'");
    }

    @Override
    public void closeStorage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeStorage'");
    }

    @Override
    public boolean isStorageOpen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStorageOpen'");
    }

    @Override
    public void storeThing(Vehicle toStore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeThing'");
    }

    @Override
    public Vehicle removeThing() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeThing'");
    }

    @Override
    public int countThings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countThings'");
    }

}
