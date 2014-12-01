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
    prevX = prevY = 0;
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
  }

  /**
   * Gets the KnowledgeBase that the agent currently has
   * @return KnowledgeBase kb
   */
  public KnowledgeBase getKnowledgeBase(){
    return kb;
  }

  /**
   * Performs an action and returns a percept after the action has been taken
   * @param a the action to take
   * @return the percept of the cell the agent is at
   */
  /*public Percept takeAction(int a) {
    prevX = currentX;
    prevY = currentY;
    switch (a) {
      case ACTION_FORWARD:
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
    // subtract one for every action taken
    performanceMeasure--;
    Percept p = env.getPercept(currentY, currentX);
    if (p.bump()) {
      currentX = prevX;
      currentY = prevY;
    }
    return p;
  }*/
  
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

