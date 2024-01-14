package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * Test for ConsoleInput
 */
class ConsoleInputTest {

  /**
   * Test for checkWidthHeight
   */
  @Test
  void testCheckWidthHeight() {
    ConsoleInput input = new ConsoleInput();
    input.scannerReset();
    final InputStream sysInBackup = System.in; // backup System.in to restore it later
    ByteArrayInputStream in = new ByteArrayInputStream(("6 6" + System.lineSeparator()).getBytes());
    System.setIn(in);
    int[] expected = {6, 6};
    System.out.println("Started w Test");
    assertArrayEquals(expected, input.checkWidthHeight());
    in = new ByteArrayInputStream(("invalid" + System.lineSeparator()).getBytes());
    System.setIn(in);
    assertArrayEquals(null, input.checkWidthHeight());
    System.out.println("Finished w  Test");
    System.setIn(sysInBackup);
  }
}