package cs3500.pa03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the GameResult class
 */
public class GameResultTest {

  /**
   * Tests the GameResult GameResult values
   */
  @Test
  public void testGameResultValues() {
    assertEquals("WIN", GameResult.WIN.toString());
    assertEquals("LOSE", GameResult.LOSE.toString());
    assertEquals("TIE", GameResult.TIE.toString());
  }

}
