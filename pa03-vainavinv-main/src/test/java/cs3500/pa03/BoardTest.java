package cs3500.pa03;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Board class.
 */
public class BoardTest {

  /**
   * Tests the Board placeShips method to ensure that ships are correctly placed on the board.
   */
  @Test
  public void testPlaceShips() {
    Board board = new Board(5, 5);
    List<Ship> ships = new ArrayList<>();
    ships.add(new Ship(ShipType.CARRIER, new ArrayList<>()));
    ships.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>()));
    ships.add(new Ship(ShipType.DESTROYER, new ArrayList<>()));
    ships.add(new Ship(ShipType.SUBMARINE, new ArrayList<>()));

    board.placeShips(ships);

    // Assert that all cells with ships are set to 'S'
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        if (board.getCell(i, j) == 'S') {
          continue;
        }
        assertEquals('0', board.getCell(i, j));
      }
    }
  }

  /**
   * Tests the Board reportDamage method to ensure that damage is correctly reported on the board.
   */
  @Test
  public void testReportDamage() {
    Board board = new Board(5, 5);
    List<Coord> hitsByOpponent = new ArrayList<>();
    hitsByOpponent.add(new Coord(0, 0));
    hitsByOpponent.add(new Coord(2, 2));
    hitsByOpponent.add(new Coord(4, 4));

    board.placeShips(new ArrayList<>());
    board.reportDamage(hitsByOpponent);

    // Assert that cells hit by opponent are set to 'H' and others are set to 'M'
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        if (hitsByOpponent.contains(new Coord(i, j))) {
          assertEquals('H', board.getCell(i, j));
        } else {
          assertEquals('0', board.getCell(i, j));
        }
      }
    }
  }

  /**
   * Tests the Board allShipsSunk method to ensure that it correctly determines whether all ships are sunk.
   */
  @Test
  public void testAllShipsSunk() {
    Board board = new Board(5, 5);

    // Place some ships on the board
    List<Ship> ships = new ArrayList<>();
    ships.add(new Ship(ShipType.CARRIER, new ArrayList<>()));
    ships.add(new Ship(ShipType.BATTLESHIP, new ArrayList<>()));
    ships.add(new Ship(ShipType.DESTROYER, new ArrayList<>()));
    board.placeShips(ships);

    // Assert that allShipsSunk returns false
    assertTrue(board.allShipsSunk());

    // Report damage on all ship cells
    List<Coord> hitsByOpponent = new ArrayList<>();
    for (Ship ship : ships) {
      for (Coord coord : ship.getCoords()) {
        hitsByOpponent.add(coord);
      }
    }
    board.reportDamage(hitsByOpponent);

    // Assert that allShipsSunk returns true
    assertTrue(board.allShipsSunk());
  }

  // Add more test cases as needed

}
