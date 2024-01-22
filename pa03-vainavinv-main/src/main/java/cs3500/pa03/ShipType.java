package cs3500.pa03;

public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),

  SUBMARINE(3);

  public final int size;

  private ShipType(int size) {

    this.size = size;
  }

  public int getShipSize() {
    return size;
  }
}