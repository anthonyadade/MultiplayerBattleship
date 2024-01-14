package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The JSON structure for representing a fleet of ships
 *
 * @param carrier the number of carriers in the fleet
 * @param battleship the number of battleships in the fleet
 * @param destroyer the number of destroyers in the fleet
 * @param submarine the number of submarines in the fleet
 */
public record FleetSpecJson(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
	@JsonProperty("SUBMARINE") int submarine) {
}
