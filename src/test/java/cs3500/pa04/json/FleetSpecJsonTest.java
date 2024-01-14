package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the FleetSpecJson class
 */
public class FleetSpecJsonTest {

  private ObjectMapper objectMapper;
  private FleetSpecJson fleetSpecJson;
  private String json;

  /**
   * Setup method for the subsequent tests
   */
  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
    fleetSpecJson = new FleetSpecJson(2, 3, 4, 5);
    json = "{\"CARRIER\":2,\"BATTLESHIP\":3,\"DESTROYER\":4,\"SUBMARINE\":5}";
  }

  /**
   * Tests if the FleetSpec is being properly serialized
   *
   * @throws IOException if an error occurs serializing the fleet spec
   */
  @Test
  public void testFleetSpecSerialization() throws IOException {
    String serializedJson = objectMapper.writeValueAsString(fleetSpecJson);
    Assertions.assertEquals(json, serializedJson);
  }

  /**
   * Tests if the FleetSpec is being properly deserialized
   *
   * @throws IOException if an error occurs deserializing the fleet spec
   */
  @Test
  public void testFleetSpecDeserialization() throws IOException {
    FleetSpecJson deserializedFleetSpecJson = objectMapper.readValue(json, FleetSpecJson.class);
    Assertions.assertEquals(fleetSpecJson, deserializedFleetSpecJson);
  }
}

