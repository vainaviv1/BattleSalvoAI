package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Ship class.
 */
class ShipTest {
  private Ship s1;
  ArrayList<Coord> coords1;
  private Ship s2;
  ArrayList<Coord> coords2;
  private Ship s3;
  ArrayList<Coord> coords3;
  private Ship s4;
  ArrayList<Coord> coords4;

  /**
   * Initializes ships for testing.
   */
  @BeforeEach
  public void initializeShips() {
    coords1 = new ArrayList<>();
    coords1.add(new Coord(0, 0));
    coords1.add(new Coord(1, 0));
    coords1.add(new Coord(2, 0));
    coords1.add(new Coord(3, 0));
    coords1.add(new Coord(4, 0));
    coords1.add(new Coord(5, 0));
    s1 = new Ship(ShipType.CARRIER, coords1, false);

    coords2 = new ArrayList<>();
    coords2.add(new Coord(5, 4));
    coords2.add(new Coord(5, 5));
    coords2.add(new Coord(5, 6));
    coords2.add(new Coord(5, 7));
    coords2.add(new Coord(5, 8));
    s2 = new Ship(ShipType.BATTLESHIP, coords2, false);

    coords3 = new ArrayList<>();
    coords3.add(new Coord(0, 2));
    coords3.add(new Coord(0, 3));
    coords3.add(new Coord(0, 4));
    coords3.add(new Coord(0, 5));
    s3 = new Ship(ShipType.DESTROYER, coords3, false);

    coords4 = new ArrayList<>();
    coords4.add(new Coord(3, 2));
    coords4.add(new Coord(4, 2));
    coords4.add(new Coord(5, 2));
    s4 = new Ship(ShipType.SUBMARINE, coords4, false);
  }

  /**
   * Tests that getCoordinates gets the list of coordinates for each ship.
   */
  @Test
  public void testGetCoordinates() {
    assertEquals(coords1, s1.getCoordinates());
    assertEquals(coords2, s2.getCoordinates());
    assertEquals(coords3, s3.getCoordinates());
    assertEquals(coords4, s4.getCoordinates());
  }

  /**
   * Tests that updateCoordinates updates the ships coordinates and getCoordinates returns the new
   * coordinates.
   */
  @Test
  public void testUpdateCoordinates() {
    assertEquals(coords1, s1.getCoordinates());

    ArrayList<Coord> newCoords1 = new ArrayList<>();
    newCoords1.add(new Coord(3, 0));
    newCoords1.add(new Coord(3, 1));
    newCoords1.add(new Coord(3, 2));
    newCoords1.add(new Coord(3, 3));
    newCoords1.add(new Coord(3, 4));
    newCoords1.add(new Coord(3, 5));

    s1.updateCoordinates(newCoords1);
    assertEquals(newCoords1, s1.getCoordinates());

    assertEquals(coords4, s4.getCoordinates());

    ArrayList<Coord> newCoords4 = new ArrayList<>();
    newCoords4.add(new Coord(0, 5));
    newCoords4.add(new Coord(0, 6));
    newCoords4.add(new Coord(0, 7));

    s4.updateCoordinates(newCoords4);
    assertEquals(newCoords4, s4.getCoordinates());
  }

  /**
   * Test that a ship's sunk field is initially set to false.
   */
  @Test
  public void testIsSunkInitial() {
    assertEquals(s1.isSunk(), false);
    assertEquals(s2.isSunk(), false);
    assertEquals(s3.isSunk(), false);
    assertEquals(s4.isSunk(), false);
  }

  /**
   * Tests that checkSunk sinks ships if they are location in the list of given coordinates.
   * Tests that is sunk returns whether the ship is sunk after check sunk is called.
   */
  @Test
  public void testCheckSunkAndIsSunk() {
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 4));
    coords.add(new Coord(0, 0));
    coords.add(new Coord(3, 2));
    coords.add(new Coord(5, 8));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));
    coords.add(new Coord(4, 2));
    coords.add(new Coord(5, 6));
    coords.add(new Coord(4, 0));
    coords.add(new Coord(5, 2));
    coords.add(new Coord(5, 0));

    assertEquals(s1.isSunk(), false);
    s1.checkSunk(coords);
    assertEquals(s1.isSunk(), true);
    assertEquals(s2.isSunk(), false);
    s2.checkSunk(coords);
    assertEquals(s2.isSunk(), false);
    assertEquals(s3.isSunk(), false);
    s3.checkSunk(coords);
    assertEquals(s3.isSunk(), false);
    assertEquals(s4.isSunk(), false);
    s4.checkSunk(coords);
    assertEquals(s4.isSunk(), true);
  }

  /**
   * Tests that getShipType gets the ShipType for each Ship.
   */
  @Test
  public void testGetShipType() {
    assertEquals(ShipType.CARRIER, s1.getShipType());
    assertEquals(ShipType.BATTLESHIP, s2.getShipType());
    assertEquals(ShipType.DESTROYER, s3.getShipType());
    assertEquals(ShipType.SUBMARINE, s4.getShipType());
  }
}