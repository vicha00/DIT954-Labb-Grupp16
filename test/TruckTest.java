import org.junit.Before;

import java.awt.*;

public class TruckTest {

    private Truck<Integer> testTruck;
    @Before
    public void init() {
        testTruck =  new Truck<>(1,Color.GREEN,"dummy");
    }


}
