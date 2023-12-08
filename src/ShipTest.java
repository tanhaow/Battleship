import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class ShipTest {
    private Ocean testOcean;

    @BeforeEach
    public void setup() {
        testOcean = new Ocean();
    }


    @Test
    public void testOkToPlaceShipAt0() {
        // create a Battleship and place it at (0, 0)
        Ship ship = new Battleship();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        // creat another Battleship and try place it at the same location
        Ship ship2 = new Battleship();
        boolean okToPlace = ship2.okToPlaceShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        // should be false
        assertFalse(okToPlace);
    }

    @Test
    public void testOkToPlaceShipAt1() {
        // create a submarine and place it at (0, 0)
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        // creat another submarine and try place it next to the first submarine
        Ship ship2 = new Submarine();
        boolean okToPlace = ship2.okToPlaceShipAt(0, 1, ship.isHorizontal(), this.testOcean);
        // should be false
        assertFalse(okToPlace);
    }

    @Test
    public void testOkToPlaceShipAt2() {
        // create a submarine and place it at (0, 0)
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        // creat another submarine and try place it at the diagonal corner of the first submarine
        Ship ship2 = new Submarine();
        boolean okToPlace = ship2.okToPlaceShipAt(1, 1, ship.isHorizontal(), this.testOcean);
        // should be false
        assertFalse(okToPlace);
    }

    @Test
    public void testPlaceShipAt0() {
        // create a Battleship and place it at (0, 0)
        Ship ship = new Battleship();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        for (int i = 0; i < ship.length; ++i) {
            int targetRow = ship.isHorizontal() ? 0 : i;
            int targetCol = ship.isHorizontal() ? i : 0;
            assertTrue(testOcean.isOccupied(targetRow, targetCol));
        }
    }

    @Test
    public void testPlaceShipAt1() {
        // create a Battleship and place it at (5, 5)
        Ship ship = new Battleship();
        ship.placeShipAt(5, 5, ship.isHorizontal(), this.testOcean);
        for (int i = 0; i < ship.length; ++i) {
            int targetRow = ship.isHorizontal() ? 5 : 5 + i;
            int targetCol = ship.isHorizontal() ? 5 + i : 5;
            assertTrue(testOcean.isOccupied(targetRow, targetCol));
        }
    }


    @Test
    public void testShootAt0() {
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(0 ,0);
        assertTrue(ship.isSunk());
    }

    @Test
    public void testShootAt1() {
        Ship ship = new Cruiser();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(0 ,0);
        ship.shootAt(0 ,2);
        boolean[] expected_hit = new boolean[]{true, false, true};
        assertArrayEquals(expected_hit, ship.getHit());
    }


    @Test
    public void testIsSunk0() {
        Ship ship = new Destroyer();
        ship.setHorizontal(true);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(0 ,0);
        ship.shootAt(0 ,1);
        assertTrue(ship.isSunk());
    }

    @Test
    public void testIsSunk1() {
        Ship ship = new Destroyer();
        ship.setHorizontal(false);
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(1 ,0);
        assertFalse(ship.isSunk());
    }


    @Test
    public void testToString0() {
        Ship ship = new Submarine();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(0, 0);
        assertEquals("x", ship.toString());
    }

    @Test
    public void testToString1() {
        Ship ship = new Battleship();
        ship.placeShipAt(0, 0, ship.isHorizontal(), this.testOcean);
        ship.shootAt(0, 0);
        assertEquals("S", ship.toString());
    }


    @Test
    public void testGetShipType0() {
        Ship ship = new Battleship();
        assertEquals(ship.getShipType(), "Battleship");
    }

    @Test
    public void testGetShipType1() {
        Ship ship = new Cruiser();
        assertEquals(ship.getShipType(), "Cruiser");
    }

    @Test
    public void testGetShipType2() {
        Ship ship = new Destroyer();
        assertEquals(ship.getShipType(), "Destroyer");
    }

    @Test
    public void testGetShipType3() {
        Ship ship = new Submarine();
        assertEquals(ship.getShipType(), "Submarine");
    }
}
