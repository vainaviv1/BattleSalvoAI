package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the GameType enumeration.
 */
class GameTypeTest {

  /**
   * Tests that the .name() class returns the name for each GameType.
   */
  @Test
  public void testGamType() {
    assertEquals("MULTI", GameType.MULTI.name());
    assertEquals("SINGLE", GameType.SINGLE.name());
  }
}