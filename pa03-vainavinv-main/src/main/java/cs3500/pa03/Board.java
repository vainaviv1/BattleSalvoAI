package cs3500.pa03;


import java.util.List;
/**
 * Represents the game board for placing ships and tracking hits and misses.
 */
public class Board {

  private char[][] board;
  private int height;
  private int width;

  /**
   * Constructs a game board with the specified height and width.
   *
   * @param height the height of the game board
   * @param width  the width of the game board
   */
  public Board(int height, int width) {
    this.height= height;
    this.width= width;
    board = new char[height][width];
    cellValue();

  }

  /**
   * Initializes the cells of the game board with default values.
   */
  private void cellValue() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = '0';
      }
    }
  }


  /**
   * Places the ships on the game board.
   *
   * @param ships the list of ships to place on the board
   */
  public void placeShips(List<Ship> ships) {
    for (Ship ship : ships) {
      for (Coord coord : ship.getCoords()) {
        int x = coord.getX();
        int y = coord.getY();
        board[x][y] = 'S';
      }
    }
  }

  /**
   * Sets the cell value to represent a hit.
   *
   * @param i the row index of the cell
   * @param j the column index of the cell
   */
  private void hitValue(int i, int j) {
    board[i][j]='H';
  }
  /**
   * Sets the cell value to represent a miss.
   *
   * @param i the row index of the cell
   * @param j the column index of the cell
   */
  private void missValue(int i, int j) {
    board[i][j]='M';
  }


  /**
   * Updates the cells of the game board based on the opponent's hits.
   *
   * @param hitsByOpponent the list of coordinates representing hits by the opponent
   */
  public void reportDamage(List<Coord> hitsByOpponent) {
//
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (getCell(i, j) == 'S'){
        Coord coord = new Coord(i, j);
        for (int g = 0; g < hitsByOpponent.size(); g++) {
          if ((coord.getX() == hitsByOpponent.get(g).getX()) && (coord.getY() ==
              hitsByOpponent.get(g).getY())) {
            hitValue(i, j);
          } else {
            missValue(i, j);
          }
        }
      }
      }
    }
  }


  /**
   * Checks if all ships on the game board have been sunk.
   *
   * @return true if all ships are sunk, false otherwise
   */
  public boolean allShipsSunk() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (board[i][j] != '0') {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * gets the value at the cell at the given coordinates
   * @param x coordinate
   * @param y coordinate
   * @return the coordinate at x,y on the baord
   */
  public char getCell(int x, int y) {
    return board[x][y];
  }

  /**
   * set the place on the board at the given ccoordinate to a value
   * @param x coordinate
   * @param y coordinate
   * @param a value you waant to set the x, y coordinate to on the board
   */
  public void setCell(int x, int y, char a) {
    board[x][y] = a;
  }


  /**
   * gets the height of the board
   * @return the height of the board
   */
  public int getHeight() {
    return height;
  }

  /**
   * gets the width of the board
   * @return the width of the board
   */
  public int getWidth() {
    return width;
  }
}

