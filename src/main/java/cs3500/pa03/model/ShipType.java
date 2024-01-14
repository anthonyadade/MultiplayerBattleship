package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a type of ship
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  private final int size;

  private ShipType(int size) {
    this.size = size;
  }

  /**
   * Gets the size of the current ship type
   *
   * @return - the size of the current ship type
   */
  public int getSize() {
    return size;
  }

  /**
   * Lists shiptypes from biggest to smallest
   *
   * @return - List of shiptypes from biggest to smallest
   */
  public List<ShipType> biggestToSmallest() {
    List<ShipType> res = new ArrayList<>();
    for (int i = 0; i < ShipType.values().length; i++) {
      int j = 0;
      while (j < res.size() && ShipType.values()[i].size < res.get(j).size) {
        j++;
      }
      res.add(j, ShipType.values()[i]);
    }
    return res;
  }

}
