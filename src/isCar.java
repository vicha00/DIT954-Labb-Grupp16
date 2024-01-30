import java.awt.*;

public interface isCar {

    public String getModel();

    public int getNrDoors();

    public double getEnginePower();

    public double getCurrentSpeed();

    public Color getColor();

    public void setColor(Color clr);

    public void startEngine();

    public void stopEngine();

    abstract double speedFactor();

    private void incrementSpeed(double amount){}

    private void decrementSpeed(double amount){}

    // current speed is not allowed to go above the engine power nor below 0 
    private void setCurrentSpeed(double newSpeed){}

    // gas and break is only allowed values within the span [0,1]
    public void gas(double amount);

    public void brake(double amount);
}
