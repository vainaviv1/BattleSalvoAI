package cs3500.pa03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ShipType class
 */
public class ShipTypeTest {

  /**
   * Tests the ShipType GetShipSize method
   */
  @Test
  public void testGetShipSize() {
    assertEquals(6, ShipType.CARRIER.getShipSize());
    assertEquals(5, ShipType.BATTLESHIP.getShipSize());
    assertEquals(4, ShipType.DESTROYER.getShipSize());
    assertEquals(3, ShipType.SUBMARINE.getShipSize());
  }
}
