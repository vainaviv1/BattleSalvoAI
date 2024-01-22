package cs3500.pa04;

import cs3500.pa03.controller.BattleSalvoController;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point. Calls method to either start the local game or connect with the server
   * and start the game.
   *
   * @param args - either no arguments or the host and port for the server
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      runLocalGame();
    } else if (args.length == 2) {
      try {
        runServerGame(args[0], Integer.parseInt(args[1]));
      } catch (IOException | IllegalStateException e) {
        throw new RuntimeException("Unable to connect to the server.");
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Second argument should be an integer. "
            + "Format: [host] [part].");
      }
    } else {
      throw new IllegalArgumentException("Please input either the host and port or no arguments.");
    }
  }

  /**
   * Creates 2 players: an AI player and a manual player. Creates a BattleSalvoController
   * giving it the 2 players that were created and calls the startGame() method.
   */
  private static void runLocalGame() {
    InputStreamReader isr = new InputStreamReader(System.in);
    Scanner s = new Scanner(isr);
    Player p1 = new ManualPlayer("Nina", new Random(), s, System.out);
    Player p2 = new AiPlayer("AI Player", new Random());
    BattleSalvoController bsc = new BattleSalvoController(s,
        System.out, p1, p2);
    bsc.startGame();
  }

  /**
   * Connects to the server and gives it an AiPlayer. Creates a ProxyController to run and interact
   * with the server.
   *
   * @param host the host for the server
   * @param port the port for the server
   * @throws IOException if there is a communication issue with the server
   */
  private static void runServerGame(String host, int port) throws IOException  {
    Socket socket = new Socket(host, port);

    Player aiPlayer = new AiPlayer("nileenajohn", new Random());
    ProxyController proxyController;
    try {
      proxyController = new ProxyController(socket, aiPlayer);
      proxyController.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}