package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.BoardInfoRecord;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the BoardInfoRecord class.
 */
class BoardInfoRecordTest {
  BoardInfoRecord b1;
  BoardInfoRecord b2;
  BoardInfoRecord b3;
  Map<ShipType, Integer> shipInfo1;
  Map<ShipType, Integer> shipInfo2;
  Map<ShipType, Integer> shipInfo3;

  /**
   * Initialize BoardInfoRecords to use for tests.
   */
  @BeforeEach
  public void initializeBoardInfoRecord() {
    shipInfo1 = new TreeMap<>();
    shipInfo1.put(ShipType.CARRIER, 1);
    shipInfo1.put(ShipType.BATTLESHIP, 3);
    shipInfo1.put(ShipType.DESTROYER, 2);
    shipInfo1.put(ShipType.SUBMARINE, 2);
    b1 = new BoardInfoRecord(13, 9, shipInfo1);

    shipInfo2 = new TreeMap<>();
    shipInfo2.put(ShipType.CARRIER, 3);
    shipInfo2.put(ShipType.BATTLESHIP, 1);
    shipInfo2.put(ShipType.DESTROYER, 1);
    shipInfo2.put(ShipType.SUBMARINE, 1);
    b2 = new BoardInfoRecord(7, 16, shipInfo2);

    shipInfo3 = new TreeMap<>();
    shipInfo3.put(ShipType.CARRIER, 2);
    shipInfo3.put(ShipType.BATTLESHIP, 2);
    shipInfo3.put(ShipType.DESTROYER, 1);
    shipInfo3.put(ShipType.SUBMARINE, 1);
    b3 = new BoardInfoRecord(8, 8, shipInfo3);
  }

  /**
   * Test that fleet() gets the fleet of the board.
   */
  @Test
  public void testBoardInfoFleet() {
    assertEquals(shipInfo1, b1.fleet());
    assertEquals(shipInfo2, b2.fleet());
    assertEquals(shipInfo3, b3.fleet());
  }

  /**
   * Test that height() gets the height of the board.
   */
  @Test
  public void testBoardInfoHeight() {
    assertEquals(9, b1.height());
    assertEquals(16, b2.height());
    assertEquals(8, b3.height());
  }

  /**
   * Test that width() gets the width of the board.
   */
  @Test
  public void testBoardInfoWidth() {
    assertEquals(13, b1.width());
    assertEquals(7, b2.width());
    assertEquals(8, b3.width());
  }
}