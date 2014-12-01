import javax.swing.*;
import java.awt.Point;


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
  public WumpusGraphics wg;

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

  /**
   * Constructs and initializes a new KnowledgeBase with
   * a known map of size s
   * @param s the size of the known map
   */
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
    if (foundGold && !grabbedGold) {
      grabbedGold = true;
      return Agent.ACTION_GRAB;
    }
    if (grabbedGold) {
      System.out.println("Find way back home");
      // TODO find path back home
      // TODO climb out if we are home
    }
    if (foundWumpus) {
      System.out.println("Find way back home");
      // TODO shoot the wumpus
    }
    // if none of the above, just move to the next
    // safe square
    Point p = findNextSafeUnvisitedCell();

    System.out.println("Next safe cell: " + p);

    if (p == null) {
      System.out.println("There are no more safe unvisited cells");
    }

    if (p.x - agentX > 0)
      return Agent.ACTION_MOVE_EAST;
    else if (p.x - agentX < 0)
      return Agent.ACTION_MOVE_WEST;
    else if (p.y - agentY > 0)
      return Agent.ACTION_MOVE_SOUTH;
    else if (p.y - agentY < 0)
      return Agent.ACTION_MOVE_NORTH;

    return -1;
  }

  /**
   * Returns the next safe and unvisited cell, or null if there isn't one
   * @return the next safe and unvisited cell, or null if there isn't one
   */
  private Point findNextSafeUnvisitedCell() {
    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        // if the cell is safe and not visited
        if ((map[j][i] & SAFE) != 0 && (map[j][i] & VISITED) == 0)
          return new Point(j, i);

    return null;
  }

  /**
   * Tells the Knowledge Base a percept and the coordinate it was received. The KB
   * will then infer facts based on the percept.
   * @param p the percept
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public void tell(Percept p, int x, int y) {
    // save the agents location
    agentX = x;
    agentY = y;
    // TODO are the two arrays unnecessary?
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
    foundGold = p.glitter();
    wg.renderKnowledgeBaseMap(map);
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
    if (!outOfBounds(y + 1, x) && !contains(map[y + 1][x], SAFE) && !contains(map[y + 1][x], state))
      map[y + 1][x] += state;
    if (!outOfBounds(y - 1, x) && !contains(map[y - 1][x], SAFE) && !contains(map[y - 1][x], state))
      map[y - 1][x] += state;
    if (!outOfBounds(y, x + 1) && !contains(map[y][x + 1], SAFE) && !contains(map[y][x + 1], state))
      map[y][x + 1] += state;
    if (!outOfBounds(y, x - 1) && !contains(map[y][x - 1], SAFE) && !contains(map[y][x - 1], state))
      map[y][x - 1] += state;
  }

  /**
   * Returns true if the cell contains status
   * @param status the status to check
   * @param cell the cell to check
   * @return true if the cell contains status
   */
  private boolean contains(int cell, int status) {
    return (cell & status) != 0;
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

