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
  }

}

