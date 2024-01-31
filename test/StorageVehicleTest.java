
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class StorageVehicleTest<T> {
    StorageVehicle<T> testVehicle;
    @Before
    public void init()
    {
        testVehicle = new StorageVehicle<T>(2, 150, Color.CYAN, "testVehicleModel", new ArrayList<T>());
        //storageOpen set to false on construction
    }

    @Test
    public void testCloseStorage() {
        assertFalse(testVehicle.isStorageOpen());
    }

    @Test
    public void testOpenStorage() {
        testVehicle.openStorage();
        assertTrue(testVehicle.isStorageOpen());
    }
}
