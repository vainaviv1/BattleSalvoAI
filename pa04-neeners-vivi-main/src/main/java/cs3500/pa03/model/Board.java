package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a game board for a BattleSalvo game.
 */
public class Board {
  private List<Ship> ships;
  private String[][] grid;
  private Random random;

  /**
   * Represents a constructor for a board.
   *
   * @param height the height of the board.
   * @param width the width of the board.
   * @param random the random to use to randomly place ships.
   */
  public Board(int height, int width, Random random) {
    this.ships = new ArrayList<>();
    this.grid = new String[height][width];
    this.random = random;
    initializeGrid();
  }

  /**
   * Initializes the grid of the board with 0s.
   */
  private void initializeGrid() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = "0";
      }
    }
  }

  /**
   * Randomly place ships on the board according to the given map of shiptypes and number of each
   * shiptype to include.
   *
   * @param shipCounts a map representing a shiptype and the number of that shiptype to have.
   */
  public void placeShips(Map<ShipType, Integer> shipCounts) {
    for (ShipType shipType : shipCounts.keySet()) {
      int count = shipCounts.get(shipType);
      for (int i = 0; i < count; i++) {
        Ship ship = new Ship(shipType, new ArrayList<>(), false);
        boolean placed = false;
        while (!placed) {
          Coord c = getRandomCoordinate();
          boolean horizontal = random.nextBoolean();
          updateShipCoordinates(ship, c.getX(), c.getY(), horizontal);
          if (canPlaceShip(ship)) {
            placeShipOnGrid(ship);
            placed = true;
          }
        }
      }
    }
  }

  /**
   * Creates a random coordinate.
   *
   * @return a random coordinate.
   */
  private Coord getRandomCoordinate() {
    return new Coord(random.nextInt(grid[0].length), random.nextInt(grid.length));
  }

  /**
   * Updates the given ship's coordinates to the given x and y location so that the ship's
   * coordinates are all together in a row or column.
   *
   * @param ship the ship whose coordinates should be updated.
   * @param x the x position to place the head of the ship
   * @param y the y position to place the head of the ship
   * @param horizontal whether the ship should be placed horizontally or vertically
   */
  private void updateShipCoordinates(Ship ship, int x, int y, boolean horizontal) {
    List<Coord> coordinates = new ArrayList<>();
    for (int i = 0; i < ship.getShipType().getSize(); i++) {
      if (horizontal) {
        coordinates.add(new Coord(x + i, y));
      } else {
        coordinates.add(new Coord(x, y + i));
      }
    }
    ship.updateDirection(horizontal);
    ship.updateCoordinates(coordinates);
  }

  /**
   * Checks whether this ship can be placed based on if there is available space on the board to fit
   * the entire ship.
   *
   * @param ship the ship to check whether it can be placed.
   * @return whether the ship can be placed.
   */
  private boolean canPlaceShip(Ship ship) {
    List<Coord> coordinates = ship.getCoordinates();
    for (Coord coord : coordinates) {
      int x = coord.getX();
      int y = coord.getY();
      if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
        return false;
      } else if (!grid[y][x].equals("0")) {
        return false;
      }
    }
    return true;
  }

  /**
   * Places the ship on the grid by updating the ship's coordinates on the grid to the letter of
   * the ship and adding it to the list of ships.
   *
   * @param ship the ship to place on the grid.
   */
  private void placeShipOnGrid(Ship ship) {
    List<Coord> coordinates = ship.getCoordinates();
    for (Coord coord : coordinates) {
      int x = coord.getX();
      int y = coord.getY();
      grid[y][x] = ship.getShipType().getLetter();
    }
    ships.add(ship);
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, update the board, and
   * report which shots hit a ship on this player's board.
   *
   * @param shots the list of shots the opponent has fired
   * @return the list of shots that hit a ship on this player's board
   */
  public List<Coord> takeShots(List<Coord> shots) {
    List<Coord> hits = new ArrayList<>();
    for (Coord c : shots) {
      if (grid[c.getY()][c.getX()] == "S" || grid[c.getY()][c.getX()] == "D"
          || grid[c.getY()][c.getX()] == "C" || grid[c.getY()][c.getX()] == "B") {
        grid[c.getY()][c.getX()] = "H";
        hits.add(c);
      } else {
        grid[c.getY()][c.getX()] = "M";
      }
    }
    return hits;
  }

  /**
   * Given a list of coordinates that were hits, find the ship at the coordinate and update the ship
   *
   * @param hits the list of coordinates that were hits
   */
  public void updateShips(List<Coord> hits) {
    for (Ship s : ships) {
      s.checkSunk(hits);
    }
  }

  /**
   * Gets the ships on this board.
   *
   * @return the list of ships on this board.
   */
  public List<Ship> getShips() {
    return ships;
  }

  /**
   * Gets the number of ships on this board that are not sunk.
   *
   * @return the number of unsunk ships.
   */
  public int getNumUnsunkShips() {
    int count = 0;
    for (Ship s : ships) {
      if (!s.isSunk()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Creates a string representation of this board.
   *
   * @return this board as a string
   */
  @Override
  public String toString() {
    String s = "";
    for (String[] row : grid) {
      for (String cell : row) {
        s += cell;
      }
    }
    return s;
  }
}
