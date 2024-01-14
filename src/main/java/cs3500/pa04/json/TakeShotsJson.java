package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.json.CoordinatesJson;

public record TakeShotsJson(
    @JsonProperty("take-shots") String takeShots,
    @JsonProperty("coordinates") CoordinatesJson coordinates) {
}