package wumpusworld;


/**
 * KnowledgeBase.java
 * @author Jake Wilson
 * @version Nov 30, 2014
 */
public class KnowledgeBase {
  
  private boolean[][] breeze;
  private boolean[][] stench;
  private int[][] map;
  private int size;
  private int agentX, agentY;
  
  /** Possible states of a cell */
  public static final int UNKNOWN           = 0,
                          SAFE              = 1,
                          POTENTIAL_WUMPUS  = 2,
                          POTENTIAL_PIT     = 4,
                          WUMPUS            = 8,
                          PIT               = 16,
                          VISITED           = 32;
  
  private boolean foundWumpus;
  private boolean foundGold;
  private boolean grabbedGold;
  
  public KnowledgeBase(int s) {
    size = s;
    breeze = new boolean[s][s];
    stench = new boolean[s][s];
    map = new int[s][s];
    foundWumpus = foundGold = grabbedGold = false;
    agentX = agentY = 0;
  }
  
  /**
   * Returns the Agents next Action
   * @return the Agents next Action
   */
  public int ask() {
    if (foundGold) {
      return Agent.ACTION_GRAB;
    }
    return 0;
  }
  
  /**
   * Tells the Knowledge Base a percept and the coordinate it was received. The KB
   * will then infer facts based on the percept.
   * @param p the percept
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public void tell(Percept p, int x, int y) {
    breeze[y][x] = p.breeze();
    stench[y][x] = p.stench();
    map[y][x] = SAFE + VISITED;
    if (!p.breeze() && !p.stench()) {
      // if no stench or breeze in the current cell, all adj cells are safe
      markAdjCells(x, y, SAFE);
    } else if (p.breeze() && !p.stench()) {
      markAdjCells(x, y, POTENTIAL_PIT);
    } else if (!p.breeze() && p.stench()) {
      markAdjCells(x, y, POTENTIAL_WUMPUS);
    } else if (p.breeze() && p.stench()) {
      markAdjCells(x, y, POTENTIAL_WUMPUS + POTENTIAL_PIT);
    }
    System.out.println(this);
  }
  
  /**
   * Marks all the adj cells to (x,y) as status if they're not already
   * safe
   * @param x the x coordinate
   * @param y the y coordinate
   * @param state the state of the cell
   */
  private void markAdjCells(int x, int y, int state) {
    if (!outOfBounds(x + 1, y) && map[x + 1][y] != SAFE) map[x + 1][y] += state;
    if (!outOfBounds(x - 1, y) && map[x - 1][y] != SAFE) map[x - 1][y] += state;
    if (!outOfBounds(x, y + 1) && map[x][y + 1] != SAFE) map[x][y + 1] += state;
    if (!outOfBounds(x, y - 1) && map[x][y - 1] != SAFE) map[x][y - 1] += state;
  }
  
  /**
   * Returns true if the coordinate (x,y) is out of Bounds
   * @param x the x coordinate
   * @param y the y coordinate
   * @return true if the coordinate (x,y) is out of Bounds
   */
  private boolean outOfBounds(int x, int y) {
    return (x < 0 || x >= size) || (y < 0 || y >= size);
  }
  
  public String toString() {
    String str = Environment.getDashes(size);
    for (int i = 0; i < size; i++) {
      str += "|";
      for (int j = 0; j < size; j++) {
        str += String.format("%2d", map[i][j]) + "|";
      }
      str += "\n" + Environment.getDashes(size);
    }
  
    return str;
  }

}
