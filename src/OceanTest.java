import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

public class OceanTest {
    private Ocean ocean;

    @BeforeEach
    public void setUp() {
        ocean = new Ocean();
    }

    /******************* Test Ocean Class ********************/
    @Test
    public void testPlaceAllShipsRandomly() {
        ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        int shipsCount = 0;
        for (int i = 0; i < Ocean.OCEAN_SIZE; i++) {
            for (int j = 0; j < Ocean.OCEAN_SIZE; j++) {
                if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
                    shipsCount++;
                }
            }
        }
        assertEquals(20, shipsCount); // 20 ship blocks in total (= 4*1 + 3*2 + 2*3 + 1*4)
    }

    @Test
    public void testIsOccupied() {
        // initialize a new ocean
        ocean = new Ocean();
        // when no ships are places, all blocks are empty sea
        for (int row = 0; row < Ocean.OCEAN_SIZE; ++row) {
            for (int col = 0; col < Ocean.OCEAN_SIZE; ++ col) {
                assertFalse(ocean.isOccupied(row, col));
            }
        }
    }

    @Test
    public void testShootAtEmptySea() {
        ocean = new Ocean();
        assertFalse("Shooting at an empty sea should return false",
                     ocean.shootAt(9, 9));
    }

    @Test
    public void testShootOutOfBounds() {
        ocean = new Ocean();
        assertFalse("Shooting outside the grid should return false",
                     ocean.shootAt(10, 10));
    }

    @Test
    public void testShootAtSameLocation() {
        ocean = new Ocean();
        ocean.shootAt(0, 0); // First shot
        assertFalse("Shooting at the same location should return false the second time",
                     ocean.shootAt(0, 0));
    }

    @Test
    public void testIsGameOver() {
        ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        // shoot every block of the ocean
        for (int row = 0; row < Ocean.OCEAN_SIZE; ++row) {
            for (int col = 0; col < Ocean.OCEAN_SIZE; ++ col) {
                ocean.shootAt(row, col);
            }
        }
        assertTrue(ocean.isGameOver());
    }
}
