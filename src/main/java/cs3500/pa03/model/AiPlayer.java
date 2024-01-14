package cs3500.pa03.model;

import static java.lang.Math.min;

import cs3500.pa03.view.ConsoleDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an AI Player
 */
public class AiPlayer extends PlayerAbstract  {
  private static List<Coord> shotsTaken = new ArrayList<>();
  private static List<Coord> hitsOnOpponent = new ArrayList<>();

  /**
   * Not needed yet
   *
   * @return - null
   */
  @Override
  public String name() {
    return "anthonyadade";
  }


  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    Random random = new Random();
    List<Coord> res = new ArrayList<>();
    //int numShips = new Gameplay().getNumAiShips();
    int numShips = new Gameplay().getNumRemainingShips(myShips);
    new ConsoleDisplay().displayMyBoard(myShips, height, width);

    //System.out.println(numShips);
    int numMaxShots = min(height * width - shotsTaken.size(), numShips);
    //System.out.println("ai fired " + numMaxShots + " shots");

    for (int i = 0; i < numMaxShots; i++) {
      while (true) {
        int xpos = random.nextInt(width);
        int ypos = random.nextInt(height);
        Coord curCoord = new Coord(xpos, ypos);
        //Coord curCoord = new Coord(ypos, xpos);
        if (!shotsTaken.contains(curCoord)) {
          shotsTaken.add(curCoord);
          res.add(curCoord);
          break;
        }
      }
    }

    return res;
  }

  /**
   * Not needed yet
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    hitsOnOpponent.addAll(shotsThatHitOpponentShips);
  }

  /**
   * Prints the reason and result for the end of the game to the user
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    String endText = result.getEndText();

    System.out.println("Result: " + result);
    System.out.println("Reason: " + reason);
  }
}
