import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.awt.*;

public class TruckTest {

    private Truck<Integer> testTruck;
    @Before
    public void init() {
        testTruck =  new Truck<>(1,Color.GREEN,"dummy");
    }

    @Test
    public void removeThingError() {
        assertThrows(IllegalAccessError.class, () -> {testTruck.removeThing();});
    }
}
