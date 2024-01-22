package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.BattleSalvoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the BattleSalvoView class.
 */
class BattleSalvoViewTest {
  private BattleSalvoView bsv;
  private StringBuilder sb;

  /**
   * Initializes a StringBuilder and BattleSalvoView.
   */
  @BeforeEach
  public void initializeBattleSalvoView() {
    sb = new StringBuilder();
    bsv = new BattleSalvoView(sb);
  }

  /**
   * Tests that printMessage outputs the given message.
   */
  @Test
  public void testPrintMessage() {
    bsv.printMessage("Welcome to Battleship!");
    assertEquals(sb.toString(), "Welcome to Battleship!\n");
    bsv.printMessage("Please input your board dimensions.");
    assertEquals("Welcome to Battleship!\nPlease input your board dimensions.\n", sb.toString());
  }

  /**
   * Tests that printBoard outputs the given empty 6x8 board as a string.
   */
  @Test
  public void testPrintEmptyBoard6By8() {
    String[][] emptyBoard = new String[6][8];
    for (int i = 0; i < emptyBoard.length; i++) {
      for (int j = 0; j < emptyBoard[0].length; j++) {
        emptyBoard[i][j] = "0";
      }
    }
    bsv.printBoard(emptyBoard);
    assertEquals("0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 "
        + "\n0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 \n", sb.toString());
  }

  /**
   * Tests that printBoard outputs the given empty 7x12 board as a string.
   */
  @Test
  public void testPrintEmptyBoard7By12() {
    String[][] emptyBoard = new String[7][12];
    for (int i = 0; i < emptyBoard.length; i++) {
      for (int j = 0; j < emptyBoard[0].length; j++) {
        emptyBoard[i][j] = "0";
      }
    }
    bsv.printBoard(emptyBoard);
    assertEquals("0 0 0 0 0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 \n", sb.toString());
  }

  /**
   * Tests that printBoard outputs the given non-empty board as a string.
   */
  @Test
  public void testPrintNonEmptyBoard() {
    String[][] board = new String[7][12];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = "0";
      }
    }
    board[3][4] = "X";
    board[5][8] = "X";
    board[1][8] = "X";
    board[6][3] = "X";
    board[0][9] = "X";
    board[2][2] = "X";
    board[5][11] = "X";
    bsv.printBoard(board);
    assertEquals("0 0 0 0 0 0 0 0 0 X 0 0 \n0 0 0 0 0 0 0 0 X 0 0 0 \n"
        + "0 0 X 0 0 0 0 0 0 0 0 0 \n0 0 0 0 X 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 0 0 X 0 0 X \n0 0 0 X 0 0 0 0 0 0 0 0 \n", sb.toString());
  }
}