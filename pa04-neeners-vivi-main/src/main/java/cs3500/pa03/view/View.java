package cs3500.pa03.view;

import java.io.IOException;

/**
 * Represents a View.
 */
public interface View {

  /**
   * Appends the given string to the appendable.
   */
  void printMessage(String s);

  /**
   * Appends the given 2d array of strings as to the appendable.
   */
  void printBoard(String[][] board);
}
