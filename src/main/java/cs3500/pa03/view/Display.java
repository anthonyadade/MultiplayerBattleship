package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.HashSet;
import java.util.List;

/**
 * Represents the user display
 */
public interface Display {

  /**
   * Displays the welcome screen and prompt
   */
  void displayWelcome();

  /**
   * Displays an invalid input prompt
   *
   * @param wrong - what was invalid
   */
  void displayUhOh(String wrong);

  /**
   * Asks the user to enter the number of each ship
   *
   * @param max - max number of total ships
   */
  void askShipsNum(int max);

  /**
   * Displays the user's board
   *
   * @param ships - user's ships
   * @param height - height of board
   * @param width - width of board
   */
  void displayMyBoard(List<Ship> ships, int height, int width);

  /**
   * Displays the opponent's board
   *
   * @param ships - opponent's ships
   * @param height - height of board
   * @param width - width of board
   * @param shotsTaken - shots you have fired at opponent
   */
  void displayOppBoard(List<Ship> ships, int height, int width, HashSet<Coord> shotsTaken);

  /**
   * Asks the user to enter their desired shots
   *
   * @param numShots - number of shots user will take
   */
  void displaySelectShots(int numShots);

  /**
   * Displays the winner of the game
   *
   * @param winner - who won
   */
  void displayGameOver(String winner);
}
