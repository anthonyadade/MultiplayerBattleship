package cs3500.pa03.model;

/**
 * Represents and endgame condition
 */
public enum GameResult {
  WIN("You win!"),
  LOSE("You lose!"),
  DRAW("You tied!");

  private final String result;

  private GameResult(String result) {
    this.result = result;
  }

  /**
   * Gets the endgame text
   *
   * @return - win, lose, or tie
   */
  public String getEndText() {
    return result;
  }

}
