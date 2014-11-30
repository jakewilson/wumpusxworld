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
    currentX = currentY = performanceMeasure = time = 0;
    orientation = ORIENTATION_EAST;
  }
  
  /**
   * Performs an action and returns a percept after the action has been taken
   * @param a the action to take
   * @return the percept of the cell the agent is at
   */
  public int takeAction(int a) {
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
        break;
      case ACTION_TURN_RIGHT:
        break;
      case ACTION_SHOOT:
        break;
      case ACTION_GRAB:
        break;
      case ACTION_CLIMB:
        break;
      default: // should be impossible
        return -1;
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

