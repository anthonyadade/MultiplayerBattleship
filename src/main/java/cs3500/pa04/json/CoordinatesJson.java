package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent CoordinatesJson which is a list of Coord objects
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") List<CoordJson> coordinates) {

  /**
   * Will return the list of Coord Objects
   *
   * @return the list of Coord objects
   */
  @Override
  public List<CoordJson> coordinates() {
    return coordinates;
  }

  /**
   * Gets the non-jsonified coords
   *
   * @return coords
   */
  public List<Coord> getCoordinates() {
    List<Coord> res = new ArrayList<>();
    for (CoordJson c : coordinates) {
      res.add(new Coord(c.x(), c.y()));
    }
    return res;
  }

  /**
   * Gets the jsonified coords
   *
   * @param coords - non-jsonified coords
   * @return - json coords
   */
  public List<CoordJson> getCoordsJson(List<Coord> coords) {
    List<CoordJson> res = new ArrayList<>();
    for (Coord c : coords) {
      res.add(new CoordJson(c.getX(), c.getY()));
    }
    return res;
  }
}
