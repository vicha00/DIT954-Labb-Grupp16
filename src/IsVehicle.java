import java.awt.*;

public interface IsVehicle {

    String getModel();

    int getNrDoors();

    double getEnginePower();

    double getCurrentSpeed();

    Color getColor();

    void setColor(Color clr);

    void startEngine();

    void stopEngine();

    boolean isEngineOn();

    void gas(double amount);

    void brake(double amount);
}
