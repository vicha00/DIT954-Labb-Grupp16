import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class VehicleControll extends JComponent implements KeyListener {
    private final static int CAR_WIDTH = 10;
    private final static int CAR_LENGTH = 20;
    private final static int TRUCK_WIDTH = 14;
    private final static int Truck_LENGTH = 40;

    // private final List<Tuple<GroundVehicle,VehicleGraphicsRepresentation>>
    // vehicles;
    private List<VehicleGraphicsRepresentation> vehicles;
    private int selectedVehicleIndex;
    // private Tuple<GroundVehicle,VehicleGraphicsRepresentation> selectedVehicle;
    private VehicleGraphicsRepresentation selectedVehicle;
    private JFrame f;

    // private final List<NormalCar> cars;
    // private final List<Truck<Cargo>> trucks;
    // private final List<GroundVehicle> vehicles;
    // private GroundVehicle selectedVehicle;

    // private final List<VehicleGraphicsRepresentation> vehicleGraphics;
    // private VehicleGraphicsRepresentation selectedVehicleGraphics;

    VehicleControll(JFrame f) {
        // cars = new ArrayList<>();
        // trucks = new ArrayList<>();
        vehicles = new ArrayList<>();
        // vehicleGraphics = new ArrayList<>();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.f = f;
    }

    @Override
    public void paint(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        for (VehicleGraphicsRepresentation v : vehicles) {
            if (v == selectedVehicle) {
                v.drawSelectedVhicle(g);
            } else {
                v.draw(g);
            }
        }
    }

    // public void addVehicle(Truck<Cargo> truck) {
    public <T extends GroundVehicle> void addVehicle(Truck<Cargo> truck) {
        // trucks.add(truck);
        addVGR(new VehicleGraphicsRepresentation(truck));
    }

    public void addVehicle(NormalCar car) {
        addVGR(new VehicleGraphicsRepresentation(car));
        // cars.add(car);
        // VehicleGraphicsRepresentation vgrCar = new
        // VehicleGraphicsRepresentation(car);
        // vehicles.add(new Tuple<GroundVehicle,
        // VehicleGraphicsRepresentation>(car,vgrCar));

        // selectedVehicleIndex = vehicles.size()-1;
        // // vehicleGraphics.add(new VehicleGraphicsRepresentation(car));
        // if (selectedVehicle != null) {
        // selectedVehicle.fst().stopEngine();
        // }
        // // selectedVehicle = car;
        // selectedVehicle = vehicles.get(selectedVehicleIndex);
        // selectedVehicle.fst().startEngine();
        // selectedVehicleGraphics = vehicleGraphics.getLast();
    }

    private void addVGR(VehicleGraphicsRepresentation vgr) {
        vehicles.add(vgr);

        selectedVehicleIndex = vehicles.size() - 1;
        // vehicleGraphics.add(new VehicleGraphicsRepresentation(truck));
        if (selectedVehicle != null) {
            selectedVehicle.getVehicle().stopEngine();
        }
        selectedVehicle = vehicles.get(selectedVehicleIndex);
        // selectedVehicle = truck;
        selectedVehicle.getVehicle().startEngine();
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
                selectedVehicle.getVehicle().gas(0.5);
                break;
            case KeyEvent.VK_LEFT:
                selectedVehicle.getVehicle().turnLeft(Math.PI / 10);
                break;
            case KeyEvent.VK_RIGHT:
                selectedVehicle.getVehicle().turnRight(Math.PI / 10);
                break;
            case KeyEvent.VK_DOWN:
                selectedVehicle.getVehicle().brake(0.5);
                break;
            case KeyEvent.VK_SPACE:
                selectedVehicle.getVehicle().move();
                System.out.println(selectedVehicle.getVehicle().getCurrentSpeed());
            default:
                break;
        }
        updateVisualsAll();
    }

    private void updateVisualsAll() {
        vehicles.forEach((VehicleGraphicsRepresentation v) -> {
            v.updateGraphics();
        });
        f.repaint();
    }

    private void selectNexVehicle() {
        selectedVehicle.getVehicle().stopEngine();
        selectedVehicleIndex++;
        selectedVehicle = vehicles.get((selectedVehicleIndex) % vehicles.size());
        selectedVehicle.getVehicle().startEngine();
        // selectedVehicleGraphics =
        // vehicleGraphics.get((vehicleGraphics.indexOf(selectedVehicleGraphics)+1) %
        // vehicleGraphics.size());
        f.repaint();
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

        public GroundVehicle getVehicle() {
            return v;
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
            int[] xs = { p.xpoints[0] + 1, p.xpoints[1] + 1, p.xpoints[2] - 1, p.xpoints[3] - 1 };
            int[] ys = { p.ypoints[0] + 1, p.ypoints[1] - 1, p.ypoints[2] - 1, p.ypoints[3] + 1 };
            return new Polygon(xs, ys, p.npoints);
        }

        public void updateGraphics() {
            setCenterPoint();
            carPolygon = genGVPoly();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((centerPoint == null) ? 0 : centerPoint.hashCode());
            result = prime * result + ((carPolygon == null) ? 0 : carPolygon.hashCode());
            result = prime * result + ((v == null) ? 0 : v.hashCode());
            result = prime * result + width;
            result = prime * result + length;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            VehicleGraphicsRepresentation other = (VehicleGraphicsRepresentation) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (centerPoint == null) {
                if (other.centerPoint != null)
                    return false;
            } else if (!centerPoint.equals(other.centerPoint))
                return false;
            if (carPolygon == null) {
                if (other.carPolygon != null)
                    return false;
            } else if (!carPolygon.equals(other.carPolygon))
                return false;
            if (v == null) {
                if (other.v != null)
                    return false;
            } else if (!v.equals(other.v))
                return false;
            if (width != other.width)
                return false;
            if (length != other.length)
                return false;
            return true;
        }

        private VehicleControll getEnclosingInstance() {
            return VehicleControll.this;
        }

    }

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);

        VehicleControll m = new VehicleControll(f);

        Volvo240 volvo1 = new Volvo240();
        volvo1.setPosition(new Point2D.Double(75, 75));
        m.addVehicle(volvo1);

        Saab95 saab1 = new Saab95();
        saab1.setPosition(new Point2D.Double(325, 325));
        m.addVehicle(saab1);

        ScaniaV8<Cargo> truck1 = new ScaniaV8<>();
        truck1.setPosition(new Point2D.Double(200, 200));
        m.addVehicle(truck1);

        // f.getContentPane().add(m);
        // f.repaint();
        f.add(m);
        f.setVisible(true);
    }
}
