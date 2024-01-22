package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the GameEndRecord class.
 */
class GameEndRecordTest {
  GameEndRecord ge1;
  GameEndRecord ge2;
  GameEndRecord ge3;

  /**
   * Initialize GameEndRecords to use for tests.
   */
  @BeforeEach
  public void initializeGameEndRecord() {
    ge1 = new GameEndRecord(GameResult.WIN,
        "Your player sunk all the server player's ships!");
    ge2 = new GameEndRecord(GameResult.LOSE,
        "All your ships were sunk by the server player.");
    ge3 = new GameEndRecord(GameResult.DRAW,
        "Both players sunk each other's ship at the same time.");
  }

  /**
   * Test that result() gets the GameEndRecord's result.
   */
  @Test
  public void testGameEndRecordResult() {
    assertEquals(GameResult.WIN, ge1.result());
    assertEquals(GameResult.LOSE, ge2.result());
    assertEquals(GameResult.DRAW, ge3.result());
  }

  /**
   * Test that reason() gets the GameEndRecord's reason.
   */
  @Test
  public void testGameEndRecordReason() {
    assertEquals("Your player sunk all the server player's ships!", ge1.reason());
    assertEquals("All your ships were sunk by the server player.", ge2.reason());
    assertEquals("Both players sunk each other's ship at the same time.", ge3.reason());
  }
}