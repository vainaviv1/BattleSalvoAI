package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ShipInfoRecord class.
 */
class ShipInfoRecordTest {
  ShipInfoRecord si1;
  ShipInfoRecord si2;
  ShipInfoRecord si3;

  /**
   * Initialize ShipInfoRecords to use for tests.
   */
  @BeforeEach
  public void initializeShipInfoRecords() {
    si1 = new ShipInfoRecord(2, 1, 2, 3);
    si2 = new ShipInfoRecord(3, 1, 1, 1);
    si3 = new ShipInfoRecord(1, 2, 1, 1);
  }

  /**
   * Test that numCarriers() gets the number of carriers for the ShipInfo object.
   */
  @Test
  public void testShipInfoNumCarriers() {
    assertEquals(2, si1.numCarriers());
    assertEquals(3, si2.numCarriers());
    assertEquals(1, si3.numCarriers());
  }

  /**
   * Test that numBattleships() gets the number of battleships for the ShipInfo object.
   */
  @Test
  public void testShipInfoNumBattleships() {
    assertEquals(1, si1.numBattleships());
    assertEquals(1, si2.numBattleships());
    assertEquals(2, si3.numBattleships());
  }

  /**
   * Test that numDestroyers() gets the number of destroyers for the ShipInfo object.
   */
  @Test
  public void testShipInfoNumDestroyers() {
    assertEquals(2, si1.numDestroyers());
    assertEquals(1, si2.numDestroyers());
    assertEquals(1, si3.numDestroyers());
  }

  /**
   * Test that numSubmarines() gets the number of submarines for the ShipInfo object.
   */
  @Test
  public void testShipInfoNumSubmarines() {
    assertEquals(3, si1.numSubmarines());
    assertEquals(1, si2.numSubmarines());
    assertEquals(1, si3.numSubmarines());
  }
}