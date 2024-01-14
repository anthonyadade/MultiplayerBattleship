package cs3500.pa04.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the EndRequestJson Class
 */
public class EndRequestJsonTest {

  /**
   * Tests endRequestJson
   */
  @Test
  public void testEndRequestJson() {
    EndRequestJson endRequestJson = new EndRequestJson("You Win", "Sunk All Opponents"
        + " Ships");

    Assertions.assertEquals("You Win", endRequestJson.result());
    Assertions.assertEquals("Sunk All Opponents Ships", endRequestJson.reason());
  }
}

