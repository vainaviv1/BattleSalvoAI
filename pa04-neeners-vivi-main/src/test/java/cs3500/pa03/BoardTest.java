package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Board class.
 */
class BoardTest {
  Board b1;
  Board b2;
  Map<ShipType, Integer> shipCounts1;
  Map<ShipType, Integer> shipCounts2;

  /**
   * Initializes boards and a map of ShipTypes.
   */
  @BeforeEach
  public void initializeBoards() {
    b1 = new Board(9, 12, new Random(1));
    b2 = new Board(6, 7, new Random(21));
    shipCounts1 = new TreeMap<>();
    shipCounts1.put(ShipType.CARRIER, 2);
    shipCounts1.put(ShipType.BATTLESHIP, 2);
    shipCounts1.put(ShipType.DESTROYER, 1);
    shipCounts1.put(ShipType.SUBMARINE, 1);
    shipCounts2 = new TreeMap<>();
    shipCounts2.put(ShipType.CARRIER, 2);
    shipCounts2.put(ShipType.BATTLESHIP, 1);
    shipCounts2.put(ShipType.DESTROYER, 1);
    shipCounts2.put(ShipType.SUBMARINE, 2);
  }

  /**
   * Tests that the boards are initialized to just 0s initializeGrid is called when a board is
   * created.
   */
  @Test
  public void testInitializeGrid() {
    assertEquals("00000000000000000000000000000000000000000000000000000000000000000"
        + "0000000000000000000000000000000000000000000", b1.toString());
    assertEquals("000000000000000000000000000000000000000000", b2.toString());
  }

  /**
   * Tests that when placeShips is called, the board has ships on it.
   */
  @Test
  public void testPlaceShips() {
    assertEquals("00000000000000000000000000000000000000000000000000000000000000000000000"
        + "0000000000000000000000000000000000000", b1.toString());
    b1.placeShips(shipCounts1);
    assertEquals("0BBBBB00000000CCCCCC0C00000000000C0000000SSS0C0000000000BC0000000000BC"
        + "0000000000BC0000000000B00000DDDD00B000", b1.toString());

    assertEquals("000000000000000000000000000000000000000000", b2.toString());
    b2.placeShips(shipCounts2);
    assertEquals("D0C0SBCD0C0SBCD0C0SBCD0CS0BC00CS0BC00CS00C", b2.toString());
  }

  /**
   * Checks that the board contains the correct number of carrier ships when place ships is called.
   */
  @Test
  public void testPlaceShipsContainsCarrier() {
    b1.placeShips(shipCounts1);
    int count1 = 0;
    for (char c : b1.toString().toCharArray()) {
      if (c == 'C') {
        count1++;
      }
    }
    assertEquals(12, count1);

    b2.placeShips(shipCounts2);
    int count2 = 0;
    for (char c : b2.toString().toCharArray()) {
      if (c == 'C') {
        count2++;
      }
    }
    assertEquals(12, count2);
  }

  /**
   * Checks that the board contains the correct number of battleship ships when place ships is
   * called.
   */
  @Test
  public void testPlaceShipsContainsBattleship() {
    b1.placeShips(shipCounts1);
    int count1 = 0;
    for (char c : b1.toString().toCharArray()) {
      if (c == 'B') {
        count1++;
      }
    }
    assertEquals(10, count1);


    b2.placeShips(shipCounts2);
    int count2 = 0;
    for (char c : b2.toString().toCharArray()) {
      if (c == 'B') {
        count2++;
      }
    }
    assertEquals(5, count2);
  }

  /**
   * Checks that the board contains the correct number of carrier ships when place ships is called.
   */
  @Test
  public void testPlaceShipsContainsDestroyer() {
    b1.placeShips(shipCounts1);

    int count1 = 0;
    for (char c : b1.toString().toCharArray()) {
      if (c == 'D') {
        count1++;
      }
    }
    assertEquals(4, count1);

    b2.placeShips(shipCounts2);

    int count2 = 0;
    for (char c : b2.toString().toCharArray()) {
      if (c == 'D') {
        count2++;
      }
    }
    assertEquals(4, count2);
  }

  /**
   * Checks that the board contains the correct number of carrier ships when place ships is called.
   */
  @Test
  public void testPlaceShipsContainsSubmarine() {
    b1.placeShips(shipCounts1);

    int count1 = 0;
    for (char c : b1.toString().toCharArray()) {
      if (c == 'S') {
        count1++;
      }
    }
    assertEquals(3, count1);

    b2.placeShips(shipCounts2);

    int count2 = 0;
    for (char c : b2.toString().toCharArray()) {
      if (c == 'S') {
        count2++;
      }
    }
    assertEquals(6, count2);
  }

  /**
   * Test the getShips method by checking that the size of the output list corresponds
   * to the number of ships in the map.
   */
  @Test
  public void testGetShipsBySize() {
    assertEquals(new ArrayList<Ship>(), b1.getShips());
    assertEquals(0, b1.getShips().size());

    b1.placeShips(shipCounts1);

    assertEquals(6, b1.getShips().size());

    assertEquals(new ArrayList<Ship>(), b2.getShips());
    assertEquals(0, b2.getShips().size());

    b2.placeShips(shipCounts2);

    assertEquals(6, b2.getShips().size());
  }
}