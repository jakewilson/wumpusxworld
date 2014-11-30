package wumpusworld;


/**
 * Agent.java
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class Agent {
  
  private Cell[][] knownMap;
  private Environment env;
  private boolean hasArrow, hasGold;
  private int currentX, currentY;
  private int prevX, prevY;
  private int performanceMeasure;
  private int time;
  private int orientation;
  
//  private KnowledgeBase kb;
  
  /** Possible Actions for the Agent to take */
  public static final int  ACTION_FORWARD    = 1,
                           ACTION_TURN_LEFT  = 2,
                           ACTION_TURN_RIGHT = 3,
                           ACTION_SHOOT      = 4,
                           ACTION_GRAB       = 5,
                           ACTION_CLIMB      = 6;
  
  /** Possible orientations of the Agent */
  public static final int ORIENTATION_NORTH = 1,
                          ORIENTATION_EAST  = 2,
                          ORIENTATION_SOUTH = 3,
                          ORIENTATION_WEST  = 4;
  
  /**
   * Constructs and initializes a new Agent with Environment equal to e
   * and with the Agent facing East
   * @param e the environment of the Agent
   */
  public Agent(Environment e) {
    env = e;
    knownMap = new Cell[e.getSize()][e.getSize()];
    Environment.initMap(knownMap);
    hasArrow = true;
    hasGold = false;
    currentX = currentY = performanceMeasure = time = 0;
    prevX = prevY = 0;
    orientation = ORIENTATION_EAST;
  }
  
  /**
   * Performs an action and returns a percept after the action has been taken
   * @param a the action to take
   * @return the percept of the cell the agent is at
   */
  public Percept takeAction(int a) {
    prevX = currentX;
    prevY = currentY;
    switch (a) {
      case ACTION_FORWARD:
        switch (orientation) {
          case ORIENTATION_NORTH:
            currentY--;
            break;
          case ORIENTATION_EAST:
            currentX++;
            break;
          case ORIENTATION_SOUTH:
            currentY++;
            break;
          case ORIENTATION_WEST:
            currentX--;
            break;
        }
        break;
      case ACTION_TURN_LEFT:
        orientation--;
        if (orientation == 0) orientation = ORIENTATION_WEST;
        break;
      case ACTION_TURN_RIGHT:
        orientation++;
        if (orientation == 5) orientation = ORIENTATION_NORTH;
        break;
      case ACTION_SHOOT:
        break;
      case ACTION_GRAB:
        break;
      case ACTION_CLIMB:
        break;
      default: // should be impossible
        return null;
    }
    return env.getPercept(currentY, currentX);
  }
  
  /**
   * Returns the coordinate of the Agent
   */
  public String toString() {
    return "(" + currentX + "," + currentY + ")";
  }
  
}

