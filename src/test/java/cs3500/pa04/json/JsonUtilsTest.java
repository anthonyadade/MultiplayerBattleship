package cs3500.pa04.json;

import cs3500.pa03.model.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;


/**
 * Tests for the JsonUtils class
 */
public class JsonUtilsTest {
  private Map<ShipType, Integer> specifications;

  /**
   * Setup method for the subsequent tests
   */
  @BeforeEach
  public void setUp() {
    specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.DESTROYER, 3);
    specifications.put(ShipType.SUBMARINE, 4);
  }
}

