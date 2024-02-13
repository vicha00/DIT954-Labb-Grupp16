import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

/* ---------------------------------- WARNING ----------------------------------
 *                          THIS CLASS IS AN ABOMINATION
 * -------------------------------------------------------------------------- */

public class VehicleControll extends JComponent implements KeyListener {
    private final static int CAR_WIDTH = 10;
    private final static int CAR_LENGTH = 20;
    private final static int TRUCK_WIDTH = 14;
    private final static int Truck_LENGTH = 40;

    private List<VehicleGraphicsRepresentation> vehicles;
    private int selectedVehicleIndex;
    private VehicleGraphicsRepresentation selectedVehicle;
    private JFrame f;

    VehicleControll(JFrame f) {
        vehicles = new ArrayList<>();
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

    public <T> void addVehicle(Truck<T> truck) {
        addVGR(new VehicleGraphicsRepresentation(truck));
    }

    public void addVehicle(NormalCar car) {
        addVGR(new VehicleGraphicsRepresentation(car));
    }

    private void addVGR(VehicleGraphicsRepresentation vgr) {
        vehicles.add(vgr);
        selectAddedVehicle();
    }

    private void selectAddedVehicle() {
        stopEngineOfPrevSelectedVehicle();
        selectedVehicleIndex = vehicles.size() - 1;
        selectedVehicle = vehicles.get(selectedVehicleIndex);
        selectedVehicle.getVehicle().startEngine();
    }

    private void stopEngineOfPrevSelectedVehicle() {
        if (selectedVehicle != null) {
            selectedVehicle.getVehicle().stopEngine();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("keyTyped: " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_TAB:
                selectNexVehicle();
                break;
            case KeyEvent.VK_UP:
                gasSelectedVehicle();
                break;
            case KeyEvent.VK_LEFT:
                turnSelectedVehicleLeft();
                break;
            case KeyEvent.VK_RIGHT:
                turnSelectedVehicleRigth();
                break;
            case KeyEvent.VK_DOWN:
                brakeSelectedVehicle();
                break;
            case KeyEvent.VK_SPACE:
                moveAllVehicles();
            default:
                break;
        }
        updateVisualsAll();
    }

    private void moveAllVehicles() {
        vehicles.forEach((VehicleGraphicsRepresentation v) -> {
            v.getVehicle().move();
            // theWorldIsATorus(v.getVehicle());
            theWorldHasBouncyWalls(v.getVehicle());
        });
        System.out.println(selectedVehicle.getVehicle().getCurrentSpeed());
    }

    private void brakeSelectedVehicle() {
        selectedVehicle.getVehicle().brake(0.5);
    }

    private void turnSelectedVehicleRigth() {
        selectedVehicle.getVehicle().turnRight(Math.PI / 10);
    }

    private void turnSelectedVehicleLeft() {
        selectedVehicle.getVehicle().turnLeft(Math.PI / 10);
    }

    private void gasSelectedVehicle() {
        selectedVehicle.getVehicle().gas(0.5);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyPressed: " + e.getKeyCode());
    }


    private void theWorldHasBouncyWalls(GroundVehicle v) {
        if (isOutOfBounds(v)) {
            v.turnLeft(Math.PI);
        }
    }

    private boolean isOutOfBounds(GroundVehicle v){
        double x = v.getPosition().getX();
        int w = f.getWidth();
        double y = v.getPosition().getY();
        int h = f.getHeight();
        return x <= 0 || w <= x || y <= 0 || h <= y;
    }

    private void theWorldIsATorus(GroundVehicle v) {
        double x = v.getPosition().getX();
        int w = f.getWidth();
        double y = v.getPosition().getY();
        int h = f.getHeight();
        if (x < 0) {
            x += w;
        } else if (w < x) {
            x -= w;
        }
        if (y < 0) {
            y += h;
        } else if (h < y) {
            y -= h;
        }
        v.setPosition(new Point2D.Double(x, y));
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
            double[] l = { length * Math.cos(theta), length * Math.sin(theta) };
            // double[] w = { width * Math.cos(theta), width * Math.sin(theta) };
            double phi = Math.PI / 2.0 - theta;
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

        public <T> VehicleGraphicsRepresentation(Truck<T> truck) {
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
        f.setSize(800, 400);

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

        
        VolvoFL truck2 = new VolvoFL();
        truck2.setPosition(new Point2D.Double(500, 250));
        m.addVehicle(truck2);

        f.add(m);
        f.setVisible(true);
    }
}
