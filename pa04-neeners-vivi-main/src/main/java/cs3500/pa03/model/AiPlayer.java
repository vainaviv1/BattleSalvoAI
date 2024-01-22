package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an AI Player for a Battleship game.
 */
public class AiPlayer implements Player {
  private String name;
  private Board gameBoard;
  private int height;
  private int width;
  private ArrayList<Coord> shotsTaken;
  private ArrayList<Coord> damage;
  private ArrayList<Coord> hits;
  private Random random;

  /**
   * Represents a constructor for an AI Player.
   *
   * @param name The name of the player.
   * @param random A random to create unique shots.
   */
  public AiPlayer(String name, Random random) {
    this.name = name;
    this.shotsTaken = new ArrayList<>();
    this.hits = new ArrayList<>();
    this.damage = new ArrayList<>();
    this.random = random;
  }

  /**
   * Gets the name of the AI Player.
   *
   * @return the name of the AI Player.
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * Given the specifications for a BattleSalvo board, initializes and creates the board for the
   * AI Player. Return a list of ships with their locations on the board.
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
    gameBoard = new Board(height, width, random);
    gameBoard.placeShips(specifications);
    return gameBoard.getShips();
  }

  /**
   * Creates random shots for this AI Player to take. Creates as many shots as either the number of
   * spaces remaining on the opponent's board or the number of unsunk ships (whichever is less).
   *
   * @return the list of shots for the AI player to take
   */
  @Override
  public List<Coord> takeShots() {
    int spacesRemaining = (height * width) - shotsTaken.size();
    int numberShots = Math.min(spacesRemaining, gameBoard.getNumUnsunkShips());

    ArrayList<Coord> shots = new ArrayList<>();
    for (int i = 0; i < numberShots; i++) {
      if (shotsTaken.size() < height * width) {
        Coord c = createUniqueShot(shots);
        shots.add(c);
        shotsTaken.add(c);
      }
    }
    return shots;
  }

  /**
   * Creates a unique shots for this AI Player that has not been taken before.
   *
   * @param shots the list of shots already in this salvo
   * @return a unique shot
   */
  private Coord createUniqueShot(ArrayList<Coord> shots) {
    int x = random.nextInt(this.width);
    int y = random.nextInt(this.height);
    Coord c = new Coord(x, y);
    if (shotsTaken.contains(c) || shots.contains(c)) {
      return createUniqueShot(shots);
    } else {
      return c;
    }
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
