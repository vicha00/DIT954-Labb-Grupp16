import java.awt.*;

public interface isVehicle {

    String getModel();

    int getNrDoors();

    double getEnginePower();

    double getCurrentSpeed();

    Color getColor();

    void setColor(Color clr);

    void startEngine();

    void stopEngine();

    void gas(double amount);

    void brake(double amount);
}
