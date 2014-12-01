import javax.swing.*;
import java.awt.Point;


/**
 * KnowledgeBase.java
 * @author Jake Wilson
 * @version Nov 30, 2014
 */
public class KnowledgeBase {

  private int[][] map;
  private int size;
  private int agentX, agentY;
  private int wumpusX, wumpusY;
  private int potentialWumpusCount;
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
  private boolean wumpusDead;

  /**
   * Constructs and initializes a new KnowledgeBase with
   * a known map of size s
   * @param s the size of the known map
   */
  public KnowledgeBase(int s) {
    size = s;
    map = new int[s][s];
    foundWumpus = foundGold = grabbedGold = wumpusDead = false;
    agentX = agentY = 0;
    wumpusX = wumpusY = -1;
    potentialWumpusCount = 0;
  }

  /**
   * Returns the Agents next Action
   * @return the Agents next Action
   */
  public int ask() {
    Point p;
    if (foundGold && !grabbedGold) {
      grabbedGold = true;
      return Agent.ACTION_GRAB;
    }
    if (foundWumpus && !wumpusDead) {
      System.out.println("Need to kill the wumpus");
      // TODO shoot the wumpus
      return Agent.ACTION_SHOOT;
    }
    if (grabbedGold) {
      System.out.println("Find way back home");
      if (agentX == 0 && agentY == 0) {
        return Agent.ACTION_CLIMB;
      } else {
        p = new Point(0, 0);
      }
    } else {
      // if none of the above, just move to the next
      // safe square
      p = findNextSafeUnvisitedCell();
    }

    System.out.println("Next safe cell: " + p);

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
        if ((map[i][j] & SAFE) != 0 && (map[i][j] & VISITED) == 0)
          return new Point(j, i);

    return new Point(0, 0);
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
    map[y][x] = SAFE + VISITED;
    if (!p.breeze() && !p.stench()) {
      // if no stench or breeze in the current cell, all adj cells are safe
      markAdjCells(x, y, SAFE);
    }
    if (p.breeze()) {
      markAdjCells(x, y, POTENTIAL_PIT);
    }
    if (p.stench()) {
      markAdjCells(x, y, POTENTIAL_WUMPUS);
      // any potential wumpus' that are not adjacent
      // TODO as long as there is not a pit
      // to (x,y) are now marked unknown
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (contains(map[i][j], POTENTIAL_WUMPUS) && !isAdjacent(x, y, j, i)) {
            map[i][j] = UNKNOWN;
            potentialWumpusCount--;
          }
        }
      }
    }
    if (potentialWumpusCount == 1 && !foundWumpus) {
      // find the potential wumpus and change it to wumpus
      System.out.println("Found the wumpus!");
      foundWumpus = true;
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (contains(map[i][j], POTENTIAL_WUMPUS)) {
            map[i][j] -= POTENTIAL_WUMPUS;
            map[i][j] += WUMPUS;
            break;
          }
        }
      }
    }
    if (!wumpusDead) {
      wumpusDead = p.scream();
    }
    if (!foundGold) {
      foundGold = p.glitter();
    }
    wg.renderKnowledgeBaseMap(map);
    System.out.println(this);
  }

  /**
   * Returns true if (x2,y2) is adjacent to (x1,y1)
   * @param x1 the first x coordinate
   * @param y1 the first y coordinate
   * @param x2 the second x coordinate
   * @param y2 the second y coordinate
   * @return true if (x2,y2) is adjacent to (x1,y1)
   */
  private boolean isAdjacent(int x1, int y1, int x2, int y2) {
    return  (x2 == (x1 + 1) && y1 == y2) ||
            (x2 == (x1 - 1) && y1 == y2) ||
            (x2 == x1 && (y2 == y1 + 1)) ||
            (x2 == x1 && (y2 == y1 - 1));
  }

  /**
   * Marks all the adj cells to (x,y) as status if they're not already
   * safe
   * @param x the x coordinate
   * @param y the y coordinate
   * @param state the state of the cell
   */
  private void markAdjCells(int x, int y, int state) {
    if (!outOfBounds(y + 1, x) && !contains(map[y + 1][x], SAFE) && !contains(map[y + 1][x], state)) {
      if (state == SAFE) {
        map[y + 1][x] = state;
      } else {
        map[y + 1][x] += state;
      }
      if (state == POTENTIAL_WUMPUS) potentialWumpusCount++;
    }
    if (!outOfBounds(y - 1, x) && !contains(map[y - 1][x], SAFE) && !contains(map[y - 1][x], state)) {
      if (state == SAFE) {
        map[y - 1][x] = state;
      } else {
        map[y - 1][x] += state;
      }
      if (state == POTENTIAL_WUMPUS) potentialWumpusCount++;
    }
    if (!outOfBounds(y, x + 1) && !contains(map[y][x + 1], SAFE) && !contains(map[y][x + 1], state)) {
      if (state == SAFE) {
        map[y][x + 1] = state;
      } else {
        map[y][x + 1] += state;
      }
      if (state == POTENTIAL_WUMPUS) potentialWumpusCount++;
    }
    if (!outOfBounds(y, x - 1) && !contains(map[y][x - 1], SAFE) && !contains(map[y][x - 1], state)) {
      if (state == SAFE) {
        map[y][x - 1] = state;
      } else {
        map[y][x - 1] += state;
      }
      if (state == POTENTIAL_WUMPUS) potentialWumpusCount++;
    }
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
   * Returns the x coordinate of the wumpus
   * @return the x coordinate of the wumpus
   */
  public int getWumpusX() {
    return wumpusX;
  }

  /**
   * Returns the y coordinate of the wumpus
   * @return the y coordinate of the wumpus
   */
  public int getWumpusY() {
    return wumpusY;
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

