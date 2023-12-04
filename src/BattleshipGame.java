import java.util.Scanner;

/* The Main Method */
public class BattleshipGame {

    private Ocean gameOcean;
    private Scanner scanner;

    public static void main(String[] args) throws InterruptedException {

        BattleshipGame game = new BattleshipGame();
        game.setupGame();

        // Introduce a delay for game to set up
        Thread.sleep(1000); // Wait for 1 second

        // Run a loop for the game process (accepting shots, displaying results, etc.).
        while (!game.gameOcean.isGameOver()) {
            game.displayGameBoard();
            game.acceptUserShot();
            game.displayResults();
        }
        game.printFinalScores();

        // Ask the player if they want to replay after the game ends.
        if (game.askForReplay()) {
            main(args); // Restart the game
        }
    }


    /**
     *  setupGame(): Set up the game board and ships.
     * */
    private void setupGame() {
        gameOcean = new Ocean();
        gameOcean.placeAllShipsRandomly();
        scanner = new Scanner(System.in);
    }

    /**
     * acceptUserShot(): Accept coordinates from the user for their shot.
     * */
    private void acceptUserShot() {
        System.out.println("Enter row and column to shoot (e.g., 3 5): ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
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
     * printFinalScores(): Print the final scores after the game ends.
     * */
    private void printFinalScores() {
        System.out.println("Game Over!");
        System.out.println("Final Scores:");
        System.out.println("Shots Fired: " + gameOcean.getShotsFired());
        System.out.println("Hit Count: " + gameOcean.getHitCount());
        System.out.println("Ships Sunk: " + gameOcean.getShipsSunk());
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
