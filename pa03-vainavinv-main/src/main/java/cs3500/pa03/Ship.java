package cs3500.pa03;


import java.util.ArrayList;

public class Ship {
  private ShipType type;
  private ArrayList<Coord> coords;

  public Ship(ShipType type, ArrayList<Coord> coords) {
    this.type = type;
    this.coords = coords;
  }

  public ShipType getShipType() {

    return type;
  }

  public ArrayList<Coord> getCoords() {

    return coords;
  }
}
