package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.Coord;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Coord class.
 */
class CoordTest {
  private Coord c1;
  private Coord c2;
  private Coord c3;
  private Coord c4;
  private Coord c5;

  /**
   * Initializes 5 coords for testing.
   */
  @BeforeEach
  public void initializeCoord() {
    c1 = new Coord(0, 0);
    c2 = new Coord(12, 4);
    c3 = new Coord(7, 6);
    c4 = new Coord(9, 9);
    c5 = new Coord(12, 4);
  }

  /**
   * Tests that the getX() method returns the x value of the coordinate.
   */
  @Test
  public void testGetX() {
    assertEquals(0, c1.getX());
    assertEquals(12, c2.getX());
    assertEquals(7, c3.getX());
    assertEquals(9, c4.getX());
    assertEquals(12, c5.getX());
  }

  /**
   * Tests that the getY() method returns the y value of the coordinate.
   */
  @Test
  public void testGetY() {
    assertEquals(0, c1.getY());
    assertEquals(4, c2.getY());
    assertEquals(6, c3.getY());
    assertEquals(9, c4.getY());
    assertEquals(4, c5.getY());
  }


  /**
   * Tests that the equals method is overridden and compares coordinates based on their x and y
   * values.
   */
  @Test
  public void testEquals() {
    assertEquals(false, c1.equals(c2));
    assertEquals(true, c2.equals(new Coord(12, 4)));
    assertEquals(false, c4.equals(c5));
    assertEquals(true, c5.equals(c2));
    assertEquals(true, c2.equals(c5));
  }

  /**
   * Tests that the equals method returns false when comparing a non-coord with a coord.
   */
  @Test
  public void testEqualsNotCoord() {
    assertEquals(false, c1.equals("Hi there!"));
    assertEquals(false, c2.equals(2));
    assertEquals(false, c4.equals(new AiPlayer("Ai", new Random())));
  }
}