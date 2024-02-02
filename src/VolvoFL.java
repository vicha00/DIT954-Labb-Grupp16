import java.awt.*;
import java.awt.geom.Point2D;

public class VolvoFL extends Truck<NormalCar> {

    public static final int MAX_LOAD = 10;
    public VolvoFL() {
        super(200, Color.BLUE, "VolvoFL");
    }

    @Override
    public void storeThing(NormalCar thing) {
        if(countThings() >= MAX_LOAD) {
            return;
        }
        thing.setPosition(getPositionRef());
        super.storeThing(thing);
    }

    @Override
    public NormalCar removeThing() {
        NormalCar removedCar = super.removeThing();
        removedCar.setPosition(new Point2D.Double(this.getPosition().getX(),this.getPosition().getY()));
        return removedCar;
    }
}



