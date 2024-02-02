import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class StorageTest {
    Storage<Integer> testStorage;

    @Before
    public void init() {
        testStorage = new Storage<>();
    }

    @Test
    public void testStorageStatus() {
        assertFalse(testStorage.isStorageOpen());
        testStorage.openStorage();
        assertTrue(testStorage.isStorageOpen());
        testStorage.closeStorage();
        assertFalse(testStorage.isStorageOpen());
    }

    @Test
    public void testStorageFunctions() {
        testStorage.openStorage();
        Integer added = 1;
        testStorage.storeThing(added);
        testStorage.storeThing(2);
        testStorage.storeThing(3);
        assertEquals(testStorage.countThings(), 3);
        Integer removed = testStorage.removeThing();
        assertEquals(3, (long) removed);
        assertEquals(testStorage.countThings(), 2);
        assertEquals(testStorage.removeThing(), (Integer) 2);


        assertThrows(IllegalAccessError.class, () -> {
            testStorage.closeStorage();
            testStorage.removeThing();
        });
    }
}
