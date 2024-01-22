package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ShipType enumeration.
 */
class ShipTypeTest {

  /**
   * Tests that get getSize returns the appropriate size for each ShipType.
   */
  @Test
  public void testGetSize() {
    assertEquals(6, ShipType.CARRIER.getSize());
    assertEquals(5, ShipType.BATTLESHIP.getSize());
    assertEquals(4, ShipType.DESTROYER.getSize());
    assertEquals(3, ShipType.SUBMARINE.getSize());
  }

  /**
   * Tests that getLetter returns the appropriate letter for each ShipType.
   */
  @Test
  public void testGetLetter() {
    assertEquals("C", ShipType.CARRIER.getLetter());
    assertEquals("B", ShipType.BATTLESHIP.getLetter());
    assertEquals("D", ShipType.DESTROYER.getLetter());
    assertEquals("S", ShipType.SUBMARINE.getLetter());
  }
}