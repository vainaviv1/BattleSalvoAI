package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Model.
 */
public interface Model {
  /**
   * Creates the board for each player according the given height and width dimensions and fleet
   * specifications.
   *
   * @param height the height of the board to be created
   * @param width the width of the board to be created
   * @param fleetSpecifications a list of how many of each ship to put on the board.
   */
  void createBoards(int height, int width, ArrayList<Integer> fleetSpecifications);

  /**
   * Gets player 1's board as a 2d array list of strings.
   *
   * @return player 1's board as a 2d array list
   */
  String[][] getP1Board();

  /**
   * Gets the given board as all 0's so no ship placement information is displayed.
   *
   * @param opponentBoard the board to hide ship information.
   * @return the opponents board with ship placements hidden.
   */
  String[][] displayOpponentBoard(String[][] opponentBoard);

  /**
   * Changes all specification ship information to X's so information about each ships' shiptype
   * is hidden.
   *
   * @param opponentBoard the board to hide shiptype information.
   * @return the opponents board with shiptype information hidden.
   */
  String[][] displayPlayerBoard(String[][] opponentBoard);

  /**
   * Gets player 2's board as a 2d array list of strings.
   *
   * @return player 2's board as a 2d array list
   */
  String[][] getP2Board();

  /**
   * Returns the given string representation of a board as a 2d array of strings.
   *
   * @param boardString the string representation of a board
   * @return the 2d array representation of the given board.
   */
  String[][] getBoard(String boardString);

  /**
   * Reports to each player the successful hits from their last salvo and reports to each player
   * the damage from the opponent's last salvo.
   */
  void updateBoards();

  /**
   * Checks whether the game is over based on whether the ships on player 1 or player 2's board are
   * all hit.
   *
   * @return whether the game is over or not.
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game by returning their name by checking each player's board.
   *
   * @return the name of the winner.
   */
  String getWinner();
}
