package cs3500.pa03;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests the ShipTest class
 */
public class ShipTest {

  /**
   * Tests the ShipTest ShipCreation method
   */
  @Test
  public void testShipCreation() {
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(1, 2));
    coords.add(new Coord(2, 2));
    coords.add(new Coord(3, 2));

    ShipType shipType = ShipType.CARRIER;

    Ship ship = new Ship(shipType, coords);

    assertEquals(shipType, ship.getShipType());
    assertEquals(coords, ship.getCoords());
  }
}
