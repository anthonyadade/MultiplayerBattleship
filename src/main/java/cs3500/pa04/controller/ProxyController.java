package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa03.controller.ConsoleInput;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.ConsoleDisplay;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndRequestJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinInfoJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupRequestJson;
import cs3500.pa04.json.ShipAdapter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A proxy Controller class to connect our AI Player to the server
 */
public class ProxyController {
  private final Socket server;
  private final Player ourPlayer;
  private final ObjectMapper mapper = new ObjectMapper();
  private final PrintStream out;

  /**
   * A constructor for a ProxyController object which has a socket and our AI Player
   *
   * @param server The socket used to connect to the server
   * @param ourPlayer our AI Player used in the BattleSalvo game
   * @throws IOException if an error occurs while connecting to the server
   */
  public ProxyController(Socket server, Player ourPlayer) throws IOException {
    this.server = server;
    this.ourPlayer = ourPlayer;
    this.out = new PrintStream(server.getOutputStream());
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJson. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(
          this.server.getInputStream());

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        //System.out.println(message);
        delegateMessage(message);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Error parsing information and sending it to the server");
      // Disconnected from server or parsing exception;
    }
  }

  /**
   * Determines the type of request the server has sent ("guess" or "win") and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJson used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if (name.equals("join")) {
      joinServer();
    }
    if (name.equals("setup")) {
      setupServer(arguments);
    }
    if (name.equals("take-shots")) {
      takeShotsServer();
    }
    if (name.equals("report-damage")) {
      reportDamageServer(arguments);
    }
    if (name.equals("successful-hits")) {
      successfulHitsServer(arguments);
    }
    if (name.equals("end-game")) {
      endGameServer(arguments);
    } else if (name.equals(null)) {
      throw new IllegalArgumentException("Error Delegating the Message");
    }
  }

  private void endGameServer(JsonNode arguments) {
    EndRequestJson endRequest = this.mapper.convertValue(arguments, EndRequestJson.class);

    String result = endRequest.result();
    String reason = endRequest.reason();
    //System.out.println(reason);
    //System.out.println(result);
    ourPlayer.endGame(GameResult.valueOf(result), reason);

    MessageJson endJsonMsg = new MessageJson("end-game", new ObjectMapper().createObjectNode());
    JsonNode jsonResponse = JsonUtils.serializeRecord(endJsonMsg);

    this.out.println(jsonResponse);

    try {
      this.server.close();
    } catch (IOException e) {
      System.out.println("Could not close the socket at the end of game");
    }
  }

  private void successfulHitsServer(JsonNode arguments) {
    CoordinatesJson coords = this.mapper.convertValue(arguments, CoordinatesJson.class);

    ourPlayer.successfulHits(coords.getCoordinates());

    MessageJson response = new MessageJson("successful-hits",
        new ObjectMapper().createObjectNode());

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);

    this.out.println(jsonResponse);
  }


  private void reportDamageServer(JsonNode arguments) {
    CoordinatesJson coords = this.mapper.convertValue(arguments, CoordinatesJson.class);

    List<Coord> hits = ourPlayer.reportDamage(coords.getCoordinates());

    List<CoordJson> hitsJson = coords.getCoordsJson(hits);

    CoordinatesJson coordsJson = new CoordinatesJson(hitsJson);

    JsonNode jsonNodeHits = JsonUtils.serializeRecord(coordsJson);

    MessageJson response = new MessageJson("report-damage", jsonNodeHits);

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);

    this.out.println(jsonResponse);
  }

  private int numShots = 0;

  private void takeShotsServer() {
    List<CoordJson> shots = new CoordinatesJson(new ArrayList<>()).getCoordsJson(ourPlayer.takeShots());
    CoordinatesJson shotsJson = new CoordinatesJson(shots);
    numShots += shots.size();
    JsonNode nodeShots = JsonUtils.serializeRecord(shotsJson);
    System.out.println(numShots + " " + nodeShots);
    MessageJson msgShots = new MessageJson("take-shots", nodeShots);

    JsonNode jsonResponse = JsonUtils.serializeRecord(msgShots);
    this.out.println(jsonResponse);
  }

/*
  private void takeShotsServer() {
    List<CoordJson> shots = new CoordinatesJson(new ArrayList<>()).getCoordsJson(ourPlayer.takeShots());
    CoordinatesJson shotsJson = new CoordinatesJson(shots);
    numShots += shots.size();
    MessageJson msgShots;

    if (shots.size() != 0) {
      JsonNode nodeShots = JsonUtils.serializeRecord(shotsJson);
      System.out.println(numShots + " " + nodeShots);
      msgShots = new MessageJson("take-shots", nodeShots);
    } else {
      msgShots = new MessageJson("take-shots", new ObjectMapper().createObjectNode());
    }
    JsonNode jsonResponse = JsonUtils.serializeRecord(msgShots);

    this.out.println(jsonResponse);
  }
 */


  void joinServer() {
    JoinInfoJson joinResponse = new JoinInfoJson(ourPlayer.name(), GameType.SINGLE.toString());
    JsonNode jsonResponse = JsonUtils.serializeRecord(joinResponse);

    MessageJson joinJson = new MessageJson("join", jsonResponse);
    JsonNode finalJson = JsonUtils.serializeRecord(joinJson);

    this.out.println(finalJson);
  }

  public void setupServer(JsonNode arguments) {
    SetupRequestJson setupArgs = this.mapper.convertValue(arguments, SetupRequestJson.class);

    List<ShipAdapter> ships = setupArgs.setupCaller(this.ourPlayer);

    //System.out.println(ships.size());
    System.out.println("Width: " + setupArgs.width());
    System.out.println("Height: " + setupArgs.height());

    FleetJson fleet = new FleetJson(ships);

    JsonNode jsonResponse = JsonUtils.serializeRecord(fleet);

    MessageJson fleetMessage = new MessageJson("setup", jsonResponse);

    JsonNode fleetMsgJson = JsonUtils.serializeRecord(fleetMessage);

    this.out.println(fleetMsgJson);
  }
}
