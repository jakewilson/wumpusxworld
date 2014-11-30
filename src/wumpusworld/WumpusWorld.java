package wumpusworld;


/**
 * WumpusWorld.java
 * 
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class WumpusWorld {
  
  public static void main(String[] args) {
    Environment e = Environment.getEnvironment();
    e.generateMap();
    System.out.println(e);
    Agent a = new Agent(e);
    int percept;
    while ((percept = a.takeAction(Agent.ACTION_FORWARD)) != -1) {
      System.out.println("Percept: " + percept + " " + a);
    }
  }

}

