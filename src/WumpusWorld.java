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

    System.out.println(a.senseEnv());
    a.getNextAction();
    a.takeAction();

    System.out.println(a.senseEnv());
    a.getNextAction();
    a.takeAction();

    System.out.println(a.senseEnv());
    a.getNextAction();
    a.takeAction();
  }

}

