import org.junit.*;
import static org.junit.Assert.*;
public class VolvoFLTest {
    VolvoFL testTSP;

    @Before
    public void init() {
        this.testTSP = new VolvoFL();
        this.testTSP.startEngine();
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
