package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the GameResult enumeration.
 */
class GameResultTest {

  /**
   * Tests that the .name() class returns the name for each GameResult.
   */
  @Test
  public void testGameResult() {
    assertEquals("WIN", GameResult.WIN.name());
    assertEquals("LOSE", GameResult.LOSE.name());
    assertEquals("DRAW", GameResult.DRAW.name());
  }
}