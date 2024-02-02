import org.junit.*;
import static org.junit.Assert.*;
import java.awt.geom.Point2D;

public class VolvoFLTest {
    VolvoFL testTSP;

    @Before
    public void init() {
        this.testTSP = new VolvoFL();
        this.testTSP.startEngine();
    }

    @Test 
    public void testStoreThing(){
        Volvo240 car1 = new Volvo240();
        Saab95 car2 = new Saab95();
        testTSP.setPosition(new Point2D.Double(3, 3));
        testTSP.storeThing(car1);
        assertEquals(0, testTSP.countThings());
        car2.setPosition(new Point2D.Double(3,3));
        testTSP.storeThing(car2);
        assertEquals(1, testTSP.countThings());
    }

    @Test
    public void maxLoad() {
        testTSP.openStorage();
        for (int i = 0; i < 20; i++) {
            testTSP.storeThing(new Volvo240());
        }
        assertEquals(10,testTSP.countThings());
    }
}
