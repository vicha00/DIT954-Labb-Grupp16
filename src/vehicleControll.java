import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class vehicleControll extends Canvas {
    private final static int CAR_WIDTH = 5;
    private final static int CAR_LENGTH = 10;
    private final static int TRUCK_WIDTH = 7;
    private final static int Truck_LENGTH = 20;

    private final List<NormalCar> cars;
    private final List<Truck<Cargo>> trucks;
    private final List<VehicleGraphicsRepresentation>  vehicles;
    private GroundVehicle selectedCar;

    vehicleControll(){
        cars = new ArrayList<>();
        trucks = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public void paint(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        for (NormalCar car : cars) {
            new VehicleGraphicsRepresentation(car).draw(g);
        }
        for (Truck<Cargo> truck : trucks) {
            new VehicleGraphicsRepresentation(truck).draw(g);
        }
    }



    private class VehicleGraphicsRepresentation {
        Point centerPoint;
        Polygon carPolygon;
        

        private VehicleGraphicsRepresentation(GroundVehicle v, int w, int l) {
            centerPoint = new Point((int) v.getPosition().x, (int) v.getPosition().y);
            /* carPolygon = genPoly(); // TODO rectangle pointing in direction of vehicle*/
        }
        
        public VehicleGraphicsRepresentation(NormalCar car) {
            this(car, CAR_WIDTH, CAR_LENGTH);

        }
        public VehicleGraphicsRepresentation(Truck<Cargo> truck) {
            this(truck, TRUCK_WIDTH, Truck_LENGTH);
        }

        public void draw(Graphics g) {
            g.fillPolygon(carPolygon);
        }

    }
}
