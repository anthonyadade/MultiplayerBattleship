package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the MessageJson class
 */
public class MessageJsonTest {

  private String messageName;
  private JsonNode arguments;

  /**
   * Setup method for the subsequent tests
   */
  @BeforeEach
  public void setUp() {
    messageName = "send Help";
    arguments = new ObjectMapper().createObjectNode();
  }

  /**
   * Tests the getMessageName method
   */
  @Test
  public void testGetMessageName() {
    MessageJson messageJson = new MessageJson(messageName, arguments);
    String retrievedMessageName = messageJson.messageName();

    Assertions.assertEquals(messageName, retrievedMessageName);
  }

  /**
   * Tests the getArguments method
   */
  @Test
  public void testGetArguments() {
    MessageJson messageJson = new MessageJson(messageName, arguments);
    JsonNode retrievedArguments = messageJson.arguments();

    Assertions.assertEquals(arguments, retrievedArguments);
  }
}

