package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The JSON structure to set up a BattleSalvo game
 *
 * @param fleet the JSON representation of a fleet
 */
public record SetupJson(
    @JsonProperty("fleet") FleetJson fleet) {
}