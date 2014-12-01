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
  private int performanceMeasure;
  private int time;
  
  private Percept currentPercept;
  
  private int nextAction;
  
  private KnowledgeBase kb;
  
  /** Possible Actions for the Agent to take */
  public static final int  ACTION_MOVE_NORTH = 1,
                           ACTION_MOVE_EAST  = 2,
                           ACTION_MOVE_SOUTH = 3,
                           ACTION_MOVE_WEST  = 4,
                           ACTION_SHOOT      = 5,
                           ACTION_GRAB       = 6,
                           ACTION_CLIMB      = 7;
  
  /**
   * Constructs and initializes a new Agent with Environment equal to e
   * and with the Agent facing East
   * @param e the environment of the Agent
   */
  public Agent(Environment e) {
    env = e;
    kb = new KnowledgeBase(e.getSize());
    hasArrow = true;
    hasGold = false;
    currentX = currentY = performanceMeasure = time = 0;
    currentPercept = null;
  }
  
  /**
   * Senses the environment and returns the percept it sensed
   * @return the percept it sensed
   */
  public Percept senseEnv() {
    return currentPercept = env.getPercept(currentY, currentX);
  }
  
  /**
   * Tells the Knowledge Base the current Percept and asks it for
   * the next action to take
   */
  public void getNextAction() {
    kb.tell(currentPercept, currentX, currentY);
    nextAction = kb.ask();
    System.out.println(nextAction);
  }

  /**
   * Gets the KnowledgeBase that the agent currently has
   * @return KnowledgeBase kb
   */
  public KnowledgeBase getKnowledgeBase(){
    return kb;
  }

  /**
   * Performs an action
   */
  public void takeAction() {
    System.out.println("(" + currentX + "," + currentY + ")");
    switch (nextAction) {
      case ACTION_MOVE_NORTH:
        currentY--;
        break;
      case ACTION_MOVE_EAST:
        currentX++;
        break;
      case ACTION_MOVE_SOUTH:
        currentY++;
        break;
      case ACTION_MOVE_WEST:
        currentX--;
        break;
      case ACTION_SHOOT:
        break;
      case ACTION_GRAB:
        hasGold = true;
        break;
      case ACTION_CLIMB:
        break;
      default: // should be impossible
        break;
    }
  }
  
  /**
   * Returns the x coordinate of the Agent
   * @return the x coordinate of the Agent
   */
  public int getX() {
    return currentX;
  }
  
  /**
   * Returns the y coordinate of the Agent
   * @return the y coordinate of the Agent
   */
  public int getY() {
    return currentY;
  }
  
  /**
   * Returns the coordinate of the Agent
   */
  public String toString() {
    return "(" + currentX + "," + currentY + ")";
  }
  
}

