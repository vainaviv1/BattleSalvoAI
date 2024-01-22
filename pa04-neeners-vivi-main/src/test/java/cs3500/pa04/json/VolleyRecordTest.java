package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the VolleyRecord class.
 */
class VolleyRecordTest {
  CoordRecord c1;
  CoordRecord c2;
  CoordRecord c3;
  CoordRecord c4;
  CoordRecord c5;
  CoordRecord c6;
  CoordRecord c7;
  CoordRecord c8;
  CoordRecord c9;
  List<CoordRecord> coordRecords1;
  List<CoordRecord> coordRecords2;
  VolleyRecord v1;
  VolleyRecord v2;

  /**
   * Initialize the first VolleyRecord along with the CoordRecords needed to construct the
   * VolleyRecord.
   */
  @BeforeEach
  public void initializeVolleyRecord1() {
    c1 = new CoordRecord(3, 2);
    c2 = new CoordRecord(12, 4);
    c3 = new CoordRecord(0, 9);
    c4 = new CoordRecord(2, 0);
    c5 = new CoordRecord(5, 6);
    coordRecords1 = new ArrayList<>();
    coordRecords1.add(c1);
    coordRecords1.add(c2);
    coordRecords1.add(c3);
    coordRecords1.add(c4);
    coordRecords1.add(c5);
    v1 = new VolleyRecord(coordRecords1);
  }

  /**
   * Initialize the second VolleyRecord along with the CoordRecords needed to construct the
   * VolleyRecord.
   */
  @BeforeEach
  public void initializeVolleyRecord2() {
    c6 = new CoordRecord(5, 6);
    c7 = new CoordRecord(1, 11);
    c8 = new CoordRecord(9, 3);
    c9 = new CoordRecord(13, 1);
    coordRecords2 = new ArrayList<>();
    coordRecords2.add(c6);
    coordRecords2.add(c7);
    coordRecords2.add(c8);
    coordRecords2.add(c9);
    v2 = new VolleyRecord(coordRecords2);
  }

  /**
   * Test that coordinates() gets the Coordinates of the volley.
   */
  @Test
  public void testVolleyCoordinates() {
    assertEquals(coordRecords1, v1.coordinates());
    assertEquals(coordRecords2, v2.coordinates());
  }
}