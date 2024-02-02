import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends NormalCar {

    private final static double TRIMFACTOR = 1.25;


    public Volvo240() {
        super(4, Color.black, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01 * TRIMFACTOR;
    }
}
