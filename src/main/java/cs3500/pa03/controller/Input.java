package cs3500.pa03.controller;

import cs3500.pa03.model.ShipType;
import java.util.List;
import java.util.Map;

/**
 * Represents user input
 */
public interface Input {

  /**
   * Checks the user entered width and height
   *
   * @return - the height followed by width in array
   */
  int[] checkWidthHeight();

  /**
   * Checks that the user entered a proper amount of ships
   *
   * @param max - max number of ships
   * @return - a map of a ship to the number of that ship
   */
  Map<ShipType, Integer> checkShipsNum(int max);

  /**
   * Checks that the user selected valid shots
   *
   * @param height - height of the board
   * @param width - width of the board
   * @return - a list of the integers that will be put into coordinate objects
   */
  List<Integer> checkShotsSelected(int height, int width);

}
