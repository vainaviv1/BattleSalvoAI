package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a console-based (the human) player in the game.
 */
public class ConsolePlayer implements Player{
  private String name;

  ArrayList<Coord> coordinates;
  ArrayList<Coord> hitsByOpponent = new ArrayList<>();

  private int height;
  private int width;
  /**
   * Constructs a console player with the specified name.
   *
   * @param name the name of the player
   */

  public ConsolePlayer(String name) {
    this.name = name;
  }

  /**
   * Retrieves the name of the player.
   *
   * @return the name of the player
   */
  @Override
  public String name() {

    return name;
  }

  /**
   * Generates random player coordinates for placing ships.
   *
   * @param height   the height of the game board
   * @param width    the width of the game board
   * @param shipSize the size of the ship to be placed
   * @return a list of coordinates representing the ship's placement
   */


  private ArrayList<Coord> generateRandomPlayerCoordinates(int height, int width, int shipSize) {
    this.height = height;
    this.width = width;
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
   * Sets up the player's ships on the game board.
   *
   * @param height        the height of the game board
   * @param width         the width of the game board
   * @param specifications the ship specifications
   * @return a list of ships representing the player's setup
   */
  @Override
  public ArrayList<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.coordinates = new ArrayList<>();
    this.height=height;
    this.width=width;
    ArrayList<Ship> ships = new ArrayList<>();
    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      ShipType type = entry.getKey();
      int count = entry.getValue();
      for (int i = 0; i < count; i++) {
          coordinates = generateRandomPlayerCoordinates(height, width, type.getShipSize());
        Ship ship = new Ship(type, coordinates);
        ships.add(ship);
      }
    }
    return ships;
  }

  /**
   * Takes shots from the player.
   *
   * @return a list of coordinates representing the player's shots
   */
  @Override
  public ArrayList<Coord> takeShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    System.out.println("Please enter your shots (x y):");
    Scanner scanner = new Scanner(System.in);
    for (int j=0; j<4; j++) {
      String input = scanner.nextLine();
      String[] coordinates = input.split(" ");
      //for (int i = 0; i < coordinates.length; i += 2) {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        Coord shot = new Coord(x, y);
        if (!(shots.contains(shot))) {
          shots.add(shot);
        } else if (j == 0) {
          shots.add(shot);
        }
    }

      //}
      return shots;
    }
  /**
   * Given the list of shots the opponent has fired on the AiPlayer's board, report which
   * shots hit a ship on the AiPlayer'sboard.
   *
   * @param opponentShotsOnBoard the opponent's shots on the AiPlayer's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   * ship on the AiPlayer's board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    for (int i = 0; i < opponentShotsOnBoard.size(); i++) {
      for (int j = 0; j < coordinates.size(); j++) {
        if ((opponentShotsOnBoard.get(i).getX() == coordinates.get(j).getX()) &&
            (opponentShotsOnBoard.get(i).getX() == coordinates.get(j).getX())) {
          hitsByOpponent.add(opponentShotsOnBoard.get(i));

        }
      }
    }


    return hitsByOpponent;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {

  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }
}