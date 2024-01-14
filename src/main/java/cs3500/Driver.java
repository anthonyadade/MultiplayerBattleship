package cs3500;


import cs3500.pa03.model.AiPlayer;
import cs3500.pa04.controller.ProxyController;
import java.io.IOException;
import java.net.Socket;

/**
 * Driver class containing the main method
 */
public class Driver {

  /**
   * The entry point for our Program
   *
   * @param args the arguments passed to the command line
   */
  public static void main(String[] args) {
    try {
      if (args.length == 2) {
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        AiPlayer ourAi = new AiPlayer();
        ProxyController controller = new ProxyController(socket, ourAi);
        controller.run();
      } else {
        System.out.println("Expected two arguments: `[host] [port]`.");
      }
    } catch (IOException | IllegalStateException e) {
      System.out.println("Unable to connect to the server.");
    } catch (NumberFormatException e) {
      System.out.println("Second argument should be an integer. Format: `[host] [part]`.");
    }
  }
}
