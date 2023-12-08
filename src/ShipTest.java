//import static org.junit.Assert.*;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import java.util.ArrayList;
//
//
//public class ShipTest {
//    private Ocean gameOcean;
//
//    @BeforeEach
//    public void setup() {
//        gameOcean = new Ocean();
//    }
//
//    /******************* Test Ship Class ********************/
//    @Test
//    public void testSetPosition() {
//        gameOcean = new Ocean();
//        // initialize and set the position of the test ship
//        Ship ship = new Cruiser();  // length = 3
//        ship.setPosition(0, 0);
//
//        // the cruiser can be placed either horizontally or vertically
//        // if horizontal:
//        if (gameOcean.isOccupied(0, 1)){
//            assertTrue(gameOcean.isOccupied(0, 2));
//        }
//        if (gameOcean.isOccupied(1, 0)){
//            assertTrue(gameOcean.isOccupied(2, 0));
//        }
//    }
//
//
//    @Test
//    public void testIsHit() {
//        // initialize and set the position of the test ship
//        Ship ship = new Battleship();
//        ship.setPosition(0, 0);
//
//        // Test hit on the ship
//        ship.setHit(0, 0);
//        assertTrue(ship.isHit(0, 0));
//
//        // Test hit on a non-existing part
//        assertFalse(ship.isHit(5, 5));
//    }
//
//
//    @Test
//    public void testSetHit() {
//        // initialize and set the position of the test ship
//        Ship ship = new Submarine();
//        ship.setPosition(0, 0);
//
//        // Test hit on the ship
//        ship.setHit(0, 0);
//        assertTrue(ship.isHit(0, 0));
//
//        // Test hit on a non-existing part
//        assertFalse(ship.isHit(1, 1));
//    }
//
//    @Test
//    public void testIsSunk() {
//        Ship ship = new Destroyer(); // length = 2
//        ship.setPosition(0, 0);
//
//        // the destroyer can be placed either horizontally or vertically
//        // so hit evey possible block
//        ship.setHit(0, 0);
//        ship.setHit(0, 1);
//        ship.setHit(1, 0);
//
//        assertTrue(ship.isSunk());
//    }
//
//    @Test
//    public void testGetShipType0() {
//        Ship ship = new Battleship();
//        assertEquals(ship.getShipType(), "Battleship");
//    }
//    @Test
//    public void testGetShipType1() {
//        Ship ship = new Cruiser();
//        assertEquals(ship.getShipType(), "Cruiser");
//    }
//    @Test
//    public void testGetShipType2() {
//        Ship ship = new Destroyer();
//        assertEquals(ship.getShipType(), "Destroyer");
//    }
//    @Test
//    public void testGetShipType3() {
//        Ship ship = new Submarine();
//        assertEquals(ship.getShipType(), "Submarine");
//    }
//
//    @Test
//    public void testGetBlocks() {
//        Ship ship = new Battleship();
//        ArrayList<Ship.ShipBlock> expected_blocks = new ArrayList<>(4); // length = 4
//        ArrayList<Ship.ShipBlock> actual_blocks = (ArrayList<Ship.ShipBlock>) ship.getBlocks();
//        assertEquals(expected_blocks, actual_blocks);
//    }
//}