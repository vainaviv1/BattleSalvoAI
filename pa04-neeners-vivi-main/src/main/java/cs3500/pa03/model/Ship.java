package cs3500.pa03.model;

import java.util.List;

/**
 * Represents a ship in a BattleSalvo game.
 */
public class Ship {
  private ShipType shipType;
  private List<Coord> coordinates;
  private boolean sunk;
  private String direction;

  /**
   * Represents a constructor for a Ship.
   *
   * @param shipType the ShipType of this ship.
   * @param coordinates the coordinates of the location of this ship on the board.
   * @param sunk whether this ship is sunk.
   */
  public Ship(ShipType shipType, List<Coord> coordinates, Boolean sunk) {
    this.shipType = shipType;
    this.coordinates = coordinates;
    this.sunk = sunk;
  }

  /**
   * Gets the coordinates of this ship.
   *
   * @return the list of this ship's coordinates.
   */
  public List<Coord> getCoordinates() {
    return coordinates;
  }

  /**
   * Gets the leftmost or topmost coordinate in the ship's coordinates.
   *
   * @return the first coordinate in the ship's coordinates.
   */
  public Coord getHeadCoordinate() {
    return coordinates.get(0);
  }

  /**
   * Gets the direction of the ship placement.
   *
   * @return The ship's direction as "HORIZONTAL" or "VERTICAL".
   */
  public String getDirection() {
    return this.direction;
  }

  /**
   * Updates the direction field of the ship based on the given boolean that represents if the ship
   * was placed horizontally or not.
   *
   * @param horizontal whether the ship was placed horizontally.
   */
  public void updateDirection(boolean horizontal) {
    if (horizontal) {
      this.direction = "HORIZONTAL";
    } else {
      this.direction = "VERTICAL";
    }
  }

  /**
   * Updates the coordinates of this ship to the given coordinates.
   *
   * @param c the coordinates to update the ship to.
   */
  public void updateCoordinates(List<Coord> c) {
    this.coordinates = c;
  }

  /**
   * Check whether this ship is sunk.
   *
   * @return whether this ship is sunk.
   */
  public boolean isSunk() {
    return sunk;
  }

  /**
   * Get the ShipType of this Ship.
   *
   * @return the ShipType of this ship
   */
  public ShipType getShipType() {
    return shipType;
  }

  /**
   * Update the sunk field of this ship by checking if all the coordinates of the ship are in the
   * given list of hits.
   *
   * @param hits the list of hits on the board the ship is on.
   */
  public void checkSunk(List<Coord> hits) {
    boolean sunk = true;
    for (Coord c : coordinates) {
      if (!hits.contains(c)) {
        sunk = false;
      }
    }
    this.sunk = sunk;
  }
}
