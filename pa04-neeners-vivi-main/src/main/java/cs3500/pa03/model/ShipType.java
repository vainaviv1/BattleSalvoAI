package cs3500.pa03.model;

/**
 * Represents an enumeration for a ShipType. A ShipType can be a CARRIER, BATTLESHIP, DESTROYER, or
 * SUBMARINE.
 */
public enum ShipType {
  CARRIER(6, "C"),
  BATTLESHIP(5, "B"),
  DESTROYER(4, "D"),
  SUBMARINE(3, "S");

  private final int size;
  private final String letter;

  /**
   * Represents a constructor for a ShipType.
   *
   * @param size the size associated with this ShipType.
   * @param letter the letter associated with this ShipType.
   */
  ShipType(int size, String letter) {
    this.size = size;
    this.letter = letter;
  }

  /**
   * Gets the size for this ShipType.
   *
   * @return the size associated with this ShipType.
   */
  public int getSize() {
    return size;
  }

  /**
   * Gets the letter for this ShipType.
   *
   * @return the letter associated with this ShipType.
   */
  public String getLetter() {
    return letter;
  }
}