package cs3500.pa03.model;

/**
 * Represents a coordinate for a ship.
 */
public class Coord {
  private int xcoord;
  private int ycoord;

  /**
   * Represents a constructor for a Coord.
   *
   * @param xcoord the x coordinate
   * @param ycoord the y coordinate
   */
  public Coord(int xcoord, int ycoord) {
    this.xcoord = xcoord;
    this.ycoord = ycoord;
  }

  /**
   * Gets the x coordinate.
   *
   * @return the x coordinate.
   */
  public int getX() {
    return xcoord;
  }

  /**
   * Gets the y coordinate.
   *
   * @return the y coordinate.
   */
  public int getY() {
    return ycoord;
  }

  /**
   * Checks whether this coordinate and the given object are the same.
   * Override the equals method so that a coordinate's equality is based on whether the x and y
   * are the same number.
   *
   * @param obj the object to compare to.
   * @return whether the given object is the same as the given coordinate.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Coord) {
      return (this.xcoord == ((Coord) obj).getX()) && (this.ycoord == ((Coord) obj).getY());
    } else {
      return false;
    }
  }
}
