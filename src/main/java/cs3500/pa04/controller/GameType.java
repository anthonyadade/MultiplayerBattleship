package cs3500.pa04.controller;

/**
 * Represents the game type
 */
public enum GameType {
  SINGLE("SINGLE"),
  MULTI("MULTI");

  private String type;

  private GameType(String type) {

    this.type = type;
  }

  /**
   * To string for this game type
   *
   * @return - string representation of this game type
   */
  @Override
  public String toString() {
    return type;
  }
}
