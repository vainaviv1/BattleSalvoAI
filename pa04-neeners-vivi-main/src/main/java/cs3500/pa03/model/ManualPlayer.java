package cs3500.pa03.model;

import cs3500.pa03.view.BattleSalvoView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a Manual Console Player for a Battleship game.
 */
public class ManualPlayer implements Player {
  private String name;
  private Board gameBoard;
  private ArrayList<Coord> hits;
  private ArrayList<Coord> damage;
  private ArrayList<Coord> shotsTaken;
  private int height;
  private int width;
  private Random rand;
  private Scanner scanner;
  private Appendable appendable;

  /**
   * Represents a constructor for a Manual Player.
   *
   * @param name The name of the player.
   * @param rand A random to pass to the game board.
   * @param scanner A scanner to take in input from user.
   * @param appendable An appendable to output to.
   */
  public ManualPlayer(String name, Random rand, Scanner scanner, Appendable appendable) {
    this.name = name;
    this.hits = new ArrayList<>();
    this.damage = new ArrayList<>();
    this.shotsTaken = new ArrayList<>();
    this.rand = rand;
    this.scanner = scanner;
    this.appendable = appendable;
  }

  /**
   * Gets the name of the Manual Player.
   *
   * @return the name of the Manual Player.
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * Given the specifications for a BattleSalvo board, initializes and creates the board for the
   * Manual Player. Return a list of ships with their locations on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the list of ships on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.height = height;
    this.width = width;
    gameBoard = new Board(height, width, rand);
    gameBoard.placeShips(specifications);
    return gameBoard.getShips();
  }

  /**
   * Prompts the user to enter a salvo. Takes in valid shots from the console for the player to
   * take. Creates as many shots as either the number of spaces remaining on the opponent's board or
   * the number of unsunk ships (whichever is less).
   *
   * @return the list of shots for the Manual Player to take.
   */
  @Override
  public List<Coord> takeShots() {
    int spacesRemaining = (height * width) - shotsTaken.size();
    int numberShots = Math.min(spacesRemaining, gameBoard.getNumUnsunkShips());
    new BattleSalvoView(appendable).printMessage("Please Enter " + numberShots + " Shots:");
    List<Coord> coords = new ArrayList<>();
    try {
      for (int i = 0; i < numberShots; i++) {
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s+");
        int[] coordinates = new int[2];
        if (numbers.length == 2) {
          coordinates[0] = Integer.parseInt(numbers[0]);
          coordinates[1] = Integer.parseInt(numbers[1]);
          if (coordinates[0] >= 0 && coordinates[1] >= 0 && coordinates[0] < width
              && coordinates[1] < height
              && !coords.contains(new Coord(coordinates[0], coordinates[1]))
              && !shotsTaken.contains(new Coord(coordinates[0], coordinates[1]))) {
            coords.add(new Coord(coordinates[0], coordinates[1]));
          } else {
            return respondToInvalidShots();
          }
        } else {
          return respondToInvalidShots();
        }
      }
      shotsTaken.addAll(coords);
      return coords;
    } catch (NumberFormatException e) {
      return respondToInvalidShots();
    }
  }

  /**
   * Notifies the user that they entered an invalid shot and gets a new salvo from the user.
   *
   * @return The new salvo from the user.
   */
  private List<Coord> respondToInvalidShots() {
    new BattleSalvoView(appendable).printMessage("Uh Oh! You've entered an invalid salvo.");
    return takeShots();
  }

  /**
   * Creates a list of the shots taken by the opponent that hit a ship on this player's board.
   * Tracks it in the player's list of damage and updates the sink field of the ships on the game
   * board based on damage.
   *
   * @param opponentShotsOnBoard the opponent's shots taken on this player's board
   * @return the list of shots taken by the opponent that hit a ship on this player's board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hits = gameBoard.takeShots(opponentShotsOnBoard);
    damage.addAll(hits);
    gameBoard.updateShips(damage);
    return hits;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    hits.addAll(shotsThatHitOpponentShips);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    return;
  }

  /**
   * Gets this player's game board as a string.
   *
   * @return the game board of the player as a string.
   */
  @Override
  public String toString() {
    return gameBoard.toString();
  }
}