import java.util.Random;

/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 */
public class Ocean implements OceanInterface {

	// Constants
	public static final int OCEAN_SIZE = 10;

	// Fields
	protected Ship[][] ships; // A 10x10 2D array of Ships
	protected int shotsFired; // The total number of shots fired by the user
	protected int hitCount;   // The number of times a shot hit a ship
	protected int shipsSunk;  // The number of ships totally sunk

	/**
	 * Creates an "empty" ocean, filling every space in the ships array with EmptySea objects.
	 */
	public Ocean() {
		ships = new Ship[OCEAN_SIZE][OCEAN_SIZE];
		for (int row = 0; row < OCEAN_SIZE; row++) {
			for (int column = 0; column < OCEAN_SIZE; column++) {
				ships[row][column] = new EmptySea();
			}
		}
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
		Ship[] shipsToPlace = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(),
				new Destroyer(), new Destroyer(), new Submarine(), new Submarine(),
				new Submarine(), new Submarine()};

		for (Ship ship : shipsToPlace) {
			boolean placed = false;
			while (!placed) {
				int row = random.nextInt(OCEAN_SIZE);
				int column = random.nextInt(OCEAN_SIZE);
				boolean horizontal = random.nextBoolean();

				if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
					ship.placeShipAt(row, column, horizontal, this);
					placed = true;
				}
			}
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



	/** Check if the game is over yet:
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}. */
	public boolean isGameOver() {
		if (getShipsSunk() == 10) return true;
		return false;
	}


	/**
	 * Fires a shot at this coordinate. This will update the number of shots that
	 * have been fired (and potentially the number of hits, as well). If a location
	 * contains a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 *
	 * @param row    the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return {@literal true} if the given location contains an afloat ship (not an
	 *         EmptySea), {@literal false} if it does not.
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		if (ships[row][column].shootAt(row, column)) {
			hitCount++;
			if (ships[row][column].isSunk()) {
				shipsSunk++;
			}
			return true;
		}
		return false;
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

				if (ship instanceof EmptySea) {
					// For EmptySea, print '-' if hit, otherwise '.'
					System.out.print(ship.getHit()[0] ? "-  " : ".  ");
				} else {
					// For other ships, print only if they've been hit
					int delta = ship.isHorizontal() ? column - ship.getBowColumn() : row - ship.getBowRow();
					if (delta >= 0 && delta < ship.getLength() && ship.getHit()[delta]) {
						System.out.print(ship.isSunk() ? "x  " : "S  ");
					} else {
						System.out.print(".  "); // Unhit parts of ships should not be revealed
					}
				}
			}
			System.out.println(); // New line at the end of each row
		}
	}

	/** Calculate the final score. */
	public int getFinalScore() {
		int score = 0;
		for (int row = 0; row < OCEAN_SIZE; ++row) {
			for (int column = 0; column < OCEAN_SIZE; ++column) {
				Ship ship = ships[row][column];
				int delta = ship.isHorizontal() ? column - ship.getBowColumn() : row - ship.getBowRow();
				if (delta >= 0 && delta < ship.getLength() && ship.getHit()[delta]) {
					score++;
				}
			}
		}
		return score;
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
		return this.hitCount;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}
}
