package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import cs3500.pa04.GameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the GameInfoRecord class.
 */
class GameInfoRecordTest {
  GameInfoRecord gi1;
  GameInfoRecord gi2;

  /**
   * Initialize GameInfoRecords to use for tests.
   */
  @BeforeEach
  public void initializeGameInfoRecord() {
    gi1 = new GameInfoRecord("nileenajohn", GameType.MULTI);
    gi2 = new GameInfoRecord("vainavinv", GameType.SINGLE);
  }

  /**
   * Test that username() gets the GameInfo's username.
   */
  @Test
  public void testGameInfoRecordUsername() {
    assertEquals("nileenajohn", gi1.username());
    assertEquals("vainavinv", gi2.username());
  }

  /**
   * Test that gameType() gets the GameInfo's gameType.
   */
  @Test
  public void testGameInfoRecordGameType() {
    assertEquals(GameType.MULTI, gi1.gameType());
    assertEquals(GameType.SINGLE, gi2.gameType());
  }
}