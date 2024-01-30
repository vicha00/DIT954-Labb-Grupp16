import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

public class CarTest {

    Car testCar;

    @Before
    public void init() {
        testCar = new Saab95();
        testCar.startEngine();
    }

    @Test
    public void testBrake() {
        double initSpeed = testCar.getCurrentSpeed();
        testCar.brake(0.1);
        assertTrue(initSpeed > testCar.getCurrentSpeed());
    }

    @Test
    public void testGas() {
        double initSpeed = testCar.getCurrentSpeed();
        testCar.gas(0.1);
        assertTrue(initSpeed < testCar.getCurrentSpeed());
    }

    @Test
    public void testGetColor() {
        assertTrue(testCar.getColor().getClass() == Color.class);
    }

    @Test
    public void testGetCurrentSpeed() {
        assertTrue(Double.isFinite(testCar.getCurrentSpeed()));
    }

    @Test
    public void testGetDirection() {
        assertTrue(Double.isFinite(testCar.getDirection()));
    }

    @Test
    public void testGetEnginePower() {
        assertTrue(testCar.getEnginePower() > 0);
    }

    @Test
    public void testGetNrDoors() {
        int nrDoors = testCar.getNrDoors();
        assertTrue(nrDoors == 2 || nrDoors == 4);
    }

    @Test
    public void testGetPosition() {
        assertNotNull(testCar.getPosition());
    }

    @Test
    public void testMove() {
        Point2D.Double initial = testCar.getPosition();
        testCar.move();
        assertNotEquals(initial, testCar.getPosition());
    }

    @Test
    public void testSetColor() {
        Color initColour = testCar.getColor();
        testCar.setColor(initColour.darker());
        assertNotEquals(initColour, testCar.getColor());
    }

    @Test
    public void testStartEngine() {
        assertNotEquals(0, testCar.getCurrentSpeed(), 0.01);
    }

    @Test
    public void testStopEngine() {
        testCar.stopEngine();
        assertEquals(0, testCar.getCurrentSpeed(), 0.01);
    }

    @Test
    public void testTurnLeft() {
        double initDir = testCar.getDirection();
        testCar.turnLeft();
        assertTrue(initDir < testCar.getDirection());
    }

    @Test
    public void testTurnRight() {
        double initDir = testCar.getDirection();
        testCar.turnRight();
        assertTrue(initDir > testCar.getDirection());
    }

    @Test
    public void throwGasBrakeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCar.gas(2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCar.gas(-2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCar.brake(2);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCar.brake(-2);
                });
    }

    @Test
    public void ensureCurrentSpeedAllowedValue() {
        for (int i = 0; i < 100; i++) {
            testCar.brake(1);
        }
        assertTrue(testCar.getCurrentSpeed() >= 0);

        for (int i = 0; i < 100; i++) {
            testCar.gas(1);
        }
        assertTrue(testCar.getCurrentSpeed() <= testCar.getEnginePower());
    }

}
