package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the CoordRecord class.
 */
class CoordRecordTest {
  CoordRecord c1;
  CoordRecord c2;
  CoordRecord c3;

  /**
   * Initialize CoordRecords to use for tests.
   */
  @BeforeEach
  public void initializeCoordRecord() {
    c1 = new CoordRecord(3, 2);
    c2 = new CoordRecord(12, 4);
    c3 = new CoordRecord(0, 9);
  }

  /**
   * Test that x() gets the x value of the coordinate.
   */
  @Test
  public void testCoordRecordX() {
    assertEquals(3, c1.x());
    assertEquals(12, c2.x());
    assertEquals(0, c3.x());
  }

  /**
   * Test that y() gets the y value of the coordinate.
   */
  @Test
  public void testCoordRecordY() {
    assertEquals(2, c1.y());
    assertEquals(4, c2.y());
    assertEquals(9, c3.y());
  }
}