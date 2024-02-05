import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.*;

public class VehicleControll extends Canvas {
    private final static int CAR_WIDTH = 5;
    private final static int CAR_LENGTH = 10;
    private final static int TRUCK_WIDTH = 7;
    private final static int Truck_LENGTH = 20;

    private final List<NormalCar> cars;
    private final List<Truck<Cargo>> trucks;
    private final List<VehicleGraphicsRepresentation> vehicles;
    private VehicleGraphicsRepresentation selectedVehicle;

    VehicleControll(){
        cars = new ArrayList<>();
        trucks = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public void paint(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        for (VehicleGraphicsRepresentation v : vehicles) {
            if (v == selectedVehicle) {
                v.drawSelectedVhicle(g);
            }
            else {
                v.draw(g);
            }
            
        }
    }

    public boolean addVehicle(Truck<Cargo> truck) {
        trucks.add(truck);
        return vehicles.add(new VehicleGraphicsRepresentation(truck));
        
    }
    public boolean addVehicle(NormalCar car) {
        cars.add(car);
        return vehicles.add(new VehicleGraphicsRepresentation(car));
    }


    // public void selectVehicle()

    



    private class VehicleGraphicsRepresentation {
        Point centerPoint;
        Polygon carPolygon;
        GroundVehicle v;

        private VehicleGraphicsRepresentation(GroundVehicle v, int w, int l) {
            this.v = v;
            centerPoint = new Point((int) v.getPosition().x, (int) v.getPosition().y);
            carPolygon = genGVPoly( w, l); 
        }

        private Polygon genGVPoly(int width, int length) {
            double theta = v.getDirection();
            double phi = Math.PI/2.0 -theta;
            double[] l = {length*Math.cos(theta), length*Math.sin(theta)};
            double[] w = {width*Math.cos(phi), width*Math.sin(phi)};
            double x = (l[0] + w[0])/2.0;
            double y = (l[1] + w[1])/2.0;
            Point o = centerPoint;
            int[] xPoints = {(int) (o.getX() - x), 
                            (int) (o.getX() - x), 
                            (int) (o.getX() + x ),
                            (int) (o.getX() + x)};
            int[] yPoints = {(int) (o.getY() - y),
                            (int) (o.getY() + y),
                            (int) (o.getY() + y),
                            (int) (o.getY() - y)};
            return new Polygon(xPoints, yPoints, 4);
        }

        public VehicleGraphicsRepresentation(NormalCar car) {
            this(car, CAR_WIDTH, CAR_LENGTH);

        }
        public VehicleGraphicsRepresentation(Truck<Cargo> truck) {
            this(truck, TRUCK_WIDTH, Truck_LENGTH);
        }

        public void draw(Graphics g) {
            g.setColor(v.getColor());
            g.fillPolygon(carPolygon);
        }
        
        public void drawSelectedVhicle(Graphics g) {
            draw(g);
            g.setColor(Color.black);
            g.drawPolygon(carPolygon);
            g.setColor(Color.white);
            g.fillOval(centerPoint.x, centerPoint.y, 1, 1 );
        }

    }


    public static void main(String[] args) {
        VehicleControll m=new VehicleControll();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(400,400);  
          
        f.setVisible(true); 
    }
}
