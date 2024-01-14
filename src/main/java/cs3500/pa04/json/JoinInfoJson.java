package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The JSON structure for joining a BattleSalvo game
 *
 * @param name the name of the player
 * @param gameType the type of BattleSalvo game to be joined
 */
public record JoinInfoJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType) {
}