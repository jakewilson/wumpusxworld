package wumpusworld;


/**
 * Agent.java
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class Agent {
  
  private Cell[][] knownMap;
  private Environment env;
  private boolean hasArrow;
  private int currentX, currentY;
  private int performanceMeasure;
//  private Set<Action> possibleActions;
//  private KnowledgeBase kb;
  private int time;
  
  public Agent(Environment e) {
    env = e;
    knownMap = new Cell[e.getSize()][e.getSize()];
    hasArrow = true;
    currentX = currentY = performanceMeasure = time = 0;
  }
  
}

