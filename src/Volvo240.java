import java.awt.*;

public class Volvo240 implements isCar {

    private final static double trimFactor = 1.25;

    public Volvo240() {
        // super(4, 100, Color.black, "Volvo240");
    }

    @Override
    private double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

}
