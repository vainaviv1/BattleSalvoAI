package cs3500.pa03;

import java.io.IOException;
import java.util.*;

public class Controller {
  private int height;
  private int width;
  private List<Integer> fleetSizes;
  private View view;
  private Board playerBoard;
  private Board opponentBoard;
  private Player player;
  private Player opponent;

  /**
   * Constructs a Controller object with the specified height, width, and fleet sizes.
   * @param height of the game baord
   * @param width of the gameboard
   * @param fleetSizes the size of the ships in the fleet
   */

  public Controller(int height, int width, List<Integer> fleetSizes) {
    this.height = height;
    this.width = width;
    this.fleetSizes = fleetSizes;
    this.view = new View();
    this.player = new ConsolePlayer("Player");
    this.opponent = new AiPlayer("AI");
  }

  public void run() throws IOException {
    run();
  }
  public void checkInput() {
    System.out.println("Hello! Welcome to the OOD BattleSalvo Game!");
    System.out.println("Please enter a valid height and width below:");
    System.out.println("------------------------------------------------------");

    Scanner scanner = new Scanner(System.in);
    this.height = 0;
    this.width = 0;
    boolean isValidBoard= true;

    while (isValidBoard) {
      height = scanner.nextInt();
      width = scanner.nextInt();
      scanner.nextLine();
      if ((height <= 15 && height >=6) || (width <= 15 && width >=6)) {
        isValidBoard = false;
      } else {
        System.out.println("Uh Oh! You've entered invalid dimensions. " +
            "Please remember that the height and width\n" +
            "of the game must be in the range [6, 15], inclusive. Try again!");
      }
    }

    int num=0;
    if (height<width) {
      num = height;
    } else {
      num = width;
    }

    System.out.println("Please enter your fleet in the order [Carrier, Battleship, Destroyer, " +
        "Submarine].");
    System.out.println("Remember, your fleet may not exceed size " + (num) + ".");
    System.out.println("------------------------------------------------------------------------" +
        "--------");

    int c= 0;
    int b= 0;
    int d= 0;
    int s= 0;
    boolean isValidFleet= true;
    while (isValidFleet) {
      c= scanner.nextInt();
      b= scanner.nextInt();
      d= scanner.nextInt();
      s= scanner.nextInt();
      scanner.nextLine();
      if (c>=1 && b>=1 && d>=1 && s>=1 && (c+b+d+s <=num)){
        fleetSizes.add(c);
        fleetSizes.add(b);
        fleetSizes.add(d);
        fleetSizes.add(s);
        isValidFleet= false;
      } else {
        System.out.println("Uh Oh! You've entered invalid fleet sizes.\n" +
            "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
            "Remember, your fleet may not exceed size "+ num +".");
      }
    }
    this.playerBoard = new Board(height, width);
    this.opponentBoard = new Board(height, width);
  }

  private Map<ShipType, Integer> getShipSpecifications() {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, fleetSizes.get(0));
    specifications.put(ShipType.BATTLESHIP, fleetSizes.get(1));
    specifications.put(ShipType.DESTROYER, fleetSizes.get(2));
    specifications.put(ShipType.SUBMARINE, fleetSizes.get(3));
    return specifications;
  }
  public void startGame() {
    List<Ship> playerShips = player.setup(height, width, getShipSpecifications());
    List<Ship> opponentShips = opponent.setup(height, width, getShipSpecifications());
    playerBoard.placeShips(playerShips);
    System.out.println("Opponent Board Data:");
    view.printBoardOpponent(opponentBoard);
    opponentBoard.placeShips(opponentShips);
    System.out.println("\n"+"Your Board:");
    view.printBoard(playerBoard);

    while (!gameOver()) {
      List<Coord> playerShots = player.takeShots();
      while (!isValidShots(playerShots)) {
        System.out.println("Invalid shots. Please enter shots again.");
        playerShots = player.takeShots();
      }
      List<Coord> opponentShots = opponent.takeShots();
      List<Coord> playerHits = opponent.reportDamage(playerShots);
      List<Coord> opponentHits = player.reportDamage(opponentShots);
      player.successfulHits(playerHits);
      opponent.successfulHits(opponentHits);
      opponentBoard.reportDamage(opponentHits);
      playerBoard.reportDamage(playerHits);
      view.updateBoards(opponentBoard, playerBoard, playerShots, opponentShots, playerHits, opponentHits);
    }

    GameResult result = determineResult();
    player.endGame(result, "");
    opponent.endGame(result, "");
  }


  private boolean gameOver() {
    return playerBoard.allShipsSunk() || opponentBoard.allShipsSunk();
  }


  private boolean isValidShots(List<Coord> shots) {
    Set<Coord> uniqueShots = new HashSet<>(shots);
    return uniqueShots.size() == shots.size();
  }

  private GameResult determineResult() {
    if (playerBoard.allShipsSunk() && opponentBoard.allShipsSunk()) {
      return GameResult.TIE;
    } else if (playerBoard.allShipsSunk()) {
      return GameResult.LOSE;
    } else {
      return GameResult.WIN;
    }
  }
}
