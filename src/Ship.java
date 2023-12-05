import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*********************************************************************************************
                                    Ship (Super)
 *********************************************************************************************/

public abstract class Ship {
    private final ArrayList<ShipBlock> blocks;
    private final boolean horizontal;
    private final int length;

    public Ship(int length, boolean horizontal) {
        this.length = length;
        this.horizontal = horizontal;
        blocks = new ArrayList<>(length);
    }

    // Sets the location and orientation of the ship
    public void setPosition(int bowRow, int bowColumn) {
        blocks.clear();
        for (int i = 0; i < length; i++) {
            int row = this.horizontal ? bowRow : bowRow + i;
            int column = this.horizontal ? bowColumn + i : bowColumn;
            blocks.add(new ShipBlock(row, column));
        }
    }

    // Check if the ship is hit at the specified location
    public boolean isHit(int row, int column) {
        for (ShipBlock block : blocks) {
            if (block.getRow() == row && block.getColumn() == column) {
                return block.isHit();
            }
        }
        return false;
    }

    // Set the hit status of the ship block at the specified location
    public void setHit(int row, int column) {
        for (ShipBlock block : blocks) {
            if (block.getRow() == row && block.getColumn() == column) {
                block.setHit();
                break;
            }
        }
    }

    // Check if the ship is sunk
    public boolean isSunk() {
        for (ShipBlock block : blocks) {
            if (!block.isHit()) {
                return false;
            }
        }
        return true;
    }

    // Abstract method to get the type of the ship
    public abstract String getShipType();

    public List<ShipBlock> getBlocks() {
        return blocks;
    }


    // Inner class ShipBlock
    public static class ShipBlock {
        private final int row;
        private final int column;
        private boolean hit;

        public ShipBlock(int row, int column) {
            this.row = row;
            this.column = column;
            this.hit = false;
        }

        public int getRow() { return row; }
        public int getColumn() { return column; }
        public boolean isHit() { return hit; }
        public void setHit() { this.hit = true; }
    }
}


/*********************************************************************************************
                                Various Ships (Subclasses)
 *********************************************************************************************/

class Battleship extends Ship {
    public Battleship() {
        super(4, new Random().nextBoolean());
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }
}

/*********************************************************************************************/
class Cruiser extends Ship {
    public Cruiser() {
        super(3, new Random().nextBoolean());
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}

/*********************************************************************************************/
class Destroyer extends Ship {
    public Destroyer() {
        super(2, new Random().nextBoolean());
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }
}

/*********************************************************************************************/
class Submarine extends Ship {
    public Submarine() {
        super(1, false); // Orientation doesn't matter
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}

/*********************************************************************************************/
class EmptySea extends Ship {
    public EmptySea() {
        super(1, false); // Orientation doesn't matter
    }

    @Override
    public String getShipType() {
        return "EmptySea";
    }

    @Override
    public boolean isSunk() {
        return false; // EmptySea is never sunk
    }
}



