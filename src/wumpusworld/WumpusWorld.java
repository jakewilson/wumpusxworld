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
    Percept p;
    while ((p = a.takeAction(Agent.ACTION_FORWARD)).bump() == false) {
      System.out.println("Percept: " + p + " " + a);
    }
    a.takeAction(Agent.ACTION_TURN_RIGHT);
    while ((p = a.takeAction(Agent.ACTION_FORWARD)).bump() == false) {
      System.out.println("Percept: " + p + " " + a);
    }
    
  }

}

