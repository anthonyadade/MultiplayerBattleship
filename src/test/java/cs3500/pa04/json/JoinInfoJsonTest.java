package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the JoinInfoJson class
 */
public class JoinInfoJsonTest {

  private ObjectMapper objectMapper;
  private JoinInfoJson joinInfoJson;
  private String json;

  /**
   * Setup method for the subsequent tests
   */
  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
    joinInfoJson = new JoinInfoJson("John Doe", "BattleSalvo");
    json = "{\"name\":\"John Doe\",\"game-type\":\"BattleSalvo\"}";
  }

  /**
   * Tests if Join is being properly serialized
   *
   * @throws IOException if an error occurs serializing join
   */
  @Test
  public void testJoinSerialization() throws IOException {
    String serializedJson = objectMapper.writeValueAsString(joinInfoJson);
    Assertions.assertEquals(json, serializedJson);
  }

  /**
   * Tests if Join is being properly deserialized
   *
   * @throws IOException if an error occurs deserializing join
   */
  @Test
  public void testJoinDeserialization() throws IOException {
    JoinInfoJson deserializedJoinInfoJson = objectMapper.readValue(json, JoinInfoJson.class);
    Assertions.assertEquals(joinInfoJson, deserializedJoinInfoJson);
  }
}

