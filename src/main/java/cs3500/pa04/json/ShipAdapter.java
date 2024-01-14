package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Ship;

/**
 * An adapter class which converts the Ship object to a JSON friendly representation
 */
public class ShipAdapter {

  private CoordJson coord;
  private int length;
  private String direction;

  /**
   * Constructor for a Ship object which takes a Ship as the paramter
   *
   * @param myShip the ship object that will be adapted
   */
  public ShipAdapter(Ship myShip) {
    this.coord = new CoordJson(myShip.getCoord().getX(), myShip.getCoord().getY());
    this.length = myShip.getShipType().getSize();
    this.direction = "VERTICAL";
    if (myShip.getHorizontal()) {
      this.direction = "HORIZONTAL";
    }
  }

  /**
   * A constructor for a ShipAdapter object which takes a Coord, a boolean, and a ShipType
   *
   * @param coord the coordinate for the Ship
   * @param length length of the ship
   * @param direction the orientation of the ship
   */
  @JsonCreator
  public ShipAdapter(@JsonProperty("coord") CoordJson coord,
                     @JsonProperty("length") int length,
                     @JsonProperty("direction") String direction) {
  }

  /**
   * Will return the coord of the Ship
   *
   * @return the coord of the ship
   */
  public CoordJson getCoord() {
    return this.coord;
  }

  /**
   * Will return the orientation of the ship
   *
   * @return "VERTICAL" or "HORIZONTAL" depending on the orientation of the ship
   */
  public String getDirection() {
    return this.direction;
  }

  /**
   * Will return the length of the ship
   *
   * @return the length of the ship
   */
  public int getLength() {
    return this.length;
  }
}
