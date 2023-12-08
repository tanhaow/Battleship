import java.util.Scanner;

/* The Main Method */
public class BattleshipGame {

    private final Ocean gameOcean;
    private Scanner scanner;

    public static void main(String[] args) throws InterruptedException {

        BattleshipGame game = new BattleshipGame();

        // Introduce a delay for game to set up
        Thread.sleep(1000); // Wait for 1 second

        // Run a loop for the game process (accepting shots, displaying results, etc.).
        while (!game.isGameOver()) {
            game.displayGameBoard();
            game.acceptUserShot();
            game.displayResults();
        }
        game.printFinalScores();

        if (game.askForReplay()) { // Ask the player if they want to replay after the game ends
            main(args); // Restart the game
        }
    }

    /**
     * Constructor for BattleshipGame().
     * Set up the game board and ships.
     */
    public BattleshipGame() {
        gameOcean = new Ocean();
        gameOcean.placeAllShipsRandomly();
        scanner = new Scanner(System.in);
    }


    /**
     * acceptUserShot(): Accept coordinates from the user for their shot.
     * */
    private void acceptUserShot() {
        int row, column;
        while (true) {
            System.out.println("Enter row and column to shoot (e.g., 3 5): ");
            row = scanner.nextInt();
            column = scanner.nextInt();

            if (row >= 0 && row < 10 && column >= 0 && column < 10) {
                break; // Valid input, break out of the loop
            } else {
                System.out.println("The input row and column are not valid.");
            }
        }
        gameOcean.shootAt(row, column);
    }


    /**
     * displayResults(): Display the result of the shot (hit/miss).
     * */
    private void displayResults() {
        System.out.println("Shots Fired: " + gameOcean.getShotsFired());
        System.out.println("Hit Count: " + gameOcean.getHitCount());
        System.out.println("Ships Sunk: " + gameOcean.getShipsSunk());
    }

    /**
     * isGameOver(): check if the game is over.
     */
    private boolean isGameOver() {
        return gameOcean.isGameOver();
    }



    /**
     * printFinalScores(): Print the final scores after the game ends.
     * */
    private void printFinalScores() {
        System.out.println("------- Game Over -------");
        System.out.println("YOUR FINAL SCORES IS: " + gameOcean.getFinalScore());
    }


    /**
     * askForReplay(): Ask the user if they want to play again
     * and return true or false
     */
    private boolean askForReplay() {
        System.out.println("Do you want to play again? (yes/no):");
        String input = scanner.next();
        return input.equalsIgnoreCase("yes");
    }


    /**
     * displayGameBoard(): Display the current state of the game board.
     */
    private void displayGameBoard() {
        gameOcean.print();
    }

}
