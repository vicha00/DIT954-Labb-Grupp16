import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class NonFixedTrailerTruckTest<T>{
    NonFixedTrailerTruck<T> testTSP;

    @Before
    public void init() {
        testTSP = new NonFixedTrailerTruck<T>(2, 200, Color.white, "Scania");
        testTSP.startEngine();
    }

    @Test
    public void lowerAndHeightenStorage() {
        testTSP.raiseStorage(70);
        testTSP.lowerStorage(35);
        assertEquals(35, testTSP.getStorageAngle(), 0.01);
        testTSP.gas(1);
        testTSP.lowerStorage(35);
        assertNotEquals(35, testTSP.getStorageAngle(), 0.01);
        testTSP.lowerStorage(80);
        assertEquals(0, testTSP.getStorageAngle(), 0.01);
        testTSP.raiseStorage(70);
        assertEquals(70, testTSP.getStorageAngle(), 0.01);
        testTSP.lowerStorage(70);
        testTSP.gas(1);
        testTSP.raiseStorage(70);
        assertNotEquals(70, testTSP.getStorageAngle(), 0.01);
        testTSP.brake(1);
        testTSP.raiseStorage(80);
        assertEquals(70, testTSP.getStorageAngle(), 0.01);
    }

    @Test
    public void noGasWhenStorageIsUp (){
        testTSP.raiseStorage(70);
        testTSP.gas(1);
        assertEquals(0, testTSP.getCurrentSpeed(), 0.01);
    }
}



