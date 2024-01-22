package cs3500.pa03.controller;

import cs3500.pa03.model.BattleSalvoModel;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.BattleSalvoView;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a controller for the BattleSalvo game.
 */
public class BattleSalvoController implements Controller {
  private BattleSalvoModel bsm;
  private BattleSalvoView bsv;
  private Scanner scanner;

  /**
   * Represents a constructor for a BattleSalvoController.
   *
   * @param scanner A scanner to take in input from user.
   * @param appendable An appendable to output to.
   * @param player1 the first player in the game.
   * @param player2 the second player in the game.
   */
  public BattleSalvoController(Scanner scanner, Appendable appendable, Player player1,
                               Player player2) {
    bsm = new BattleSalvoModel(player1, player2);
    bsv = new BattleSalvoView(appendable);
    this.scanner = scanner;
  }

  /**
   * Starts the game by telling the view to print the welcome message and board dimension prompts.
   * Gets the board dimensions and fleet specifications from the user and tells the model to create
   * the boards with the dimensions and fleet specifications. Displays the initial boards and calls
   * playGame() to enter the game.
   */
  public void startGame() {
    bsv.printMessage("Hello! Welcome to the OOD BattleSalvo Game!");
    bsv.printMessage("Please enter a valid height and width below:");
    int[] dimensions = getBoardDimensions();
    int height = dimensions[0];
    int width = dimensions[1];
    ArrayList<Integer> fleetSpecifications = getFleetSpecifications(Math.min(height, width));
    bsm.createBoards(height, width, fleetSpecifications);
    displayInitialBoards();
    playGame();
  }

  /**
   * Gets the board dimensions from the user. Ensures that the user enters valid board dimensions
   * (2 positive numbers that are within [6, 15]).
   *
   * @return the dimensions of the board as an array.
   */
  private int[] getBoardDimensions() {
    int[] dimensions = new int[2];
    String input = scanner.nextLine();
    try {
      String[] numbers = input.split("\\s+");
      if (numbers.length == 2) {
        dimensions[0] = Integer.parseInt(numbers[0]);
        dimensions[1] = Integer.parseInt(numbers[1]);
        if (dimensions[0] > 15 || dimensions[0] < 6 || dimensions[1] > 15 || dimensions[1] < 6) {
          return respondToInvalidDimensions();
        } else {
          return dimensions;
        }
      } else {
        return respondToInvalidDimensions();
      }
    } catch (NumberFormatException e) {
      return respondToInvalidDimensions();
    }
  }

  /**
   * Prompts the user to reenter new board dimensions if invalid dimensions are entered. Returns the
   * new correct dimensions.
   *
   * @return the new dimensions from the user.
   */
  private int[] respondToInvalidDimensions() {
    bsv.printMessage("Uh Oh! You've entered invalid dimensions. Please remember that the "
        + "height and width of the game must be in the range [6, 15], inclusive. Try again!");
    return getBoardDimensions();
  }

  /**
   * Prompts the user to enter their fleet choices: the number of each ship they want in the game.
   * Takes in and outputs a valid fleet from the user.
   *
   * @param minDimension the smallest dimension of the board which is the limit on number of boats
   * @return the number of each ship as an array list.
   */
  private ArrayList<Integer> getFleetSpecifications(int minDimension) {
    bsv.printMessage("Please enter your fleet in the order [Carrier, Battleship, Destroyer, "
        + "Submarine].\n"
        + "Remember, your fleet may not exceed size " + minDimension + ".");
    int[] fleetInput = new int[4];
    ArrayList<Integer> fleetSpecifications = new ArrayList<>();
    String input = scanner.nextLine();
    try {
      String[] numbers = input.split("\\s+");
      if (numbers.length == 4) {
        fleetInput[0] = Integer.parseInt(numbers[0]);
        fleetInput[1] = Integer.parseInt(numbers[1]);
        fleetInput[2] = Integer.parseInt(numbers[2]);
        fleetInput[3] = Integer.parseInt(numbers[3]);
        if (((fleetInput[0] + fleetInput[1] + fleetInput[2] + fleetInput[3]) <= minDimension)
            && fleetInput[0] > 0 && fleetInput[1] > 0 && fleetInput[2] > 0 && fleetInput[3] > 0) {
          fleetSpecifications.add(fleetInput[0]);
          fleetSpecifications.add(fleetInput[1]);
          fleetSpecifications.add(fleetInput[2]);
          fleetSpecifications.add(fleetInput[3]);
          return fleetSpecifications;
        } else {
          return respondToInvalidFleet(minDimension);
        }
      } else {
        return respondToInvalidFleet(minDimension);
      }
    } catch (NumberFormatException e) {
      return respondToInvalidFleet(minDimension);
    }
  }

  /**
   * Prompts the user to reenter new fleet specifications if invalid fleet is entered. Returns the
   * new fleet.
   *
   * @param minDimension the smallest dimension of the board which is the limit on number of boats
   * @return the new fleet from the user
   */
  private ArrayList<Integer> respondToInvalidFleet(int minDimension) {
    bsv.printMessage("Uh Oh! You've entered invalid fleet sizes.");
    return getFleetSpecifications(minDimension);
  }

  /**
   * Displays the initial boards before entering the game portion. Displays player 2's board as
   * just zeros and player 1's board with each ship type represented by their corresponding letter.
   */
  private void displayInitialBoards() {
    bsv.printMessage("Opponent Board Data:");
    bsv.printBoard(bsm.displayOpponentBoard(bsm.getP2Board()));
    bsv.printMessage("Your Initial Board:");
    bsv.printBoard(bsm.getP1Board());
  }

  /**
   * Enters the game portion of the program. Prints the opponents board with shots taken so far
   * and the users board with the shots taken on it so far. Updates the board with each round and
   * displays the updated boards after each salvo. Continues or ends the game depending on whether
   * the game is over.
   */
  private void playGame() {
    bsv.printMessage("Opponent Board Data:");
    bsv.printBoard(bsm.displayOpponentBoard(bsm.getP2Board()));
    bsv.printMessage("Your Board:");
    bsv.printBoard(bsm.displayPlayerBoard(bsm.getP1Board()));
    bsm.updateBoards();
    if (!bsm.isGameOver()) {
      playGame();
    } else {
      endGame();
    }
  }

  /**
   * Ends the game and tells the view to print a game end message and the winner of the game.
   */
  private void endGame() {
    bsv.printMessage("The game is over!");
    bsv.printMessage(bsm.getWinner() + " wins!");
  }
}