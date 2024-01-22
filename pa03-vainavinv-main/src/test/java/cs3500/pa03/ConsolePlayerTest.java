package cs3500.pa03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


/**
 * Tests the ConsolePlayer class
 */
public class ConsolePlayerTest {

  private ConsolePlayer player;

  @BeforeEach
  public void setup() {
    player = new ConsolePlayer("Player");
  }

  /**
   * Tests the ConsolePlayer SuccessfulHits method
   */
  @Test
  public void testSuccessfulHits() {
    List<Coord> shotsThatHit = new ArrayList<>();
    shotsThatHit.add(new Coord(1, 2));
    shotsThatHit.add(new Coord(3, 4));
    shotsThatHit.add(new Coord(5, 6));

    player.successfulHits(shotsThatHit);

  }

  /**
   * Tests the ConsolePlayer endGame method
   */
  @Test
  public void testEndGame() {
    GameResult result = GameResult.WIN;
    String reason = "You sunk all opponent's ships!";

    player.endGame(result, reason);


  }


}
