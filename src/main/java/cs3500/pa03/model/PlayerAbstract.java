package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Reprents an abstract player
 *
 */
abstract class PlayerAbstract implements Player {

  protected static int height;
  protected static int width;
  protected static List<Ship> myShips = null;

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  public abstract String name();

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.height = height;
    this.width = width;
    Random random = new Random();
    List<Ship> res = new ArrayList<>();
    List<ShipType> order = ShipType.CARRIER.biggestToSmallest();
    for (ShipType t : order) {
      int shipSize = t.getSize();
      Ship ship;
      int numOfThisShip = 0;
      while (numOfThisShip < specifications.get(t)) {
        boolean check = true;
        if (random.nextInt(2) == 0) {
          int x = random.nextInt(width); // ship is vertical
          int y = random.nextInt(height - shipSize + 1);
          Coord coord = new Coord(x, y);
          ship = new Ship(coord, false, t);
        } else {
          int x = random.nextInt(width - shipSize + 1); // horizontal
          int y = random.nextInt(height);
          Coord coord = new Coord(x, y);
          ship = new Ship(coord, true, t);
        }
        for (Ship s : res) {
          if (s.collidesWithMe(ship)) {
            check = false;
            break;
          }
        }
        if (check) {
          res.add(ship);
          numOfThisShip++;
        }
      }
    }
    myShips = res;
    /*
    if (myShips == null) {
      myShips = res;
    }
     */
    return res;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> res = new ArrayList<>();
    for (Ship s : this.myShips) {
      for (Coord oppShot : opponentShotsOnBoard) {
        if (s.occupiedCoords().contains(oppShot)) {
          if (s.addHit(oppShot)) {
            res.add(oppShot);
          }
        }
      }
      opponentShotsOnBoard.removeAll(res); // so we don't keep looping through shots already hits
    }
    return res;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  public abstract void successfulHits(List<Coord> shotsThatHitOpponentShips);

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  public abstract void endGame(GameResult result, String reason);

}
