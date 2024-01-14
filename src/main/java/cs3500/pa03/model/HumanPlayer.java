package cs3500.pa03.model;

import cs3500.pa03.controller.ConsoleInput;
import cs3500.pa03.view.ConsoleDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a human player
 */
public class HumanPlayer extends PlayerAbstract {

  /**
   * Not needed yet
   *
   * @return - null for now
   */
  @Override
  public String name() {
    return null;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    List<Integer> shots = new ConsoleInput().checkShotsSelected(height, width);
    while (shots == null) {
      new ConsoleDisplay().displayUhOh("invalid shots");
      shots = new ConsoleInput().checkShotsSelected(height, width);
    }
    List<Coord> res = new ArrayList<>();
    for (int i = 0; i < shots.size(); i++) {
      res.add(new Coord(shots.get(i), shots.get(i + 1)));
      i++;
    }
    return res;
  }

  /**
   * Not needed
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {

  }

  /**
   * Handles the end of a BattleSalvo game
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }
}