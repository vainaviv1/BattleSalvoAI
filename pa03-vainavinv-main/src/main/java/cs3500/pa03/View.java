package cs3500.pa03;


import java.util.List;

public class View {

  public void updateBoards(Board opponentBoard, Board playerBoard, List<Coord> playerShots,
                           List<Coord> opponentShots, List<Coord> playerHits,
                           List<Coord> opponentHits) {
    System.out.println("\nOpponent Board Data:");
    printBoardOpponent(opponentBoard);
    System.out.println("\nYour Board:");
    printBoard(playerBoard);
  }

  public void printBoard(Board board) {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        System.out.print(board.getCell(i, j) + " ");
      }
      System.out.println();
    }
  }

  public void printBoardOpponent(Board board) {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        if (!(board.getCell(i, j) == 'M') || !(board.getCell(i, j) == 'H')) {
          System.out.print("0" + " ");
        } else {
          System.out.print(board.getCell(i, j) + " ");
        }
      }
      System.out.println();
    }
  }
}

