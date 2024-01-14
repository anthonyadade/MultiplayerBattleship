package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents the Console Display
 */
public class ConsoleDisplay implements Display {

  private static int[][] userBoard = null;
  private static int[][] aiBoard = null;

  /**
   * Prints the welcome screen and prompt
   */
  @Override
  public void displayWelcome() {
    System.out.println("Welcome to Battleship! Please choose the size of the board."
        + " Enter the height followed by the width.\nNote that these dimensions must be"
        + " between 6 and 15 inclusive.");
  }

  /**
   * Prints an invalid input prompt
   *
   * @param wrong - what was invalid
   */
  @Override
  public void displayUhOh(String wrong) {
    System.out.println("Uh Oh! You have entered " + wrong + ". Please try again.");
  }

  /**
   * Asks the user to enter the number of each ship
   *
   * @param max - max number of total ships
   */
  @Override
  public void askShipsNum(int max) {
    System.out.println("Please enter your desired fleet in the order"
        + " [Carrier, Battleship, Destroyer, Submarine]\nIt may not exceed size " + max);

  }

  /**
   * Displays the user's board
   *
   * @param ships - user's ships
   * @param height - height of board
   * @param width - width of board
   */
  @Override
  public void displayMyBoard(List<Ship> ships, int height, int width) {
    System.out.println("Your Board:");
    if (userBoard == null) {
      userBoard = new int[height][width];
    }
    for (Ship s : ships) {
      for (Coord c : s.occupiedCoords()) {
        userBoard[c.getY()][c.getX()] = 1; // 1 represent ship
      }
      for (Coord c : s.getHits()) {
        userBoard[c.getY()][c.getX()] = 2; // 2 represent hit
      }
    }
    for (int[] i : userBoard) {
      for (int j : i) {
        System.out.print(j);
      }
      System.out.println();
    }
  }

  /**
   * Displays the opponent's board
   *
   * @param ships - opponent's ships
   * @param height - height of board
   * @param width - width of board
   * @param shotsTaken - shots you have fired at opponent
   */
  @Override
  public void displayOppBoard(List<Ship> ships, int height, int width, HashSet<Coord> shotsTaken) {
    System.out.println("Opponent Board:");
    if (aiBoard == null) {
      aiBoard = new int[height][width];
    }
    List<Coord> shotsHit = new ArrayList<>();
    for (Ship s : ships) {
      for (Coord c : s.getHits()) {
        aiBoard[c.getY()][c.getX()] = 2; // 2 represent hit
        shotsHit.add(c);
      }
    }
    shotsTaken.removeAll(shotsHit);
    for (Coord shotMissed : shotsTaken) {
      aiBoard[shotMissed.getY()][shotMissed.getX()] = 3; // 3 represent miss
    }
    for (int[] i : aiBoard) {
      for (int j : i) {
        System.out.print(j);
      }
      System.out.println();
    }
  }

  /**
   * Asks the user to enter their desired shots
   *
   * @param numShots - number of shots user will take
   */
  @Override
  public void displaySelectShots(int numShots) {
    System.out.println("Please Enter " + numShots + " Shots:");
  }

  /**
   * Prints the winner of the game
   *
   * @param winner - who won
   */
  @Override
  public void displayGameOver(String winner) {
    System.out.println(winner);
  }
}
