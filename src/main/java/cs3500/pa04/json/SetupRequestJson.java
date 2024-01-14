package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The JSON structure for a setup request
 *
 * @param width the width of the board
 * @param height the heigh of the board
 * @param fleetSpec the fleet specs of the fleet
 */
public record SetupRequestJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpecJson fleetSpec) {

  /**
   * The setup method called on a player to setup their game board
   *
   * @param player the player whos game is being set up
   * @return a list of Ship Adapter objects
   */
  public List<ShipAdapter> setupCaller(Player player) {
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, fleetSpec.battleship());
    specifications.put(ShipType.CARRIER, fleetSpec.carrier());
    specifications.put(ShipType.DESTROYER, fleetSpec.destroyer());
    specifications.put(ShipType.SUBMARINE, fleetSpec.submarine());
    List<Ship> ships = player.setup(height, width, specifications);
    List<ShipAdapter> res = new ArrayList<>();

    for (Ship s : ships) {
      res.add(new ShipAdapter(s));
    }
    return res;
  }
}