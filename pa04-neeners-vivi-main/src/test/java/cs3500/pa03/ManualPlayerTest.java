package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the ManualPlayer class.
 */
class ManualPlayerTest {
  Player p1;
  Player p2;
  Map<ShipType, Integer> shipCounts1;
  Map<ShipType, Integer> shipCounts2;
  Readable sr;
  Appendable appendable;

  /**
   * Initializes players, a map of ShipTypes, a readable, and an appendable.
   */
  @BeforeEach
  public void initializePlayers() {
    appendable = System.out;
    sr = new StringReader("");
    p1 = new ManualPlayer("Nina", new Random(6), new Scanner(sr), appendable);
    p2 = new ManualPlayer("Niki", new Random(6), new Scanner(sr), appendable);

    shipCounts1 = new TreeMap<>();
    shipCounts1.put(ShipType.CARRIER, 2);
    shipCounts1.put(ShipType.BATTLESHIP, 2);
    shipCounts1.put(ShipType.DESTROYER, 1);
    shipCounts1.put(ShipType.SUBMARINE, 3);
    shipCounts2 = new TreeMap<>();
    shipCounts2.put(ShipType.CARRIER, 2);
    shipCounts2.put(ShipType.BATTLESHIP, 1);
    shipCounts2.put(ShipType.DESTROYER, 1);
    shipCounts2.put(ShipType.SUBMARINE, 2);
  }

  /**
   * Tests that the name() method returns the player's name.
   */
  @Test
  public void testName() {
    assertEquals("Nina", p1.name());
    assertEquals("Niki", p2.name());
  }

  /**
   * Tests that the setup() method outputs an arraylist of the ships that match the specifications
   * in the map of ShipTypes input.
   */
  @Test
  public void testSetupOutputShipType() {
    List<Ship> expectedShips = new ArrayList<>();
    expectedShips.add(new Ship(ShipType.CARRIER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.CARRIER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.DESTROYER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));

    List<Ship> actualShips = p1.setup(9, 11, shipCounts1);
    for (int i = 0; i < actualShips.size(); i++) {
      assertEquals(expectedShips.get(i).getShipType(), actualShips.get(i).getShipType());
    }
  }

  /**
   * Tests that setup output a list of ships that are all initially set to not being sunk.
   */
  @Test
  public void testSetupOutputSunk() {
    List<Ship> expectedShips = new ArrayList<>();
    expectedShips.add(new Ship(ShipType.CARRIER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.CARRIER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.DESTROYER, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));
    expectedShips.add(new Ship(ShipType.SUBMARINE, new ArrayList<>(), false));

    List<Ship> actualShips = p1.setup(9, 11, shipCounts1);
    for (int i = 0; i < actualShips.size(); i++) {
      assertEquals(expectedShips.get(i).isSunk(), actualShips.get(i).isSunk());
    }
  }

  /**
   * Tests that setup outputs a list of ships that have the correct coordinates for each ship.
   */
  @Test
  public void testSetupOutputCoordinates() {
    List<Coord> ship1Coords = new ArrayList<>();
    ship1Coords.add(new Coord(7, 1));
    ship1Coords.add(new Coord(7, 2));
    ship1Coords.add(new Coord(7, 3));
    ship1Coords.add(new Coord(7, 4));
    ship1Coords.add(new Coord(7, 5));
    ship1Coords.add(new Coord(7, 6));

    List<Ship> expectedShips = new ArrayList<>();
    expectedShips.add(new Ship(ShipType.CARRIER, ship1Coords, false));

    List<Coord> ship2Coords = new ArrayList<>();
    ship2Coords.add(new Coord(2, 0));
    ship2Coords.add(new Coord(3, 0));
    ship2Coords.add(new Coord(4, 0));
    ship2Coords.add(new Coord(5, 0));
    ship2Coords.add(new Coord(6, 0));
    ship2Coords.add(new Coord(7, 0));
    expectedShips.add(new Ship(ShipType.CARRIER, ship2Coords, false));

    List<Coord> ship3Coords = new ArrayList<>();
    ship3Coords.add(new Coord(1, 8));
    ship3Coords.add(new Coord(2, 8));
    ship3Coords.add(new Coord(3, 8));
    ship3Coords.add(new Coord(4, 8));
    ship3Coords.add(new Coord(5, 8));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, ship3Coords, false));

    List<Coord> ship4Coords = new ArrayList<>();
    ship4Coords.add(new Coord(4, 7));
    ship4Coords.add(new Coord(5, 7));
    ship4Coords.add(new Coord(6, 7));
    ship4Coords.add(new Coord(7, 7));
    ship4Coords.add(new Coord(8, 7));
    expectedShips.add(new Ship(ShipType.BATTLESHIP, ship4Coords, false));

    List<Coord> ship5Coords = new ArrayList<>();
    ship5Coords.add(new Coord(6, 8));
    ship5Coords.add(new Coord(7, 8));
    ship5Coords.add(new Coord(8, 8));
    ship5Coords.add(new Coord(9, 8));
    expectedShips.add(new Ship(ShipType.DESTROYER, ship5Coords, false));

    List<Coord> ship6Coords = new ArrayList<>();
    ship6Coords.add(new Coord(1, 0));
    ship6Coords.add(new Coord(1, 1));
    ship6Coords.add(new Coord(1, 2));
    expectedShips.add(new Ship(ShipType.SUBMARINE, ship6Coords, false));

    List<Coord> ship7Coords = new ArrayList<>();
    ship7Coords.add(new Coord(1, 3));
    ship7Coords.add(new Coord(1, 4));
    ship7Coords.add(new Coord(1, 5));
    expectedShips.add(new Ship(ShipType.SUBMARINE, ship7Coords, false));

    List<Coord> ship8Coords = new ArrayList<>();
    ship8Coords.add(new Coord(0, 5));
    ship8Coords.add(new Coord(0, 6));
    ship8Coords.add(new Coord(0, 7));
    expectedShips.add(new Ship(ShipType.SUBMARINE, ship8Coords, false));

    List<Ship> actualShips = p1.setup(9, 11, shipCounts1);
    for (int i = 0; i < actualShips.size(); i++) {
      assertEquals(expectedShips.get(i).getCoordinates(), actualShips.get(i).getCoordinates());
    }
  }

  /**
   * Test that setup creates a board with the correct number of carriers.
   */
  @Test
  public void testSetupBoardCarrier() {
    p1.setup(9, 11, shipCounts1);

    int count = 0;
    for (char c : p1.toString().toCharArray()) {
      if (c == 'C') {
        count++;
      }
    }
    assertEquals(12, count);

    p2.setup(7, 6, shipCounts2);

    int count2 = 0;
    for (char c : p2.toString().toCharArray()) {
      if (c == 'C') {
        count2++;
      }
    }
    assertEquals(12, count2);
  }

  /**
   * Test that setup creates a board with the correct number of battleships.
   */
  @Test
  public void testSetupBoardBattleShip() {
    p1.setup(9, 11, shipCounts1);

    int count = 0;
    for (char c : p1.toString().toCharArray()) {
      if (c == 'B') {
        count++;
      }
    }
    assertEquals(10, count);

    p2.setup(7, 6, shipCounts2);

    int count2 = 0;
    for (char c : p2.toString().toCharArray()) {
      if (c == 'B') {
        count2++;
      }
    }
    assertEquals(5, count2);
  }

  /**
   * Test that setup creates a board with the correct number of destroyers.
   */
  @Test
  public void testSetupBoardDestroyer() {
    p1.setup(9, 11, shipCounts1);

    int count = 0;
    for (char c : p1.toString().toCharArray()) {
      if (c == 'D') {
        count++;
      }
    }
    assertEquals(4, count);

    p2.setup(7, 6, shipCounts2);

    int count2 = 0;
    for (char c : p2.toString().toCharArray()) {
      if (c == 'D') {
        count2++;
      }
    }
    assertEquals(4, count2);
  }

  /**
   * Test that setup creates a board with the correct number of submarines.
   */
  @Test
  public void testSetupBoardSubmarine() {
    p1.setup(9, 11, shipCounts1);

    int count = 0;
    for (char c : p1.toString().toCharArray()) {
      if (c == 'S') {
        count++;
      }
    }
    assertEquals(9, count);

    p2.setup(7, 6, shipCounts2);

    int count2 = 0;
    for (char c : p2.toString().toCharArray()) {
      if (c == 'S') {
        count2++;
      }
    }
    assertEquals(6, count2);
  }

  /**
   * Test that setup creates the correct board.
   */
  @Test
  public void testSetupBoard() {
    p1.setup(9, 11, shipCounts1);
    assertEquals("0SCCCCCC0000S00000C0000S00000C0000S00000C0000S00000C000SS00000C000S000"
        + "000C000S000BBBBB000BBBBBDDDD0", p1.toString());

    p2.setup(7, 6, shipCounts2);
    assertEquals("DDDD0000SSS0CCCCCCCCCCCC0000000BBBBB0SSS00", p2.toString());
  }

  /**
   * Test that takeShots remprompts user everytime they enter an invalid shot: 3 numbers, a negative
   * number, a non-number.
   */
  @Test
  public void testRespondToInvalidShots() {
    StringBuilder sb = new StringBuilder();
    Scanner s = new Scanner(new StringReader("3 four\n2 4 6\n-4 3\n0 -3\n0 12\n14 9\n14 20\n16 2"
        + "\n0 3\n4 6\n0 3\n6 5\n2 4\n5 1\n4 2\n2 0\n1 3\n2 1\n2 2\n2 3\n5 2\n5 3\n1 2\n3 2\n6 2\n"
        + "0 2\n3 4\n4 4\n0 5\n1 5\n1 4\n5 4\n6 4\n0 4\n3 1\n4 1\n6 1\n6 3\n6 0\n2 5\n3 5\n"));
    p1 = new ManualPlayer("Nina", new Random(4), s, sb);

    p1.setup(9, 11, shipCounts1);

    assertEquals("", sb.toString());

    p1.takeShots();

    assertEquals("Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n"
        + "Uh Oh! You've entered an invalid salvo.\n"
        + "Please Enter 8 Shots:\n", sb.toString());
  }

  /**
   * Test that takeShots reprompts user everytime they enter an invalid shot: 3 numbers, a negative
   * number, a non-number.
   */
  @Test
  public void testTakeShots() {
    StringBuilder sb;
    StringReader sr;
    sr = new StringReader("0 3\n6 5\n2 4\n5 1\n4 2\n2 0\n1 3\n2 1\n");
    Scanner s = new Scanner(sr);
    sb = new StringBuilder();
    p1 = new ManualPlayer("Nina", new Random(4), s, sb);

    p1.setup(9, 11, shipCounts1);

    List<Coord> expectedCoords = new ArrayList<>();
    expectedCoords.add(new Coord(0, 3));
    expectedCoords.add(new Coord(6, 5));
    expectedCoords.add(new Coord(2, 4));
    expectedCoords.add(new Coord(5, 1));
    expectedCoords.add(new Coord(4, 2));
    expectedCoords.add(new Coord(2, 0));
    expectedCoords.add(new Coord(1, 3));
    expectedCoords.add(new Coord(2, 1));

    List<Coord> actualCoords = p1.takeShots();
    assertEquals(expectedCoords, actualCoords);
  }

  /**
   * Tests that the reportDamage() method returns the list of coordinates that hit a ship.
   */
  @Test
  public void testReportDamage() {
    p1.setup(9, 11, shipCounts1);
    assertEquals("0SCCCCCC0000S00000C0000S00000C0000S00000C0000S00000C000SS00000C000S000"
        + "000C000S000BBBBB000BBBBBDDDD0", p1.toString());
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 3));
    shots.add(new Coord(6, 5));
    shots.add(new Coord(2, 4));
    shots.add(new Coord(10, 1));
    shots.add(new Coord(4, 2));
    shots.add(new Coord(2, 8));
    shots.add(new Coord(1, 3));
    shots.add(new Coord(8, 3));
    shots.add(new Coord(4, 2));
    shots.add(new Coord(7, 8));

    List<Coord> expectedHits = new ArrayList<>();
    expectedHits.add(new Coord(2, 8));
    expectedHits.add(new Coord(1, 3));
    expectedHits.add(new Coord(7, 8));

    assertEquals(expectedHits, p1.reportDamage(shots));
  }


  /**
   * Tests the endGame method (stub).
   */
  @Test
  public void testEndGame() {
    p1.endGame(GameResult.WIN, "Player 1 won");
  }
}