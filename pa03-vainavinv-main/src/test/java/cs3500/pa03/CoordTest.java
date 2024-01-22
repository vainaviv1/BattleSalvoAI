package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * tests the Coord class
 */
public class CoordTest {

  /**
   * Tests the Coord GetX method
   */
  @Test
  public void testGetX() {
    Coord coord = new Coord(2, 3);
    assertEquals(2, coord.getX());
  }

  /**
   * Tests the Coord getY method
   */
  @Test
  public void testGetY() {
    Coord coord = new Coord(2, 3);
    assertEquals(3, coord.getY());
  }

  /**
   * Tests the Coord SetX method.
   */
  @Test
  public void testSetX() {
    Coord coord = new Coord(2, 3);
    coord.setX(5);
    assertEquals(5, coord.getX());
  }

  /**
   * Tests the Coord SetY method.
   */
  @Test
  public void testSetY() {
    Coord coord = new Coord(2, 3);
    coord.setY(4);
    assertEquals(4, coord.getY());
  }


}