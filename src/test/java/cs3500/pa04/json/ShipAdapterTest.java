package cs3500.pa04.json;

import org.junit.jupiter.api.BeforeEach;

/**
 * Tests for the ShipAdapter class
 */
public class ShipAdapterTest {

  private CoordJson coordJson;
  private ShipAdapter shipAdapter;

  /**
   * setup method for the subsequent tests beta
   */
  @BeforeEach
  public void setUp() {
    coordJson = new CoordJson(3, 5);
    shipAdapter = new ShipAdapter(coordJson, 4, "HORIZONTAL");
  }
}