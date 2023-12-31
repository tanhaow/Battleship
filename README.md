# Battleship Game
Github repo link: https://github.com/tanhaow/Battleship.git

## Overview
Battleship is a classic strategy game. This implementation is a text-based version that allows a player to play against the computer in a console application. The game, implemented in Java, features a 10x10 grid where players aim to sink their opponent's fleet of ships.

## Features
- Text-based user interface for gameplay in any Java-enabled console.
- Randomized ship placement at the start of each game.
- Various types of ships including Battleships, Cruisers, Destroyers, and Submarines.
- Tracking of shots fired, hits, and the number of ships sunk.
- Simple and intuitive gameplay suitable for players of all ages.

## How to Play
The game is played on a 10x10 grid. Players take turns firing shots at their opponent's grid to try and sink their fleet of ships. The game continues until all the ships of one player are sunk.

1. **Starting the Game**: Execute `BattleshipGame.java`.
2. **Gameplay**:
   - The game displays a 10x10 grid.
   - Players enter coordinates (row and column) to fire at the opponent's grid.
   - The grid updates with each shot, showing hits, misses, and sunken ships.
3. **Winning the Game**: The game ends when all ships of either player are sunk. The program tracks and displays the number of shots fired, hits, and ships sunk throughout the game.

## Setup and Running

### Running from Command Line
1. **Prerequisites**: Java should be installed on your system.
2. **Compilation**: Navigate to your project's root directory and compile all Java files.
   ```bash
   cd path/to/Battleship
   javac BattleshipGame.java Ship_old.java Ocean.java OceanInterface.java
   ```
3. **Running the Game**: Run `BattleshipGame.java` using the Java command.
   ```bash
   java BattleshipGame
   ```

### Running in IntelliJ IDEA
1. **Open the Project**: Open IntelliJ IDEA and navigate to 'Open', then select the project's root directory.
2. **Build the Project**: Use the 'Build' option in the menu or the build icon to build the project.
3. **Run the Game**: Right-click on `BattleshipGame.java` in the project explorer and select 'Run BattleshipGame.main()'.

## Contact
- Hao Tan: [tanhao@seas.upenn.edu](mailto:tanhao@seas.upenn.edu)
