package cs3500.pa03.model;

import java.util.Objects;

/**
 * Represents a coordinate on the board
 */
public class Coord {

  @Override
  public int hashCode() {
    return Objects.hash(xpos, ypos);
  }

  private final int xpos;
  private final int ypos;

  public Coord(int x, int y) {
    this.xpos = x;
    this.ypos = y;
  }

  /**
   * Gets the x value
   *
   * @return - x position
   */
  public int getX() {
    return xpos;
  }

  /**
   * Gets the y value
   *
   * @return - y position
   */
  public int getY() {
    return ypos;
  }

  /**
   * Rewrote hashCode because it is good practice when rewriting equals
   *
   * @return - object's hashcode
   */
/*
  @Override
  public int hashCode() {
    return Integer.valueOf(xpos).hashCode() * 1000 + Integer.valueOf(ypos).hashCode();
  }
*/
  /**
   * Rewrote equals so coordinates can be compared by their x and y values
   *
   * @param o - object checking to see if equal to this object
   * @return - true if equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coord)) {
      return false;
    }
    Coord c = (Coord) o;
    int otherx = c.getX();
    int othery = c.getY();
    return otherx == xpos && othery == ypos;
  }

}
