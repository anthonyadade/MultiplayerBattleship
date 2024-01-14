package cs3500.pa04.json;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the FleetJson class
 */
public class FleetJsonTest {
  private FleetJson fleetJson;
  private List<ShipAdapter> ships;

  /**
   * Setup method for subsequent tests
   */
  @BeforeEach
  public void setUp() {
    ships = new ArrayList<>();
    fleetJson = new FleetJson(ships);
  }

  /**
   * Tests the getShips method
   */
  @Test
  public void testGetShips() {
    List<ShipAdapter> retrievedShips = fleetJson.ships();

    Assertions.assertEquals(ships, retrievedShips);
  }
}
