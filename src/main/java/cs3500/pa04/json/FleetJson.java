package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A class to represent FleetJson which is a list of ships
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipAdapter> ships) {

  /**
   * Will return the list of Ship objects
   *
   * @return the list of Ship objects
   */
  @Override
  public List<ShipAdapter> ships() {
    return ships;
  }
}
