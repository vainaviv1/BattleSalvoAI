package cs3500.pa03;


import org.junit.jupiter.api.BeforeEach;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Tests the Controller class
 */
public class ControllerTest {

  private List<Integer> fleetSizes;
  private Controller controller;
  private Player player;
  private Player opponent;
  private Board playerBoard;
  private Board opponentBoard;
  private View view;

  /**
   * Tests the Controller setUp method
   */
  @BeforeEach
  public void setUp() {
    fleetSizes = Arrays.asList(1, 1, 1, 1);
    controller = new Controller(0, 0, fleetSizes);
    player = mock(Player.class);
    opponent = mock(Player.class);
    playerBoard = mock(Board.class);
    opponentBoard = mock(Board.class);
    view = mock(View.class);
  }

}