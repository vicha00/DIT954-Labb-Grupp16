import java.awt.*;
import java.awt.geom.Point2D;

public class ScaniaV8<T> extends Truck<T> implements Tippable<T>{

    private double storageAngle;
    public ScaniaV8() {
        super(200, Color.white, "ScaniaV8");
        this.storageAngle = 0;
    }

    @Override
    public double getStorageAngle() {
        return this.storageAngle;
    }

    private void setStorageAngle(double angle) {
        storageAngle = Math.clamp(angle,0,70);
    }

    @Override
    public void lowerStorage(double angle) {
        setStorageAngle(storageAngle - angle);
    }

    @Override
    public void raiseStorage(double angle) {
        setStorageAngle(storageAngle + angle);
    }

    @Override
    public void emptyStorage() {

    }
}
