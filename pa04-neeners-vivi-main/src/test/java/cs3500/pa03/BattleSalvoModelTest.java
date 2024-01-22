package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.BattleSalvoModel;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the BattleSalvoModel class.
 */
class BattleSalvoModelTest {
  BattleSalvoModel bsm1;
  BattleSalvoModel bsm2;
  Player p1;
  Player p2;
  Player player1;
  Player player2;
  ArrayList<Integer> fleet1;
  ArrayList<Integer> fleet2;
  Readable sr;
  Appendable appendable;

  /**
   * Initializes an appendable, readable, players, and a BattleSalvoModel.
   */
  @BeforeEach
  public void initialize() {
    appendable = System.out;
    sr = new StringReader("");
    p1 = new ManualPlayer("Nina", new Random(4), new Scanner(sr), appendable);
    p2 = new AiPlayer("AI", new Random(6));

    this.bsm1 = new BattleSalvoModel(p1, p2);
    fleet1 = new ArrayList<>();
    fleet1.add(2);
    fleet1.add(1);
    fleet1.add(1);
    fleet1.add(2);
    bsm1.createBoards(7, 8, fleet1);

    player1 = new ManualPlayer("Nina", new Random(4), new Scanner(sr), appendable);
    player2 = new AiPlayer("Other AI", new Random(6));
    this.bsm2 = new BattleSalvoModel(player1, player2);
    fleet2 = new ArrayList<>();
    fleet2.add(3);
    fleet2.add(4);
    fleet2.add(1);
    fleet2.add(1);
    bsm2.createBoards(12, 9, fleet2);
  }

  /**
   * Tests that when createBoards is called in initialize, the correct boards are saved for each
   * player.
   */
  @Test
  public void testCreateBoards() {
    assertEquals("0CCCCCC000DDDDS00B0000S00B0000S00BCCCCCC0B000SSS0B000000", p1.toString());
    assertEquals("CSSSSSS0C0000000C00BBBBBCCCCCCC0C0000000C0DDDD0000000000", p2.toString());
    assertEquals("0CCCCCC00000DDDD000000000000C00BBBBB0C00000000C000000B0CBBBBB0B0C000SSS"
        + "B0CCCCCCCB00000000B00BBBBB00000000000", player1.toString());
    assertEquals("000000000CCCCCC0000000B00000000B000000C0B00DC00C0B00DC00C0B00DCB0C0000D"
        + "CB0C0000SCB0C0000SCBBBBBB0S0B000BBBBB", player2.toString());
  }

  /**
   * Tests that the getBoard method returns the board correctly as a 2D array list.
   */
  @Test
  public void testGetBoard() {
    assertEquals("0CCCCCC000DDDDS00B0000S00B0000S00BCCCCCC0B000SSS0B000000", p1.toString());
    String[][] p1Board = bsm1.getBoard(p1.toString());
    String[][] board = new String[p1Board.length][p1Board[0].length];
    board[0][0] = "0";
    board[0][1] = "C";
    board[0][2] = "C";
    board[0][3] = "C";
    board[0][4] = "C";
    board[0][5] = "C";
    board[0][6] = "C";
    board[0][7] = "0";
    board[1][0] = "0";
    board[1][1] = "0";
    board[1][2] = "D";
    board[1][3] = "D";
    board[1][4] = "D";
    board[1][5] = "D";
    board[1][6] = "S";
    board[1][7] = "0";
    board[2][0] = "0";
    board[2][1] = "B";
    board[2][2] = "0";
    board[2][3] = "0";
    board[2][4] = "0";
    board[2][5] = "0";
    board[2][6] = "S";
    board[2][7] = "0";
    board[3][0] = "0";
    board[3][1] = "B";
    board[3][2] = "0";
    board[3][3] = "0";
    board[3][4] = "0";
    board[3][5] = "0";
    board[3][6] = "S";
    board[3][7] = "0";
    board[4][0] = "0";
    board[4][1] = "B";
    board[4][2] = "C";
    board[4][3] = "C";
    board[4][4] = "C";
    board[4][5] = "C";
    board[4][6] = "C";
    board[4][7] = "C";
    board[5][0] = "0";
    board[5][1] = "B";
    board[5][2] = "0";
    board[5][3] = "0";
    board[5][4] = "0";
    board[5][5] = "S";
    board[5][6] = "S";
    board[5][7] = "S";
    board[6][0] = "0";
    board[6][1] = "B";
    board[6][2] = "0";
    board[6][3] = "0";
    board[6][4] = "0";
    board[6][5] = "0";
    board[6][6] = "0";
    board[6][7] = "0";

    for (int i = 0; i < p1Board.length; i++) {
      for (int j = 0; j < p1Board[i].length; j++) {
        if (!p1Board[i][j].equals(board[i][j])) {
          assertEquals(true, p1Board[i][j].equals(board[i][j]));
        }
      }
    }
  }

  /**
   * Tests that getP1Board returns player 1's board.
   */
  @Test
  public void testGetP1Board() {
    String[][] board = new String[7][8];
    board[0][0] = "0";
    board[0][1] = "C";
    board[0][2] = "C";
    board[0][3] = "C";
    board[0][4] = "C";
    board[0][5] = "C";
    board[0][6] = "C";
    board[0][7] = "0";
    board[1][0] = "0";
    board[1][1] = "0";
    board[1][2] = "D";
    board[1][3] = "D";
    board[1][4] = "D";
    board[1][5] = "D";
    board[1][6] = "S";
    board[1][7] = "0";
    board[2][0] = "0";
    board[2][1] = "B";
    board[2][2] = "0";
    board[2][3] = "0";
    board[2][4] = "0";
    board[2][5] = "0";
    board[2][6] = "S";
    board[2][7] = "0";
    board[3][0] = "0";
    board[3][1] = "B";
    board[3][2] = "0";
    board[3][3] = "0";
    board[3][4] = "0";
    board[3][5] = "0";
    board[3][6] = "S";
    board[3][7] = "0";
    board[4][0] = "0";
    board[4][1] = "B";
    board[4][2] = "C";
    board[4][3] = "C";
    board[4][4] = "C";
    board[4][5] = "C";
    board[4][6] = "C";
    board[4][7] = "C";
    board[5][0] = "0";
    board[5][1] = "B";
    board[5][2] = "0";
    board[5][3] = "0";
    board[5][4] = "0";
    board[5][5] = "S";
    board[5][6] = "S";
    board[5][7] = "S";
    board[6][0] = "0";
    board[6][1] = "B";
    board[6][2] = "0";
    board[6][3] = "0";
    board[6][4] = "0";
    board[6][5] = "0";
    board[6][6] = "0";
    board[6][7] = "0";

    String[][] ans = bsm1.getP1Board();
    for (int i = 0; i < ans.length; i++) {
      for (int j = 0; j < ans[0].length; j++) {
        assertEquals(true, ans[i][j].equals(board[i][j]));
      }
    }
  }

  /**
   * Tests that getP2Board returns player 2's board.
   */
  @Test
  public void testGetP2Board() {
    String[][] board = new String[7][8];
    board[0][0] = "C";
    board[0][1] = "S";
    board[0][2] = "S";
    board[0][3] = "S";
    board[0][4] = "S";
    board[0][5] = "S";
    board[0][6] = "S";
    board[0][7] = "0";
    board[1][0] = "C";
    board[1][1] = "0";
    board[1][2] = "0";
    board[1][3] = "0";
    board[1][4] = "0";
    board[1][5] = "0";
    board[1][6] = "0";
    board[1][7] = "0";
    board[2][0] = "C";
    board[2][1] = "0";
    board[2][2] = "0";
    board[2][3] = "B";
    board[2][4] = "B";
    board[2][5] = "B";
    board[2][6] = "B";
    board[2][7] = "B";
    board[3][0] = "C";
    board[3][1] = "C";
    board[3][2] = "C";
    board[3][3] = "C";
    board[3][4] = "C";
    board[3][5] = "C";
    board[3][6] = "C";
    board[3][7] = "0";
    board[4][0] = "C";
    board[4][1] = "0";
    board[4][2] = "0";
    board[4][3] = "0";
    board[4][4] = "0";
    board[4][5] = "0";
    board[4][6] = "0";
    board[4][7] = "0";
    board[5][0] = "C";
    board[5][1] = "0";
    board[5][2] = "D";
    board[5][3] = "D";
    board[5][4] = "D";
    board[5][5] = "D";
    board[5][6] = "0";
    board[5][7] = "0";
    board[6][0] = "0";
    board[6][1] = "0";
    board[6][2] = "0";
    board[6][3] = "0";
    board[6][4] = "0";
    board[6][5] = "0";
    board[6][6] = "0";
    board[6][7] = "0";

    String[][] ans = bsm1.getP2Board();
    for (int i = 0; i < ans.length; i++) {
      for (int j = 0; j < ans[0].length; j++) {
        assertEquals(true, ans[i][j].equals(board[i][j]));
      }
    }
  }

  /**
   * Tests that displayOpponentBoard returns the player's board with zeros.
   */
  @Test
  public void testDisplayOpponentBoard() {
    String[][] inputBoard = new String[7][8];
    inputBoard[0][0] = "0";
    inputBoard[0][1] = "C";
    inputBoard[0][2] = "C";
    inputBoard[0][3] = "C";
    inputBoard[0][4] = "C";
    inputBoard[0][5] = "C";
    inputBoard[0][6] = "C";
    inputBoard[0][7] = "0";
    inputBoard[1][0] = "0";
    inputBoard[1][1] = "0";
    inputBoard[1][2] = "D";
    inputBoard[1][3] = "D";
    inputBoard[1][4] = "D";
    inputBoard[1][5] = "D";
    inputBoard[1][6] = "S";
    inputBoard[1][7] = "0";
    inputBoard[2][0] = "0";
    inputBoard[2][1] = "B";
    inputBoard[2][2] = "0";
    inputBoard[2][3] = "0";
    inputBoard[2][4] = "0";
    inputBoard[2][5] = "0";
    inputBoard[2][6] = "S";
    inputBoard[2][7] = "0";
    inputBoard[3][0] = "0";
    inputBoard[3][1] = "B";
    inputBoard[3][2] = "0";
    inputBoard[3][3] = "0";
    inputBoard[3][4] = "0";
    inputBoard[3][5] = "0";
    inputBoard[3][6] = "S";
    inputBoard[3][7] = "0";
    inputBoard[4][0] = "0";
    inputBoard[4][1] = "B";
    inputBoard[4][2] = "C";
    inputBoard[4][3] = "C";
    inputBoard[4][4] = "C";
    inputBoard[4][5] = "C";
    inputBoard[4][6] = "C";
    inputBoard[4][7] = "C";
    inputBoard[5][0] = "0";
    inputBoard[5][1] = "B";
    inputBoard[5][2] = "0";
    inputBoard[5][3] = "0";
    inputBoard[5][4] = "0";
    inputBoard[5][5] = "S";
    inputBoard[5][6] = "S";
    inputBoard[5][7] = "S";
    inputBoard[6][0] = "0";
    inputBoard[6][1] = "B";
    inputBoard[6][2] = "0";
    inputBoard[6][3] = "0";
    inputBoard[6][4] = "0";
    inputBoard[6][5] = "0";
    inputBoard[6][6] = "0";
    inputBoard[6][7] = "0";

    String[][] outputBoard = new String[7][8];
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 8; j++) {
        outputBoard[i][j] = "0";
      }
    }

    String[][] ans = bsm1.displayOpponentBoard(inputBoard);
    for (int i = 0; i < ans.length; i++) {
      for (int j = 0; j < ans[0].length; j++) {
        assertEquals(true, ans[i][j].equals(outputBoard[i][j]));
      }
    }
  }

  /**
   * Tests that displayPlayerBoard returns the player's board with X's in the ships locations.
   */
  @Test
  public void testDisplayPlayerBoard() {
    String[][] inputBoard = new String[7][8];
    inputBoard[0][0] = "0";
    inputBoard[0][1] = "C";
    inputBoard[0][2] = "C";
    inputBoard[0][3] = "C";
    inputBoard[0][4] = "C";
    inputBoard[0][5] = "C";
    inputBoard[0][6] = "C";
    inputBoard[0][7] = "0";
    inputBoard[1][0] = "0";
    inputBoard[1][1] = "0";
    inputBoard[1][2] = "D";
    inputBoard[1][3] = "D";
    inputBoard[1][4] = "D";
    inputBoard[1][5] = "D";
    inputBoard[1][6] = "S";
    inputBoard[1][7] = "0";
    inputBoard[2][0] = "0";
    inputBoard[2][1] = "B";
    inputBoard[2][2] = "0";
    inputBoard[2][3] = "0";
    inputBoard[2][4] = "0";
    inputBoard[2][5] = "0";
    inputBoard[2][6] = "S";
    inputBoard[2][7] = "0";
    inputBoard[3][0] = "0";
    inputBoard[3][1] = "B";
    inputBoard[3][2] = "0";
    inputBoard[3][3] = "0";
    inputBoard[3][4] = "0";
    inputBoard[3][5] = "0";
    inputBoard[3][6] = "S";
    inputBoard[3][7] = "0";
    inputBoard[4][0] = "0";
    inputBoard[4][1] = "B";
    inputBoard[4][2] = "C";
    inputBoard[4][3] = "C";
    inputBoard[4][4] = "C";
    inputBoard[4][5] = "C";
    inputBoard[4][6] = "C";
    inputBoard[4][7] = "C";
    inputBoard[5][0] = "0";
    inputBoard[5][1] = "B";
    inputBoard[5][2] = "0";
    inputBoard[5][3] = "0";
    inputBoard[5][4] = "0";
    inputBoard[5][5] = "S";
    inputBoard[5][6] = "S";
    inputBoard[5][7] = "S";
    inputBoard[6][0] = "0";
    inputBoard[6][1] = "B";
    inputBoard[6][2] = "0";
    inputBoard[6][3] = "0";
    inputBoard[6][4] = "0";
    inputBoard[6][5] = "0";
    inputBoard[6][6] = "0";
    inputBoard[6][7] = "0";

    String[][] outputBoard = new String[7][8];
    outputBoard[0][0] = "0";
    outputBoard[0][1] = "X";
    outputBoard[0][2] = "X";
    outputBoard[0][3] = "X";
    outputBoard[0][4] = "X";
    outputBoard[0][5] = "X";
    outputBoard[0][6] = "X";
    outputBoard[0][7] = "0";
    outputBoard[1][0] = "0";
    outputBoard[1][1] = "0";
    outputBoard[1][2] = "X";
    outputBoard[1][3] = "X";
    outputBoard[1][4] = "X";
    outputBoard[1][5] = "X";
    outputBoard[1][6] = "X";
    outputBoard[1][7] = "0";
    outputBoard[2][0] = "0";
    outputBoard[2][1] = "X";
    outputBoard[2][2] = "0";
    outputBoard[2][3] = "0";
    outputBoard[2][4] = "0";
    outputBoard[2][5] = "0";
    outputBoard[2][6] = "X";
    outputBoard[2][7] = "0";
    outputBoard[3][0] = "0";
    outputBoard[3][1] = "X";
    outputBoard[3][2] = "0";
    outputBoard[3][3] = "0";
    outputBoard[3][4] = "0";
    outputBoard[3][5] = "0";
    outputBoard[3][6] = "X";
    outputBoard[3][7] = "0";
    outputBoard[4][0] = "0";
    outputBoard[4][1] = "X";
    outputBoard[4][2] = "X";
    outputBoard[4][3] = "X";
    outputBoard[4][4] = "X";
    outputBoard[4][5] = "X";
    outputBoard[4][6] = "X";
    outputBoard[4][7] = "X";
    outputBoard[5][0] = "0";
    outputBoard[5][1] = "X";
    outputBoard[5][2] = "0";
    outputBoard[5][3] = "0";
    outputBoard[5][4] = "0";
    outputBoard[5][5] = "X";
    outputBoard[5][6] = "X";
    outputBoard[5][7] = "X";
    outputBoard[6][0] = "0";
    outputBoard[6][1] = "X";
    outputBoard[6][2] = "0";
    outputBoard[6][3] = "0";
    outputBoard[6][4] = "0";
    outputBoard[6][5] = "0";
    outputBoard[6][6] = "0";
    outputBoard[6][7] = "0";

    String[][] ans = bsm1.displayPlayerBoard(inputBoard);
    for (int i = 0; i < ans.length; i++) {
      for (int j = 0; j < ans[0].length; j++) {
        assertEquals(true, ans[i][j].equals(outputBoard[i][j]));
      }
    }
  }
}