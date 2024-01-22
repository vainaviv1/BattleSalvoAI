package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the FleetRecord class.
 */
class FleetRecordTest {
  FleetRecord f1;
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
  List<ShipRecord> shipRecords1;
  FleetRecord f2;
  CoordRecord c6;
  ShipRecord sr6;
  CoordRecord c7;
  ShipRecord sr7;
  CoordRecord c8;
  ShipRecord sr8;
  CoordRecord c9;
  ShipRecord sr9;
  List<ShipRecord> shipRecords2;

  /**
   * Initialize the first FleetRecord, along with the coordinates and ShipRecords needed to
   * construct it to use for tests.
   */
  @BeforeEach
  public void initializeFleetRecord1() {
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
    shipRecords1 = new ArrayList<>();
    shipRecords1.add(sr1);
    shipRecords1.add(sr2);
    shipRecords1.add(sr3);
    shipRecords1.add(sr4);
    shipRecords1.add(sr5);
    f1 = new FleetRecord(shipRecords1);
  }

  /**
   * Initialize the second FleetRecord, along with the coordinates and ShipRecords needed to
   * construct it to use for tests.
   */
  @BeforeEach
  public void initializeFleetRecord2() {
    c6 = new CoordRecord(5, 6);
    c7 = new CoordRecord(1, 11);
    c8 = new CoordRecord(9, 3);
    c9 = new CoordRecord(13, 1);
    sr6 = new ShipRecord(c6, 6, "VERTICAL");
    sr7 = new ShipRecord(c7, 3, "VERTICAL");
    sr8 = new ShipRecord(c8, 5, "HORIZONTAL");
    sr9 = new ShipRecord(c9, 5, "HORIZONTAL");
    shipRecords2 = new ArrayList<>();
    shipRecords2.add(sr6);
    shipRecords2.add(sr7);
    shipRecords2.add(sr8);
    shipRecords2.add(sr9);
    f2 = new FleetRecord(shipRecords2);
  }

  /**
   * Test that fleet() gets the fleet of the FleetRecord.
   */
  @Test
  public void testFleetRecordFleet() {
    assertEquals(shipRecords1, f1.fleet());
    assertEquals(shipRecords2, f2.fleet());
  }
}