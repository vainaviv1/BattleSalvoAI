package cs3500.pa03.view;

import java.io.IOException;

/**
 * Represents a view from a Battle Salvo game.
 */
public class BattleSalvoView implements View {
  private Appendable appendable;

  /**
   * Represents a constructor for a BattleSalvoView.
   *
   * @param appendable An appendable to output to.
   */
  public BattleSalvoView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Appends the given string to the appendable.
   */
  public void printMessage(String s) {
    try {
      appendable.append(s);
      appendable.append(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Appends the given 2d array of strings as to the appendable.
   */
  public void printBoard(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        try {
          appendable.append(board[i][j] + " ");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        appendable.append("\n");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
