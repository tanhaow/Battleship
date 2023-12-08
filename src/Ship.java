import java.util.Arrays;
import java.util.Random;


/**
 * Ship is the abstract class for all of the ships and sea tiles that will make up the game of Battleship.
 * Ships of all kinds are always considered to be facing up or to the left, meaning that any portion of the
 * ship that is not the bow will be at a higher numbered row or column than the bow.
 */
public abstract class Ship {

    // Field Summary
    protected int bowRow;        // The row (0 to 9) which contains the bow (front) of the ship.
    protected int bowColumn;     // The column (0 to 9) which contains the bow (front) of the ship.
    protected boolean horizontal;// true if the ship occupies a single row, false otherwise.
    protected int length;        // The number of tiles occupied by the ship.
    protected boolean[] hit;     // An array of four booleans telling whether that part of the ship has been hit.

    // Helper methods

    // Returns the length of the ship
    public int getLength() {
        return length;
    }

    // Returns the row of the bow (front) of the ship
    public int getBowRow() {
        return bowRow;
    }

    // Returns the column of the bow (front) of the ship
    public int getBowColumn() {
        return bowColumn;
    }

    // Sets the bow column
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    // Returns true if this boat is horizontal (facing left), false otherwise
    public boolean isHorizontal() {
        return horizontal;
    }

    // Sets the horizontal orientation of the ship
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    // Sets the bow row
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public boolean[] getHit() {
        return hit;
    }

    // Abstract method to get the ship type
    public abstract String getShipType();

    // Determines whether it is valid to place this ship at the specified location
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            if (column + this.length > Ocean.OCEAN_SIZE) return false; // Check horizontal boundary
        } else {
            if (row + this.length > Ocean.OCEAN_SIZE) return false; // Check vertical boundary
        }

        for (int i = 0; i < this.length; i++) {
            int targetRow = horizontal ? row : row + i;
            int targetColumn = horizontal ? column + i : column;

            // Check the cell itself and all surrounding cells
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int checkRow = targetRow + dr;
                    int checkColumn = targetColumn + dc;

                    // Check if the coordinates are within the ocean boundaries
                    if (checkRow >= 0 && checkRow < 10 && checkColumn >= 0 && checkColumn < 10) {
                        if (ocean.isOccupied(checkRow, checkColumn)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    // Places the ship at the specified location
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Set the ship's position and orientation
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        // Iterate over the length of the ship to place it in the ocean
        for (int i = 0; i < this.length; i++) {
            int currentRow = horizontal ? row : row + i;
            int currentColumn = horizontal ? column + i : column;

            // Update the ocean's ships array to include a reference to this ship
            ocean.getShipArray()[currentRow][currentColumn] = this;
        }
    }


    // If a part of this ship occupies the given coordinates, mark it as "hit"
    public boolean shootAt(int row, int column) {

        // Check if it is already sunk
        if (isSunk()) {
            return false;
        }

        // Check if the ship is an EmptySea
        if (this instanceof EmptySea) {
            this.hit[0] = true;
            return false;
        }

        // calculate the relative position of the shot: to identify which specific part of the ship got hit
        int delta = horizontal ? column - this.bowColumn : row - this.bowRow;

        // If the shot is on the ship , mark it as hit and return true
        if (delta >= 0 && delta < this.length) {
            this.hit[delta] = true;
            return true;
        }
        return false;
    }

    // Returns true if every part of the ship has been hit, false otherwise
    public boolean isSunk() {
        for (boolean partHit : hit) {
            if (!partHit) return false; // if still exist non-hit part, means the ship is not sunk
        }
        return true;
    }

    // Returns a single character String to use in the Ocean's print method
    public String toString() {
        return isSunk() ? "x" : "S";
    }
}


/*********************************************************************************************
                            Various Ships (Subclasses)
 *********************************************************************************************/

class Battleship extends Ship {
    public Battleship() {
        this.length = 4;
        this.hit = new boolean[this.length];
        Arrays.fill(this.hit, false);
        setHorizontal(new Random().nextBoolean());
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }
}

/*********************************************************************************************/
class Cruiser extends Ship {
    public Cruiser() {
        this.length = 3;
        this.hit = new boolean[this.length];
        Arrays.fill(this.hit, false);
        setHorizontal(new Random().nextBoolean());
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}

/*********************************************************************************************/
class Destroyer extends Ship {
    public Destroyer() {
        this.length = 2;
        this.hit = new boolean[this.length];
        Arrays.fill(this.hit, false);
        setHorizontal(new Random().nextBoolean());
    }


    @Override
    public String getShipType() {
        return "Destroyer";
    }
}

/*********************************************************************************************/
class Submarine extends Ship {
    public Submarine() {
        this.length = 1;
        this.hit = new boolean[this.length];
        Arrays.fill(this.hit, false);
        setHorizontal(false); // Orientation doesn't matter
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}

/*********************************************************************************************/
class EmptySea extends Ship {
    public EmptySea() {
        this.length = 1;
        this.hit = new boolean[this.length];
        Arrays.fill(this.hit, false);
        setHorizontal(false); // Orientation doesn't matter
    }

    @Override
    public String getShipType() {
        return "EmptySea";
    }

//    @Override
//    public boolean isSunk() {
//        return false; // EmptySea is never sunk
//    }
}

