package cs3500.pa03.model;

import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a Model for a BattleSalvo game.
 */
public class BattleSalvoModel implements Model {
  private Player player1;
  private Player player2;
  private int height;
  private int width;

  /**
   * Represents a constructor for a BattleSalvoModel.
   *
   * @param player1 the first player in the game.
   * @param player2 the second player in the game.
   */
  public BattleSalvoModel(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  /**
   * Creates the board for each player according the given height and width dimensions and fleet
   * specifications.
   *
   * @param height the height of the board to be created
   * @param width the width of the board to be created
   * @param fleetSpecifications a list of how many of each ship to put on the board.
   */
  public void createBoards(int height, int width, ArrayList<Integer> fleetSpecifications) {
    this.height = height;
    this.width = width;
    Map<ShipType, Integer> fleetMap = createFleetMap(fleetSpecifications);
    player1.setup(height, width, fleetMap);
    player2.setup(height, width, fleetMap);
  }

  /**
   * Creates a map of shiptypes to the number of each shiptype based on the given fleet
   * specifications.
   *
   * @param fleetSpecifications a list of how many of each ship to put on the board.
   * @return A map of shiptypes associated with how many of that ship type.
   */
  private Map<ShipType, Integer> createFleetMap(ArrayList<Integer> fleetSpecifications) {
    Map<ShipType, Integer> fleetMap = new TreeMap<>();
    fleetMap.put(ShipType.CARRIER, fleetSpecifications.get(0));
    fleetMap.put(ShipType.BATTLESHIP, fleetSpecifications.get(1));
    fleetMap.put(ShipType.DESTROYER, fleetSpecifications.get(2));
    fleetMap.put(ShipType.SUBMARINE, fleetSpecifications.get(3));
    return fleetMap;
  }

  /**
   * Gets player 1's board as a 2d array list of strings.
   *
   * @return player 1's board as a 2d array list
   */
  public String[][] getP1Board() {
    String boardString = player1.toString();
    return getBoard(boardString);
  }

  /**
   * Gets the given board as all 0's so no ship placement information is displayed.
   *
   * @param opponentBoard the board to hide ship information.
   * @return the opponents board with ship placements hidden.
   */
  public String[][] displayOpponentBoard(String[][] opponentBoard) {
    String[][] board = new String[opponentBoard.length][opponentBoard[0].length];
    for (int i = 0; i < opponentBoard.length; i++) {
      for (int j = 0; j < opponentBoard[i].length; j++) {
        if (opponentBoard[i][j].equals("S") || opponentBoard[i][j].equals("C")
            || opponentBoard[i][j].equals("B") || opponentBoard[i][j].equals("D")) {
          board[i][j] = "0";
        } else {
          board[i][j] = opponentBoard[i][j];
        }
      }
    }
    return board;
  }

  /**
   * Changes all specification ship information to X's so information about each ships' shiptype
   * is hidden.
   *
   * @param opponentBoard the board to hide shiptype information.
   * @return the opponents board with shiptype information hidden.
   */
  public String[][] displayPlayerBoard(String[][] opponentBoard) {
    String[][] board = new String[opponentBoard.length][opponentBoard[0].length];
    for (int i = 0; i < opponentBoard.length; i++) {
      for (int j = 0; j < opponentBoard[i].length; j++) {
        if (opponentBoard[i][j].equals("C") || opponentBoard[i][j].equals("B")
            || opponentBoard[i][j].equals("D") || opponentBoard[i][j].equals("S")) {
          board[i][j] = "X";
        } else {
          board[i][j] = opponentBoard[i][j];
        }
      }
    }
    return board;
  }

  /**
   * Gets player 2's board as a 2d array list of strings.
   *
   * @return player 2's board as a 2d array list
   */
  public String[][] getP2Board() {
    String boardString = player2.toString();
    return getBoard(boardString);
  }

  /**
   * Returns the given string representation of a board as a 2d array of strings.
   *
   * @param boardString the string representation of a board
   * @return the 2d array representation of the given board.
   */
  public String[][] getBoard(String boardString) {
    String[][] board =  new String[height][width];
    int index = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = boardString.substring(index, index + 1);
        index++;
      }
    }
    return board;
  }

  /**
   * Reports to each player the successful hits from their last salvo and reports to each player
   * the damage from the opponent's last salvo.
   */
  public void updateBoards() {
    List<Coord> player2Shots = player2.takeShots();
    player2.successfulHits(player1.reportDamage(player2Shots));
    List<Coord> player1Shots = player1.takeShots();
    player1.successfulHits(player2.reportDamage(player1Shots));
  }

  /**
   * Checks whether the game is over based on whether the ships on player 1 or player 2's board are
   * all hit.
   *
   * @return whether the game is over or not.
   */
  public boolean isGameOver() {
    String[][] player1Board = displayPlayerBoard(getP1Board());
    String[][] player2Board = displayPlayerBoard(getP2Board());

    boolean gameOver1 = true;
    boolean gameOver2 = true;
    for (int i = 0; i < player1Board.length; i++) {
      for (int j = 0; j < player1Board[0].length; j++) {
        if (player1Board[i][j].equals("X")) {
          gameOver1 = false;
        }
        if (player2Board[i][j].equals("X")) {
          gameOver2 = false;
        }
      }
    }
    return gameOver1 || gameOver2;
  }

  /**
   * Gets the winner of the game by returning their name by checking each player's board.
   *
   * @return the name of the winner.
   */
  public String getWinner() {
    String[][] player1Board = displayPlayerBoard(getP1Board());
    String[][] player2Board = displayPlayerBoard(getP2Board());

    boolean gameOver1 = true;
    boolean gameOver2 = true;
    for (int i = 0; i < player1Board.length; i++) {
      for (int j = 0; j < player1Board[0].length; j++) {
        if (player1Board[i][j].equals("X")) {
          gameOver1 = false;
        }
        if (player2Board[i][j].equals("X")) {
          gameOver2 = false;
        }
      }
    }
    if (gameOver1 && gameOver2) {
      return "It was a tie. Nobody";
    } else if (gameOver1) {
      return player2.name();
    } else if (gameOver2) {
      return player1.name();
    } else {
      return "Nobody";
    }
  }
}
