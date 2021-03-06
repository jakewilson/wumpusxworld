import java.util.Scanner;

/**
 * WumpusWorld.java
 * 
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class WumpusWorld {
  
  public static void main(String[] args) {
    Environment e = Environment.getEnvironment();
    WumpusGraphics wg = new WumpusGraphics();
    e.generateMap(wg);

    System.out.println(e);
    Agent a = new Agent(e);
    a.getKnowledgeBase().wg = wg;

    Scanner input = new Scanner(System.in);
    String line = "";
    while (!a.hasEscaped() && !a.isDead() && !(line = input.nextLine()).equals("q")) {
      System.out.println(a.senseEnv());
      a.getNextAction();
      a.takeAction();
    }
    if (a.isDead()) {
      System.out.println("The Agent has died!");
    }

    if (a.hasEscaped()) {
      System.out.println("The Agent has escaped!");
    }
  }

}

