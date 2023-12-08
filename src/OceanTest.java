import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OceanTest {
    private Ocean testOcean;

    @BeforeEach
    public void setUp() {
        testOcean = new Ocean();
    }

    @Test
    public void testPlaceAllShipsRandomly() {
        testOcean = new Ocean();
        testOcean.placeAllShipsRandomly();
        int shipsCount = 0;
        for (int i = 0; i < Ocean.OCEAN_SIZE; i++) {
            for (int j = 0; j < Ocean.OCEAN_SIZE; j++) {
                if (!(testOcean.getShipArray()[i][j] instanceof EmptySea)) {
                    shipsCount++;
                }
            }
        }
        assertEquals(20, shipsCount); // 20 ship blocks in total (= 4*1 + 3*2 + 2*3 + 1*4)
    }

    @Test
    public void testIsOccupied0() {
        // when no ships are places, all blocks are empty sea
        for (int row = 0; row < 10; ++row) {
            for (int col = 0; col < 10; ++ col) {
                assertFalse(testOcean.isOccupied(row, col));
            }
        }
    }

    @Test
    public void testIsOccupied1() {
        // create a submarine and place it at (0, 0)
        Ship ship = new Submarine();
        ship.placeShipAt(5, 5, ship.isHorizontal(), this.testOcean);
        assertTrue(testOcean.isOccupied(5, 5));
    }

    @Test
    public void testIsOccupied2() {
        Ship ship = new Cruiser();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        assertTrue(testOcean.isOccupied(0, 2));
    }

    @Test
    public void testIsGameOver() {
        assertFalse(testOcean.isGameOver());
    }


    @Test
    public void testShootAt0() {
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        testOcean.shootAt(0 ,0);
        assertTrue(ship.isSunk());
    }

    @Test
    public void testShootAt1() {
        Ship ship = new Cruiser();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        testOcean.shootAt(0 ,0);
        testOcean.shootAt(0 ,2);
        boolean[] expected_hit = new boolean[]{true, false, true};
        assertArrayEquals(expected_hit, ship.getHit());
    }

    @Test
    public void testGetFinalScore0() {
        testOcean.placeAllShipsRandomly();
        while (!testOcean.isGameOver()) {
            for (int row = 0; row < 10; ++row) {
                for (int col = 0; col < 10; ++col) {
                    testOcean.shootAt(row, col);
                }
            }
        }
        int finalScore = testOcean.getFinalScore();
        assertTrue(20 <= finalScore && finalScore <= 100);
    }

    @Test
    public void testGetFinalScore1() {
        testOcean.placeAllShipsRandomly();
        while (!testOcean.isGameOver()) {
            for (int row = 0; row < 10; ++row) {
                for (int col = 0; col < 10; ++col) {
                    testOcean.shootAt(row, col);
                }
            }
        }
        int finalScore = testOcean.getFinalScore();
        assertTrue(20 <= finalScore && finalScore <= 100);
    }

    @Test
    public void testGetFinalScore2() {
        testOcean.placeAllShipsRandomly();
        while (!testOcean.isGameOver()) {
            for (int row = 0; row < 10; ++row) {
                for (int col = 0; col < 10; ++col) {
                    testOcean.shootAt(row, col);
                }
            }
        }
        int finalScore = testOcean.getFinalScore();
        assertTrue(20 <= finalScore && finalScore <= 100);
    }

    @Test
    public void testGetShipArray0() {
        Ship ship = new Battleship();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);

        Ship[][] shipArray = testOcean.getShipArray();
        assertTrue(shipArray[0][0] instanceof Battleship);
        assertTrue(shipArray[0][1] instanceof Battleship);
        assertTrue(shipArray[0][2] instanceof Battleship);
        assertTrue(shipArray[0][3] instanceof Battleship);
        assertTrue(shipArray[0][4] instanceof EmptySea);
        assertTrue(shipArray[1][1] instanceof EmptySea);
    }

    @Test
    public void testGetShipArray1() {
        Ship ship = new Battleship();
        ship.setHorizontal(false);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);

        Ship[][] shipArray = testOcean.getShipArray();
        assertTrue(shipArray[0][0] instanceof Battleship);
        assertTrue(shipArray[1][0] instanceof Battleship);
        assertTrue(shipArray[2][0] instanceof Battleship);
        assertTrue(shipArray[3][0] instanceof Battleship);
        assertTrue(shipArray[4][0] instanceof EmptySea);
        assertTrue(shipArray[1][1] instanceof EmptySea);
    }

    @Test
    public void testGetShotsFired0() {
        int expected_shots = 100;
        for (int i = 0; i < expected_shots; ++i) {
            testOcean.shootAt(0, 0);
        }
        assertEquals(expected_shots, testOcean.getShotsFired());
    }

    @Test
    public void testGetShotsFired1() {
        int expected_shots = 18;
        for (int i = 0; i < expected_shots; ++i) {
            testOcean.shootAt(0, 0);
        }
        assertEquals(expected_shots, testOcean.getShotsFired());
    }

    @Test
    public void testGetHitCount0() {
        Ship ship = new Battleship();
        ship.placeShipAt(0, 0, ship.isHorizontal(), testOcean);
        int expected_hit = 100;
        for (int i = 0; i < expected_hit; ++i) {
            testOcean.shootAt(0, 0);
        }
        assertEquals(expected_hit, testOcean.getHitCount());
    }

    @Test
    public void testGetHitCount1() {
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), testOcean);
        int shotsFired = 100;
        int expected_hit = 1;
        for (int i = 0; i < shotsFired; ++i) {
            testOcean.shootAt(0, 0);
        }
        assertEquals(expected_hit, testOcean.getHitCount());
    }

    @Test
    public void testGetShipSunk0() {
        Ship ship0 = new Submarine();
        ship0.placeShipAt(0, 0, ship0.isHorizontal(), testOcean);
        Ship ship1 = new Submarine();
        ship1.placeShipAt(1, 1, ship1.isHorizontal(), testOcean);
        Ship ship2 = new Submarine();
        ship2.placeShipAt(2, 2, ship2.isHorizontal(), testOcean);

        testOcean.shootAt(0, 0);
        testOcean.shootAt(1, 1);
        testOcean.shootAt(2, 2);

        int expected_sunk = 3;
        assertEquals(expected_sunk, testOcean.getShipsSunk());
    }

    @Test
    public void testGetShipSunk1() {
        Ship ship = new Cruiser();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), testOcean);

        testOcean.shootAt(0, 0);
        testOcean.shootAt(0, 1);
        testOcean.shootAt(0, 2);

        int expected_sunk = 1;
        assertEquals(expected_sunk, testOcean.getShipsSunk());
    }

}
