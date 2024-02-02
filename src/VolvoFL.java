import java.awt.Color;

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
        super.storeThing(thing);
    }

}



