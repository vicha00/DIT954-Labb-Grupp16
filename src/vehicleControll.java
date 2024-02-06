import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class VehicleControll extends Canvas implements KeyListener{
    private final static int CAR_WIDTH = 10;
    private final static int CAR_LENGTH = 20;
    private final static int TRUCK_WIDTH = 14;
    private final static int Truck_LENGTH = 40;

    private final List<Tuple<GroundVehicle,VehicleGraphicsRepresentation>> vehicles;
    private int selectedVehicleIndex;
    private Tuple<GroundVehicle,VehicleGraphicsRepresentation> selectedVehicle;


    // private final List<NormalCar> cars;
    // private final List<Truck<Cargo>> trucks;
    // private final List<GroundVehicle> vehicles;
    // private GroundVehicle selectedVehicle;

    // private final List<VehicleGraphicsRepresentation> vehicleGraphics;
    // private VehicleGraphicsRepresentation selectedVehicleGraphics;

    VehicleControll() {
        // cars = new ArrayList<>();
        // trucks = new ArrayList<>();
        vehicles = new ArrayList<>();
        // vehicleGraphics = new ArrayList<>();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paint(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        for (Tuple<GroundVehicle,VehicleGraphicsRepresentation> v : vehicles/* vehicleGraphics */) {
            if (v.snd() == selectedVehicle.snd()) {
                v.snd().drawSelectedVhicle(g);
            } else {
                v.snd().draw(g);
            }
        }
    }

    public void addVehicle(Truck<Cargo> truck) {
        // trucks.add(truck);
        VehicleGraphicsRepresentation vgrTruck = new VehicleGraphicsRepresentation(truck);
        vehicles.add(new Tuple<GroundVehicle, VehicleGraphicsRepresentation>(truck,vgrTruck));

        selectedVehicleIndex = vehicles.size()-1;
        // vehicleGraphics.add(new VehicleGraphicsRepresentation(truck));
        if (selectedVehicle != null) {
            selectedVehicle.fst().stopEngine();
        }
        selectedVehicle = vehicles.get(selectedVehicleIndex);
        // selectedVehicle = truck;
        selectedVehicle.fst().startEngine();
        // selectedVehicleGraphics = vehicleGraphics.getLast();
    }

    public void addVehicle(NormalCar car) {
        // cars.add(car);
        VehicleGraphicsRepresentation vgrCar = new VehicleGraphicsRepresentation(car);
        vehicles.add(new Tuple<GroundVehicle, VehicleGraphicsRepresentation>(car,vgrCar));

        selectedVehicleIndex = vehicles.size()-1;
        // vehicleGraphics.add(new VehicleGraphicsRepresentation(car));
        if (selectedVehicle != null) {
            selectedVehicle.fst().stopEngine();
        }
        // selectedVehicle = car;
        selectedVehicle = vehicles.get(selectedVehicleIndex);
        selectedVehicle.fst().startEngine();
        // selectedVehicleGraphics = vehicleGraphics.getLast();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("keyTyped: " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_TAB:
            selectNexVehicle();
            break;
            case KeyEvent.VK_UP:
            selectedVehicle.fst().gas(0.5);
            break;
            case KeyEvent.VK_LEFT:
            selectedVehicle.fst().turnLeft(Math.PI/10);
            updateVisuals();
            break;
            case KeyEvent.VK_RIGHT:
            selectedVehicle.fst().turnRight(Math.PI/10);
            updateVisuals();
            break;
            case KeyEvent.VK_DOWN:
            selectedVehicle.fst().brake(0.5);
            break;
            case KeyEvent.VK_SPACE:
            selectedVehicle.fst().move();
            updateVisuals();
            System.out.println(selectedVehicle.fst().getCurrentSpeed());
            default:
                break;
        }
    }
    private void updateVisuals() {
        Tuple<GroundVehicle,VehicleGraphicsRepresentation> tpl = new Tuple<>(selectedVehicle.fst(), selectedVehicle.snd().update());
        vehicles.set(selectedVehicleIndex, tpl);
        paint(this.getGraphics());
    }

    private void selectNexVehicle() {
        selectedVehicle.fst().stopEngine();
        selectedVehicle = vehicles.get((selectedVehicleIndex+1) % vehicles.size());
        selectedVehicle.fst().startEngine();
        // selectedVehicleGraphics = vehicleGraphics.get((vehicleGraphics.indexOf(selectedVehicleGraphics)+1) % vehicleGraphics.size());
        paint(this.getGraphics());
    }

    private class VehicleGraphicsRepresentation {
        private Point centerPoint;
        private Polygon carPolygon;
        private final GroundVehicle v;
        private final int width;
        private final int length;


        private VehicleGraphicsRepresentation(GroundVehicle v, int w, int l) {
            this.v = v;
            width = w;
            length = l;
            setCenterPoint();
            carPolygon = genGVPoly();
        }

        private void setCenterPoint() {
            centerPoint = new Point((int) v.getPosition().x, (int) v.getPosition().y);
        }

        private Polygon genGVPoly() {
            double theta = v.getDirection();
            double phi = Math.PI / 2.0 - theta;
            double[] l = { length * Math.cos(theta), length * Math.sin(theta) };
            double[] w = { width * Math.cos(phi), width * Math.sin(phi) };
            double x = (l[0] + w[0]) / 2.0;
            double y = (l[1] + w[1]) / 2.0;
            Point o = centerPoint;
            int[] xPoints = { (int) (o.getX() - x),
                    (int) (o.getX() - x),
                    (int) (o.getX() + x),
                    (int) (o.getX() + x) };
            int[] yPoints = { (int) (o.getY() - y),
                    (int) (o.getY() + y),
                    (int) (o.getY() + y),
                    (int) (o.getY() - y) };
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
            g.drawPolygon(shrink(carPolygon));
            g.setColor(Color.white);
            g.fillOval(centerPoint.x - 2, centerPoint.y - 2, 4, 4);
        }
        private Polygon shrink(Polygon p) {
            int[] xs = {p.xpoints[0] + 1, p.xpoints[1] + 1, p.xpoints[2] - 1, p.xpoints[3] - 1};
            int[] ys = {p.ypoints[0] + 1, p.ypoints[1] - 1, p.ypoints[2] - 1, p.ypoints[3] + 1};
            return new Polygon(xs, ys, p.npoints);
        }

        public VehicleGraphicsRepresentation update() {
            setCenterPoint();
            carPolygon = genGVPoly();
            return this;
        }
    }

    private class Tuple<A,B> {
        private final A a;
        private final B b;
        Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
        public A fst() {return a;}
        public B snd() {return b;}
    }

    public static void main(String[] args) {
        VehicleControll m = new VehicleControll();

        Volvo240 volvo1 = new Volvo240();
        volvo1.setPosition(new Point2D.Double(75, 75));
        m.addVehicle(volvo1);

        Saab95 saab1 = new Saab95();
        saab1.setPosition(new Point2D.Double(325, 325));
        m.addVehicle(saab1);

        ScaniaV8<Cargo> truck1 = new ScaniaV8<>();
        truck1.setPosition(new Point2D.Double(200, 200));
        m.addVehicle(truck1);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(m);
        f.setSize(400, 400);
        f.setVisible(true);
    }
}
