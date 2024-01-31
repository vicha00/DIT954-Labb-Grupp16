import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class ScaniaTest {
    Scania testScania;

    @Before
    public void init() {
        testScania = new Scania();
        testScania.startEngine();
    }

    @Test
    public void lowerAndHeightenStorage() {
        testScania.raiseStorage(70);
        testScania.lowerStorage(35);
        assertEquals(35, testScania.getStorageAngle(), 0.01);
        testScania.gas(1);
        testScania.lowerStorage(35);
        assertNotEquals(35, testScania.getStorageAngle(), 0.01);
        testScania.lowerStorage(80);
        assertEquals(0, testScania.getStorageAngle(), 0.01);
        testScania.raiseStorage(70);
        assertEquals(70, testScania.getStorageAngle(), 0.01);
        testScania.lowerStorage(70);
        testScania.gas(1);
        testScania.raiseStorage(70);
        assertNotEquals(70, testScania.getStorageAngle(), 0.01);
        testScania.brake(1);
        testScania.raiseStorage(80);
        assertEquals(70, testScania.getStorageAngle(), 0.01);
    }

    @Test
    public void noGasWhenStorageIsUp (){
        testScania.raiseStorage(70);
        testScania.gas(1);
        assertEquals(0, testScania.getCurrentSpeed(), 0.01);
    }
}




