package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.GameType;
import org.junit.jupiter.api.Test;

/**
 * Test for GameTypeTest
 */
public class GameTypeTest {

  /**
   * Test for to string
   */
  @Test
  public void testToString() {
    assertEquals("SINGLE", GameType.SINGLE.toString());
    assertEquals("MULTI", GameType.MULTI.toString());
  }

}

