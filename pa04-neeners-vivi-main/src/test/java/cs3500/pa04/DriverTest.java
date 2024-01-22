package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DriverTest {
  private String[] args;

  /**
   * Tests that main throws an exception when arguments is not 0 arguments or 2 arguments.
   */
  @Test
  public void testDriverInvalidNumArgs() {
    args = new String[3];
    args[0] = new String("hi");
    args[1] = new String("2");
    args[2] = new String("hello");

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> Driver.main(args));
    assertEquals("Please input either the host and port or no arguments.",
        exception.getMessage());
  }

  /**
   * Tests that main throws an exception when the second argument is not a number.
   */
  @Test
  public void testDriverInvalidPortNumber() {
    args = new String[2];
    args[0] = new String("0.0.0.0");
    args[1] = new String("not a number");

    Throwable exception = assertThrows(IllegalArgumentException.class, () -> Driver.main(args));
    assertEquals("Second argument should be an integer. Format: [host] [part].",
        exception.getMessage());
  }
}