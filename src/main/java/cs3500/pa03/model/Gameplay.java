package cs3500.pa03.model;

import static java.lang.Math.min;

import cs3500.pa03.controller.ConsoleInput;
import cs3500.pa03.view.ConsoleDisplay;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Represents the gameplay
 */
public class Gameplay {

  private static HashSet<Coord> allHumanShots = new HashSet<>();
  private HashSet<Coord> lastHumanShots = new HashSet<>();
  private static int numAiShips;
  private static int numHumanShips;

  /**
   * Starts the program
   */
  public void start() {
    ConsoleDisplay display = new ConsoleDisplay();
    display.displayWelcome();
    ConsoleInput humanIn = new ConsoleInput();
    int[] hieghtWidth = humanIn.checkWidthHeight();
    while (hieghtWidth == null) {
      display.displayUhOh("invalid dimensions");
      hieghtWidth = humanIn.checkWidthHeight();
    }
    int maxFleetSize = hieghtWidth[1];
    if (hieghtWidth[0] < hieghtWidth[1]) {
      maxFleetSize = hieghtWidth[0];
    }
    display.askShipsNum(maxFleetSize);
    Map<ShipType, Integer> specifications = humanIn.checkShipsNum(maxFleetSize);
    while (specifications == null) {
      display.displayUhOh("invalid fleet sizes");
      specifications = humanIn.checkShipsNum(maxFleetSize);
    }
    HumanPlayer humanPlayer = new HumanPlayer();
    AiPlayer aiPlayer = new AiPlayer();
    List<Ship> humanBoard = humanPlayer.setup(hieghtWidth[0], hieghtWidth[1], specifications);
    List<Ship> aiBoard = aiPlayer.setup(hieghtWidth[0], hieghtWidth[1], specifications);
    numHumanShips = humanBoard.size();
    numAiShips = aiBoard.size();
    boolean gameOver = false;
    while (!gameOver) {
      gameOver = round(humanBoard, aiBoard, hieghtWidth[0], hieghtWidth[1]);
    }
    GameResult result;
    if (numHumanShips == 0 && numAiShips == 0) {
      result = GameResult.DRAW;
    } else if (numAiShips == 0) {
      result = GameResult.WIN;
    } else {
      result = GameResult.LOSE;
    }
    display.displayGameOver(result.getEndText());
  }

  /**
   * Represents one round of gameplay
   *
   * @param humanBoard - human's board
   * @param aiBoard -  ai's board
   * @param height - height of board
   * @param width - width of board
   * @return - true if game is over, false otherwise
   */
  public boolean round(List<Ship> humanBoard, List<Ship> aiBoard, int height, int width) {
    ConsoleDisplay display = new ConsoleDisplay();
    display.displayOppBoard(aiBoard, height, width, lastHumanShots);
    display.displayMyBoard(humanBoard, height, width);
    display.displaySelectShots(getNumHumanShots(height, width, numHumanShips));
    HumanPlayer humanPlayer = new HumanPlayer();
    AiPlayer aiPlayer = new AiPlayer();
    List<Coord> humanShots = humanPlayer.takeShots();
    allHumanShots.addAll(humanShots);
    lastHumanShots = new HashSet<>(humanShots);
    List<Coord> aiShots = aiPlayer.takeShots();
    humanPlayer.reportDamage(aiShots);
    aiPlayer.reportDamage(humanShots);

    numHumanShips = getNumRemainingShips(humanBoard);
    numAiShips = getNumRemainingShips(aiBoard);

    return numHumanShips == 0 || numAiShips == 0;
  }

  /**
   * Gets number of remaining AI ships
   *
   * @return - number of AI ships
   */
  public int getNumAiShips() {
    return numAiShips;
  }

  /**
   * Gets number of remaining human ships
   *
   * @return - number of human ships
   */
  public int getNumHumanShips() {
    return numHumanShips;
  }

  /**
   * Gets te number of shots input by the user
   *
   * @param height the height of the game board
   * @param width the width of the game board
   * @param numShips the number of ships the user has
   * @return the number of shots taken by the user on the turn
   */
  public int getNumHumanShots(int height, int width, int numShips) {
    return min(numShips, height * width - allHumanShots.size());
  }

  /**
   * Gets the number of ships remaining on a players board
   *
   * @param player the player who we are getting the number of remaining ships for
   * @return the number of ships remaining on the players board
   */
  public int getNumRemainingShips(List<Ship> player) {
    int res = player.size();
    for (int i = 0; i < player.size(); i++) {
      if (player.get(i).isSunken()) {
        res--;
      }
    }
    return res;
  }

}