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
        testTSP.setPosition(new Point2D.Double(3, 3));
        testTSP.openStorage();
        Volvo240 car1 = new Volvo240();
        double ah1 = testTSP.getPosition().distance(car1.getPosition());
        assertTrue(ah1 > 1.0);
        testTSP.storeThing(car1);
        assertEquals(0, testTSP.countThings());
        Saab95 car2 = new Saab95();
        car2.setPosition(new Point2D.Double(2.9,2.9));
        double ah2 = testTSP.getPosition().distance(car2.getPosition());
        assertTrue(ah2 < 1.0);
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
