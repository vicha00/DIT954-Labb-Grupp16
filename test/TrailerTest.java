import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class TrailerTest {
    Trailer<Integer> testTrailer;

    @Before
    public void init() {
        testTrailer = new Trailer<>();
    }

    @Test
    public void testStorageStatus() {
        assertFalse(testTrailer.isStorageOpen());
        testTrailer.openStorage();
        assertTrue(testTrailer.isStorageOpen());
        testTrailer.closeStorage();
        assertFalse(testTrailer.isStorageOpen());
    }

    @Test
    public void testStorageFunctions() {
        testTrailer.openStorage();
        Integer added = 1;
        testTrailer.storeThing(added);
        testTrailer.storeThing(2);
        testTrailer.storeThing(3);
        assertEquals(testTrailer.countThings(), 3);
        Integer removed = testTrailer.removeThing();
        assertEquals(3, (long) removed);
        assertEquals(testTrailer.countThings(), 2);
        assertEquals(testTrailer.removeThing(), (Integer) 2);


        assertThrows(IllegalAccessError.class, () -> {
            testTrailer.closeStorage();
            testTrailer.removeThing();
        });
    }
}
