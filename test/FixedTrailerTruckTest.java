import org.junit.*;
import java.awt.*;
import static org.junit.Assert.*;

public class FixedTrailerTruckTest {

    FixedTrailerTruck<NormalCar> testTruck;
    NormalCar dummy;
    @Before
    public void init() {
        testTruck = new FixedTrailerTruck<>(200, Color.GREEN,"VolvoFL");
        dummy = new NormalCar(0,Color.BLACK,"dummy");
    }

    @Test
    public void storeAndRemoveTest() {
        testTruck.storeThing(dummy);
        assertEquals("Storage not open add",0, testTruck.countThings());
        testTruck.openStorage();
        testTruck.storeThing(dummy);
        assertEquals("Storage open add",1,testTruck.countThings());
        testTruck.closeStorage();
        assertThrows(IllegalArgumentException.class, () -> {testTruck.removeThing();});
        assertEquals("storage not open remove", 1, testTruck.countThings());
        testTruck.openStorage();
        NormalCar car = testTruck.removeThing();
        assertEquals("storage open remove",0,testTruck.countThings());
        assertNotNull("storage open remove",car);
    }
}
