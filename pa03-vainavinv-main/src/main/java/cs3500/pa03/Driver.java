package cs3500.pa03;

import java.util.*;


/**
 * The main class for running the battleship game. It serves as the entry point of the program
 * and initializes the game controller.
 */
public class Driver {

  /**
   * The entry point of the program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int height = 0;
    int width = 0;
    List<Integer> fleetSizes = new ArrayList<Integer>();

    Controller controller = new Controller(height, width, fleetSizes);
    controller.checkInput();
    controller.startGame();
  }
}






