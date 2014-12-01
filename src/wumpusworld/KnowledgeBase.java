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
  
  /** Possible states of a cell */
  public static final int UNKNOWN           = 0,
                          SAFE              = 1,
                          POTENTIAL_WUMPUS  = 2,
                          POTENTIAL_PIT     = 3,
                          WUMPUS            = 4,
                          PIT               = 5;
  
  private boolean foundWumpus;
  private boolean foundGold;
  
  public KnowledgeBase(int s) {
    size = s;
    breeze = new boolean[s][s];
    stench = new boolean[s][s];
    map = new int[s][s];
    foundWumpus = foundGold = false;
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
    if (!p.breeze() && !p.stench()) {
      // if no stench or breeze in the current cell, all adj cells are safe
      map[y][x] = SAFE;
      markAdjCellsSafe(x, y);
    }
    System.out.println(this);
  }
  
  /**
   * Marks all the adj cells to (x,y) as safe
   * @param x the x coordinate
   * @param y the y coordinate
   */
  private void markAdjCellsSafe(int x, int y) {
    if (!outOfBounds(x + 1, y)) map[x + 1][y] = SAFE;
    if (!outOfBounds(x - 1, y)) map[x - 1][y] = SAFE;
    if (!outOfBounds(x, y + 1)) map[x][y + 1] = SAFE;
    if (!outOfBounds(x, y - 1)) map[x][y - 1] = SAFE;
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
        str += map[i][j] + "|";
      }
      str += "\n" + Environment.getDashes(size);
    }
  
    return str;
  }

}

