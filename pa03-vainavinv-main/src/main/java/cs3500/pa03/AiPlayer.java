package cs3500.pa03;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an AI player in the battleship game. The AI player automatically generates and
 * executes moves without user input.
 */
public class AiPlayer implements Player {

  private String name;
  private Board board;
  private ArrayList<Coord> coordinates;
  private ArrayList<Ship> ships;
  private int height;
  private int width;

  /**
   * Constructs an AI player with the specified name.
   *
   * @param name the name of the AI player
   */
  public AiPlayer(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }

  /**
   * Generates random coordinates for placing a ship on the game board.
   *
   * @param height   the height of the game board
   * @param width    the width of the game board
   * @param shipSize the size of the ship
   * @return a list of generated coordinates
   */
  private ArrayList<Coord> generateRandomCoordinates(int height, int width, int shipSize) {
    this.height=height;
    this.width=width;
    Random random = new Random();
    boolean horizontal = random.nextBoolean();
    int x;
    int y;
    if (horizontal) {
      x = random.nextInt(height);
      y = random.nextInt(width - shipSize + 1);
      for (int i = 0; i < shipSize; i++) {
        Coord coord = new Coord(x, (y + i));
        if (i != 0) {
          if (!(coordinates.contains(coord))) {
            coordinates.add(coord);
          } else {
            i--;
          }
        } else {
          coordinates.add(coord);
        }
      }
    } else {
      x = random.nextInt(height - shipSize + 1);
      y = random.nextInt(width);
      for (int i = 0; i < shipSize; i++) {
        Coord coord = new Coord(x + i, y);
        if (i != 0) {
          if (!(coordinates.contains(coord))) {
            coordinates.add(coord);
          } else {
            i--;
          }
        } else {
          coordinates.add(coord);
        }
      }
    }
    return coordinates;
  }

  /**
   * Sets up the AI player's fleet using random ship placements.
   *
   * @param height        the height of the game board
   * @param width         the width of the game board
   * @param specifications the ship type and quantity specifications
   * @return a list of the generated ships
   */
  @Override
  public ArrayList<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.coordinates = new ArrayList<>();
    this.height=height;
    this.width=width;
    this.ships = new ArrayList<>();
    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      ShipType type = entry.getKey();
      int count = entry.getValue();
      for (int i = 0; i < count; i++) {
        coordinates = generateRandomCoordinates(height, width, type.getShipSize());
        Ship ship = new Ship(type, coordinates);
        ships.add(ship);
      }
    }
    return ships;
  }

  /**
   * Analyzes the opponent's shots on the AI player's ships and determines which ships have been hit.
   *
   * @param opponentShotsOnBoard the list of opponent's shots on the AI player's board
   * @return a list of coordinates representing the hits
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitsByPlayer = new ArrayList<>();
    for (int i = 0; i < opponentShotsOnBoard.size(); i++) {
      for (int j = 0; j < coordinates.size(); j++) {
        if ((opponentShotsOnBoard.get(i).getX() == coordinates.get(j).getX()) &&
            (opponentShotsOnBoard.get(i).getX() == coordinates.get(j).getX())) {
          hitsByPlayer.add(opponentShotsOnBoard.get(i));
        }
      }
    }
    return hitsByPlayer;
  }

  /**
   * Generates a list of coordinates representing the shots to be taken by the AI player.
   *
   * @return the list of generated shots
   */
  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> shots= new ArrayList<>();
    Random random = new Random();
    int x;
    int y;
    for (int a = 0; a < ships.size(); a++) {
      x = random.nextInt(this.height);
      y = random.nextInt(this.width);
      Coord shot = new Coord(x, y);
      if (!(shots.contains(shot))) {
        shots.add(shot);
      } else if (a == 0) {
        shots.add(shot);
      }
    }
    return shots;
  }

  /**
   * Updates the AI player's internal state based on the successful hits on the opponent's ships.
   *
   * @param shotsThatHitOpponentShips the list of shots that hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {

  }

  /**
   * Handles the end of the game by performing any necessary cleanup or actions.
   *
   * @param result the game result
   * @param reason the reason for the end of the game
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }


}











