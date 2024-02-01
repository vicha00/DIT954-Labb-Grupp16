import java.awt.*;

public class testMain {
    public static void main(String[] args) {
        Workshop<Volvo240> volvo240Workshop = new Workshop<>();
        Workshop<IsVehicle> workshop = new Workshop<>();

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();

        workshop.storeThing(saab);
        volvo240Workshop.storeThing(saab);
        workshop.storeThing(volvo);
        volvo240Workshop.storeThing(volvo);
    }
}
