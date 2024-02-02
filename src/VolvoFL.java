import java.awt.Color;

public class VolvoFL extends Truck<NormalCar> {

    public static final double TOURQUE_FACTOR = 0.4;
    public VolvoFL(double enginePower, Color color, String modelName) {
        super(enginePower, color, modelName);
    }
}
