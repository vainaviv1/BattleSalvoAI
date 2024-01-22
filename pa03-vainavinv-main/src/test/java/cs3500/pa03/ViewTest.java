package cs3500.pa03;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the View class
 */
public class ViewTest {

  /**
   * Tests the View PrintBoard method
   */
  @Test
  public void testPrintBoard() {
    Board board = new Board(5, 5);
    board.setCell(0, 0, 'A');
    board.setCell(1, 1, 'B');
    board.setCell(2, 2, 'C');

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOutputStream = System.out;
    System.setOut(new PrintStream(outputStream));

    View view = new View();
    view.printBoard(board);

    String expectedOutput = "A 0 0 0 0 \n0 B 0 0 0 \n0 0 C 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n";
    assertEquals(expectedOutput, outputStream.toString());


    System.setOut(originalOutputStream);
  }

  /**
   * Tests the View PrintBoardOpponent method
   */
  @Test
  public void testPrintBoardOpponent() {
    Board board = new Board(5, 5);
    board.setCell(0, 0, 'A');
    board.setCell(1, 1, 'B');
    board.setCell(2, 2, 'H');
    board.setCell(3, 3, 'M');

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOutputStream = System.out;
    System.setOut(new PrintStream(outputStream));

    View view = new View();
    view.printBoardOpponent(board);

    String expectedOutput = "0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n";
    assertEquals(expectedOutput, outputStream.toString());


    System.setOut(originalOutputStream);
  }

  /**
   * Tests the View UpdateBoards method
   */
  @Test
  public void testUpdateBoards() {
    Board opponentBoard = new Board(5, 5);
    Board playerBoard = new Board(5, 5);
    List<Coord> playerShots = new ArrayList<>();
    List<Coord> opponentShots = new ArrayList<>();
    List<Coord> playerHits = new ArrayList<>();
    List<Coord> opponentHits = new ArrayList<>();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOutputStream = System.out;
    System.setOut(new PrintStream(outputStream));

    View view = new View();
    view.updateBoards(opponentBoard, playerBoard, playerShots, opponentShots, playerHits,
        opponentHits);

    String expectedOutput =
        "\nOpponent Board Data:\n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n" +
            "\nYour Board:\n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n0 0 0 0 0 \n";
    assertEquals(expectedOutput, outputStream.toString());


  }
}