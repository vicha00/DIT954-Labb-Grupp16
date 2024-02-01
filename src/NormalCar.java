import java.awt.*;

public class NormalCar extends Vehicle {

    public NormalCar(double enginePower, Color color, String modelName) {
        super(4, enginePower, color, modelName);
    }

    @Override
    public void setSpeedFactor(double amount) {
        super.setSpeedFactor(amount);
    }
}
