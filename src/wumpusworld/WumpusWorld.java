package wumpusworld;


/**
 * WumpusWorld.java
 * 
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class WumpusWorld {
  
  public static final int SIZE = 4;
  
  public static void main(String[] args) {
    Environment e = new Environment(SIZE);
    e.generateMap();
    System.out.println(e);
  }

}

