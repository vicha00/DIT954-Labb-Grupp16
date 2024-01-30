import java.awt.geom.Point2D;

public interface Movable {

    void move();

    void turnLeft();

    void turnRight();

    double getDirection();

    Point2D.Double getPosition();
}
