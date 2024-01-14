package cs3500.pa04.json;

//import cs3500.pa03.model.MockPlayer;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SetupRequestJson class
 */
public class SetupRequestJsonTest {

  private int width;
  private int height;
  private FleetSpecJson fleetSpec;
  //private MockPlayer playerMock;
  private SetupRequestJson setupRequest;

  /**
   * Setup method for the subsequent tests
   */
  @BeforeEach
  public void setUp() {
    width = 10;
    height = 8;
    fleetSpec = new FleetSpecJson(1, 2, 3, 4);
    //playerMock = new MockPlayer();
    setupRequest = new SetupRequestJson(width, height, fleetSpec);
  }

  /**
   * Tests the getWidth method
   */
  @Test
  public void testGetWidth() {
    SetupRequestJson requestJson = new SetupRequestJson(width, height, fleetSpec);
    int retrievedWidth = requestJson.width();

    Assertions.assertEquals(width, retrievedWidth);
  }

  /**
   * Tests the getHeight method
   */
  @Test
  public void testGetHeight() {
    SetupRequestJson requestJson = new SetupRequestJson(width, height, fleetSpec);
    int retrievedHeight = requestJson.height();

    Assertions.assertEquals(height, retrievedHeight);
  }

  /**
   * Tests the getFleetSpec method
   */
  @Test
  public void testGetFleetSpec() {
    SetupRequestJson requestJson = new SetupRequestJson(width, height, fleetSpec);
    FleetSpecJson retrievedFleetSpec = requestJson.fleetSpec();

    Assertions.assertEquals(fleetSpec, retrievedFleetSpec);
  }

}



