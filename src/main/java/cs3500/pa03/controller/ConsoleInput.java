package cs3500.pa03.controller;

import cs3500.pa03.model.Gameplay;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the Command Line Input
 */
public class ConsoleInput implements Input {

  private static Scanner input = null;

  /**
   * Checks the user entered width and height
   *
   * @return - the height followed by width in array
   */
  @Override
  public int[] checkWidthHeight() {
    if (input == null) {
      input = new Scanner(System.in);
    }
    try {
      int height = input.nextInt();
      int width = input.nextInt();
      if (height <= 15 && height >= 6 && width >= 6 && width <= 15) {
        return new int[] {height, width};
      } else {
        return null;
      }
    } catch (InputMismatchException e) {
      input.next();
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Checks that the user entered a proper amount of ships
   *
   * @param max - max number of ships
   * @return - a map of a ship to the number of that ship
   */
  @Override
  public Map<ShipType, Integer> checkShipsNum(int max) {
    try {
      Map<ShipType, Integer> specifications = new HashMap<>();
      specifications.put(ShipType.CARRIER, input.nextInt());
      specifications.put(ShipType.BATTLESHIP, input.nextInt());
      specifications.put(ShipType.DESTROYER, input.nextInt());
      specifications.put(ShipType.SUBMARINE, input.nextInt());
      int sum = 0;
      for (Integer i : specifications.values()) {
        sum += i;
      }
      if (sum <= max) {
        return specifications;
      }
    } catch (InputMismatchException e) {
      input.next();
      return null;
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  /**
   * Checks that the user selected valid shots
   *
   * @param height - height of the board
   * @param width - width of the board
   * @return - a list of the integers that will be put into coordinate objects
   */
  @Override
  public List<Integer> checkShotsSelected(int height, int width) {
    List<Integer> shots = new ArrayList<>();
    int numShips = new Gameplay().getNumHumanShips();
    try {
      while (shots.size() < new Gameplay().getNumHumanShots(height, width, numShips) * 2) {
        int num = input.nextInt();
        int maxDimension;
        if (shots.size() % 2 == 0) {
          maxDimension = height;
        } else {
          maxDimension = width;
        }
        if (num >= 0 && num < maxDimension) {
          shots.add(num);
        } else {
          return null;
        }
      }
    } catch (InputMismatchException e) {
      input.next();
      return null;
    } catch (Exception e) {
      return null;
    }
    return shots;
  }

  /**
   * Resets the scanner used to read inputs to null
   */
  public void scannerReset() {
    input = null;
  }
}
