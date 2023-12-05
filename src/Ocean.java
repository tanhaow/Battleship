/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 */
import java.util.Random;
public class Ocean implements OceanInterface {

	/** A 10x10 2D array of Ships, which can be used to quickly determine which ship is in any given location.*/
	static int OCEAN_SIZE = 10;
	protected Ship[][] ships;

	/** The total number of shots fired by the user */
	protected int shotsFired;

	/** The number of times a shot hit a ship. If the user shoots the same part of a ship more than once,
	 * every hit is counted, even though the additional "hits" don't do the user any good. */
	protected int hitCount;

	/** The number of ships totally sunk. */
	protected int shipsSunk;

	/**
	 * Creates an "empty" ocean, filling every space in the ships array with EmptySea objects.
	 * Should also initialize the other instance variables appropriately.
	 */
	public Ocean() {
		// Creates an "empty" ocean
		ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
		// filling every space in the ship array with EmptySea objects
		for (int row = 0; row < OCEAN_SIZE; ++row) {
			for (int column = 0; column < OCEAN_SIZE; ++column) {
				ships[row][column] = new EmptySea(); // Assign EmptySea to each position
				ships[row][column].setPosition(row, column);
			}
		}
		// initialize the other instance variables
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}


	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Larger ships
	 * must be placed before smaller ones to avoid cases where it may be impossible
	 * to place the larger ships.
	 */
	public void placeAllShipsRandomly() {
		Random random = new Random();

		Ship[] shipsToPlace = {
				new Battleship(),	// Largest ship first
				new Cruiser(), new Cruiser(),
				new Destroyer(), new Destroyer(), new Destroyer(),
				new Submarine(), new Submarine(), new Submarine(), new Submarine() };

		for (Ship ship : shipsToPlace) {
			boolean placed = false;
			while (!placed) {
				int row = random.nextInt(OCEAN_SIZE);
				int column = random.nextInt(OCEAN_SIZE);

				ship.setPosition(row, column);
				if (isValidPosition(ship)) {
					placeShip(ship, row, column);
					// FOR TEST ONLY:
					// System.out.printf("%s is placed at: (%d, %d)\n", ship.getShipType(), row, column);
					placed = true;
				}
			}
		}
	}
	private boolean isValidPosition(Ship ship) {
		for (Ship.ShipBlock block : ship.getBlocks()) {
			int row = block.getRow();
			int column = block.getColumn();

			// Check surrounding positions including the position of the block itself
			for (int dRow = -1; dRow <= 1; dRow++) {
				for (int dColumn = -1; dColumn <= 1; dColumn++) {
					int checkRow = row + dRow;
					int checkColumn = column + dColumn;

					// Check if the position is within the grid
					if (checkRow < 0 || checkRow >= OCEAN_SIZE || checkColumn < 0 || checkColumn >= OCEAN_SIZE) {
						return false; // Invalid if outside the grid
					}
					// Check if the position overlaps with an existing ship or is adjacent to one
					if (isOccupied(checkRow, checkColumn)) {
						return false; // Invalid if overlapping or adjacent to another ship
					}
				}
			}
		}
		return true;
	}

	private void placeShip(Ship ship, int row, int column) {
		ship.setPosition(row, column); // Set ship position

		for (Ship.ShipBlock block : ship.getBlocks()) {
			// Place each block of the ship in the ocean grid
			ships[block.getRow()][block.getColumn()] = ship;
		}
	}


	/**
	 * Checks if this coordinate is not empty;
	 * @return {@literal true} if the given location contains a ship (not equals to "EmptySea")
	 *         {@literal false} if the given location does not contain a ship ("EmptySea")
	 */
	public boolean isOccupied(int row, int column) {
		return (!(ships[row][column] instanceof EmptySea));
	}


	/**
	 * Fires a shot at this coordinate. This will update the number and the location of shots that
	 * have been fired (and potentially the number of hits, as well). If a location
	 * contains a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 *
	 * @return {@literal false} if the given location is EmptySea or is a sunk ship
	 * 		   {@literal true} if it does not (also need to update hitCount).
	 */

	public boolean shootAt(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			System.out.println("The input row and column is out of boundary.");
			return false;
		}
		Ship target = ships[row][column];
		++shotsFired;

		if (!target.isHit(row, column)) {
			target.setHit(row, column);
			if (isOccupied(row, column)) {
				++hitCount;
				System.out.println("Hit at (" + row + "," + column + "). Ship: " + target.getShipType());
				if (target.isSunk()) {
					System.out.println("Congrat! You just sunk a " + target.getShipType() + "!");
					++shipsSunk;
				}
				return true;
			} else {
				System.out.println("Missed! The target at (" + row + ", " + column + ") is empty.");
				return false;
			}
		// hit at an already-hit block
		} else {
			System.out.println("The location (" + row + "," + column + ") has been hit before.");
			return false;
		}
	}



	/** Check if the game is over yet:
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}. */
	public boolean isGameOver() {
		for (int row = 0; row < OCEAN_SIZE; ++row) {
			for (int col = 0; col < OCEAN_SIZE; ++col) {
				// if it is a real ship and not sunk, the game is not over
				if (isOccupied(row, col) && !ships[row][col].isSunk()) return false;
			}
		}
		return true;
	}




	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the
	 * left edge of the array, and column numbers should be displayed along the top.
	 * *** This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 */
	public void print() {
		// Print the column numbers along the top
		System.out.print("   "); // Extra space for row numbers
		for (int column = 0; column < OCEAN_SIZE; column++) {
			System.out.printf("%2d ", column);
		}
		System.out.println();

		// Print the ocean grid
		for (int row = 0; row < OCEAN_SIZE; row++) {
			System.out.printf("%2d  ", row); // Row number
			for (int column = 0; column < OCEAN_SIZE; column++) {
				Ship ship = ships[row][column];

				if (ship.isHit(row, column)) {
					// A. if got hit: 3 cases
					// case 1: Empty Sea
					if (!isOccupied(row, column)) {
						System.out.print("-  ");
					// case 2: Hit part of a sunken ship
					} else if (ship.isSunk()) {
						System.out.print("X  ");
					// Hit a ship but the ship is not sunk
					} else {
						System.out.print("S  ");
					}
				// B. if not got hit
				} else {
					System.out.print(".  "); // Not fired upon or a miss
				}
			}
			System.out.println(); // New line at the end of each row
		}
	}


	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instancce variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 *
	 * @return the 10x10 array of ships.
	 */
	public Ship[][] getShipArray() {
		return this.ships;
	}

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.shotsFired;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}
}
