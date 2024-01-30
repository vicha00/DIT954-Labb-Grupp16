import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

    Vehicle testVehicle;

    @Before
    public void init() {
        testVehicle = new Vehicle(4, 120, Color.red, "testVehicle");
        testVehicle.startEngine();
    }
    
    @Test
    public void testIsEngineOn() {
        assertTrue(testVehicle.isEngineOn());
        testVehicle.stopEngine();
        assertFalse(testVehicle.isEngineOn());
    }

    @Test
    public void testBrake() {
        double initSpeed = testVehicle.getCurrentSpeed();
        testVehicle.brake(0.5);
        assertTrue(initSpeed > testVehicle.getCurrentSpeed());
    }

    @Test
    public void testGas() {
        double initSpeed = testVehicle.getCurrentSpeed();
        testVehicle.gas(0.5);
        assertTrue(initSpeed < testVehicle.getCurrentSpeed());
    }

    @Test
    public void testGetColor() {
        assertNotNull(testVehicle.getColor());
    }

    @Test
    public void testGetCurrentSpeed() {
        assertTrue(0 <= testVehicle.getCurrentSpeed() 
            && testVehicle.getCurrentSpeed() <= testVehicle.getEnginePower());
    }

    @Test
    public void testGetDirection() {
        assertTrue(Double.isFinite(testVehicle.getDirection()));
    }

    @Test
    public void testGetEnginePower() {
        assertTrue(testVehicle.getEnginePower() > 0);
    }

    @Test
    public void testGetNrDoors() {
        int nrDoors = testVehicle.getNrDoors();
        assertTrue(nrDoors == 2 || nrDoors == 4);
    }

    @Test
    public void testGetPosition() {
        assertNotNull(testVehicle.getPosition());
    }

    @Test
    public void testMove() {
        Point2D.Double initial = testVehicle.getPosition();
        testVehicle.move();
        assertNotEquals(initial, testVehicle.getPosition());
    }

    @Test
    public void testSetColor() {
        Color initColour = testVehicle.getColor();
        testVehicle.setColor(initColour.darker());
        assertNotEquals(initColour, testVehicle.getColor());
    }

    @Test
    public void testStartEngine() {
        assertNotEquals(0, testVehicle.getCurrentSpeed(), 0.01);
    }

    @Test
    public void testStopEngine() {
        testVehicle.stopEngine();
        assertEquals(0, testVehicle.getCurrentSpeed(), 0.01);
    }

    @Test
    public void testTurnLeft() {
        double initDir = testVehicle.getDirection();
        testVehicle.turnLeft(1.5);
        assertTrue(initDir < testVehicle.getDirection());
    }

    @Test
    public void testTurnRight() {
        double initDir = testVehicle.getDirection();
        testVehicle.turnRight(1.5);
        assertTrue(initDir > testVehicle.getDirection());
    }

    @Test
    public void throwGasBrakeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testVehicle.gas(2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testVehicle.gas(-2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testVehicle.brake(2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testVehicle.brake(-2);
                });
    }

    @Test
    public void ensureCurrentSpeedAllowedValue() {
        for (int i = 0; i < 100; i++) {
            testVehicle.brake(1);
        }
        assertTrue(testVehicle.getCurrentSpeed() >= 0);

        for (int i = 0; i < 100; i++) {
            testVehicle.gas(1);
        }
        assertTrue(testVehicle.getCurrentSpeed() <= testVehicle.getEnginePower());
    }

}
