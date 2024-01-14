package cs3500.pa03.model;

import java.util.ArrayList;

/**
 * Represents a ship in the game
 */

public class Ship {

  private final Coord coord;
  private final boolean horizontal;
  private final ShipType shipType;
  private ArrayList<Coord> hits = new ArrayList<>();

  Ship(Coord coord, boolean horizontal, ShipType shipType) {
    this.coord = coord;
    this.horizontal = horizontal;
    this.shipType = shipType;
  }

  /**
   * Gets the coordinates that this ship occupies
   *
   * @return the coordinates that this ship occupies
   */
  public ArrayList<Coord> occupiedCoords() {
    ArrayList<Coord> res = new ArrayList<>();
    int xend = coord.getX() + 1;
    int yend = coord.getY() + shipType.getSize();
    if (horizontal) {
      xend = coord.getX() + shipType.getSize();
      yend = coord.getY() + 1;
    }
    for (int x = coord.getX(); x < xend; x++) {
      for (int y = coord.getY(); y < yend; y++) {
        res.add(new Coord(x, y));
      }
    }
    return res;
  }

  /**
   * Gets this ship's type
   *
   * @return this ship's type
   */
  public ShipType getShipType() {
    return shipType;
  }

  /**
   * Gets the orientation of this ship
   *
   * @return - true if horizontal, false if vertical
   */
  public boolean getHorizontal() {
    return horizontal;
  }

  /**
   * Check if the given ship collides with this ship
   *
   * @param otherShip - the ship we are checking to see if we collide with
   * @return - true if they collide, false if they don't
   */
  public boolean collidesWithMe(Ship otherShip) {
    ArrayList<Coord> checkCollision = new ArrayList<>(otherShip.occupiedCoords());
    checkCollision.retainAll(this.occupiedCoords());
    return checkCollision.size() != 0; // if the ships share coordinates, this will be greater
    // then 0
  }

  /**
   * Gets the coordinate of this ship
   *
   * @return the coordinate of this ship
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * Gets the hits that this ship has taken
   *
   * @return - hits on this ship
   */
  public ArrayList<Coord> getHits() {
    return hits;
  }

  /**
   * Adds a hit to this ship
   *
   * @param hit - coordinate of hit
   * @return - true if it's a new hit, false if it's an old hit (already been hit there)
   */
  public boolean addHit(Coord hit) {
    if (hits.contains(hit)) {
      return false;
    } else {
      hits.add(hit);
      return true;
    }
  }

  /**
   * Checks if this ship is sunken
   *
   * @return True if sunken, false otherwise
   */
  public boolean isSunken() {
    return hits.containsAll(occupiedCoords());
  }

}
