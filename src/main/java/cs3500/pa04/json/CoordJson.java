package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class to represent a CoordJson with an x and y value
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {

  /**
   * Will return the x-value of a coordinate
   *
   * @return the 'x' value of a coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Will return the y-value of a coordinate
   *
   * @return the 'y' value of a coordinate
   */
  public int getY() {
    return this.y;
  }

}
