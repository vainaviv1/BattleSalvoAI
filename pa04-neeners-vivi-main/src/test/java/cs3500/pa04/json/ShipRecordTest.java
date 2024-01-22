package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ShipRecord class.
 */
class ShipRecordTest {
  CoordRecord c1;
  ShipRecord sr1;
  CoordRecord c2;
  ShipRecord sr2;
  CoordRecord c3;
  ShipRecord sr3;
  CoordRecord c4;
  ShipRecord sr4;
  CoordRecord c5;
  ShipRecord sr5;

  /**
   * Initialize ShipRecords along with coordinates to construct ShipRecords to use for tests.
   */
  @BeforeEach
  public void initializeShipInfoRecords() {
    c1 = new CoordRecord(3, 2);
    c2 = new CoordRecord(12, 4);
    c3 = new CoordRecord(0, 9);
    c4 = new CoordRecord(2, 0);
    c5 = new CoordRecord(5, 6);
    sr1 = new ShipRecord(c1, 6, "HORIZONTAL");
    sr2 = new ShipRecord(c2, 5, "VERTICAL");
    sr3 = new ShipRecord(c3, 4, "HORIZONTAL");
    sr4 = new ShipRecord(c4, 3, "HORIZONTAL");
    sr5 = new ShipRecord(c5, 6, "VERTICAL");
  }

  /**
   * Test that c() gets the coordinates for the ShipRecord object.
   */
  @Test
  public void testShipRecordC() {
    assertEquals(c1, sr1.c());
    assertEquals(c2, sr2.c());
    assertEquals(c3, sr3.c());
    assertEquals(c4, sr4.c());
    assertEquals(c5, sr5.c());
  }

  /**
   * Test that length() gets the length of the ship for the ShipRecord object.
   */
  @Test
  public void testShipRecordLength() {
    assertEquals(6, sr1.length());
    assertEquals(5, sr2.length());
    assertEquals(4, sr3.length());
    assertEquals(3, sr4.length());
    assertEquals(6, sr5.length());
  }

  /**
   * Test that direction() gets the direction of the ship for the ShipRecord object.
   */
  @Test
  public void testShipRecordDirection() {
    assertEquals("HORIZONTAL", sr1.direction());
    assertEquals("VERTICAL", sr2.direction());
    assertEquals("HORIZONTAL", sr3.direction());
    assertEquals("HORIZONTAL", sr4.direction());
    assertEquals("VERTICAL", sr5.direction());
  }
}