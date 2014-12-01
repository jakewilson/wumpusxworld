
/**
 * Environment.java
 * 
 * @author Jake Wilson
 * @version Nov 28, 2014
 */
public class Environment {
  
  private int size;
  
  private Cell[][] map;
  
  private boolean mapGenerated;
  
  private static boolean envCreated = false;
  
  public static final int SIZE = 4;
  
  private static Environment e;

  private WumpusGraphics graphics;
  
  /**
   * Constructs and initializes a new Environment with a map size
   * equal to s
   * @param s the length and width of the map
   */
  private Environment(int s) {
    map  = new Cell[s][s];
    size = s;
    mapGenerated = false;
    initMap(map);
    envCreated = true;
  }
  
  /**
   * Ensures only one environment can be created
   * @return the environment
   */
  public static Environment getEnvironment() {
    if (!envCreated)
      return e = new Environment(SIZE);
    
    return e;
  }
  
  /**
   * Returns the percept of the cell at (x,y)
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @return the percept of the cell at (x,y) or -1 if (x,y) is out of bounds
   */
  public Percept getPercept(int y, int x) {
    if (outOfBounds(x, y))
      return new Percept(Percept.PERCEPT_BUMP);
    
    int p = 0;
    Cell c = map[y][x];
    if (c.contains(Cell.BREEZE)) p += Percept.PERCEPT_BREEZE;
    if (c.contains(Cell.STENCH)) p += Percept.PERCEPT_STENCH;
    if (c.contains(Cell.GLITTER)) p += Percept.PERCEPT_GLITTER;
    return new Percept(p);
  }
  
  /**
   * Initializes every cell in the map to a new cell
   */
  public static void initMap(Cell[][] map) {
    for (int i = 0; i < map.length; i++)
      for (int j = 0; j < map[i].length; j++)
        map[i][j] = new Cell();
  }
  
//  public Percept 
  
  /**
   * Generates the map of the environment. There is exactly one wumpus
   * and one piece of gold in the map. Also, each cell that does not contain
   * a wumpus or gold, and is not the starting cell can contain a pit with
   * P(.2)
   */
  public void generateMap(WumpusGraphics wg) {
    if (!mapGenerated) { // ensure we only generate the map once
      // generate exactly one wumpus
      int wX, wY;

      graphics = wg;

      do {
        wX = (int)(Math.random() * 4);
        wY = (int)(Math.random() * 4);
      } while (!validWumpusCoord(wX, wY));
      
      map[wX][wY].addContent(Cell.WUMPUS);
      addAdjacentPercepts(wX, wY, Cell.STENCH);
      
      // generate exactly one gold piece
      map[(int)(Math.random() * 4)][(int)(Math.random() * 4)].addContent(Cell.GLITTER);
      
      // generate a pit on every cell that doesn't contain gold or a wumpus
      // with p = .2
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          if (!map[i][j].contains(Cell.WUMPUS) && !map[i][j].contains(Cell.GLITTER)) {
            if (Math.random() <= 0.2 && validPitCoord(i, j)) {
              System.out.println(i + " " + j);
              map[i][j].addContent(Cell.PIT);
              addAdjacentPercepts(i, j, Cell.BREEZE);
            }
          }
        }
      }
      wg.renderMap(map);
      mapGenerated = true;
    }
  }
  
  /**
   * Returns whether the coordinate (x,y) is allowed to have a pit
   * @param x the x coordinate
   * @param y the y coordinate
   * @return whether the coordinate (x,y) is allowed to have a pit
   */
  private boolean validPitCoord(int x, int y) {
    return validWumpusCoord(x, y);
  }
  
  /**
   * Returns whether the coordinate (x,y) is allowed to have a wumpus
   * @param x the x coordinate
   * @param y the y coordinate
   * @return whether the coordinate (x,y) is allowed to have a wumpus
   */
  private boolean validWumpusCoord(int x, int y) {
    return !(x == 0 && y == 0) && !(x == 0 && y == 1) && !(x == 1 && y == 0);
  }
  
  /**
   * Adds the percept p to all adjacent cells of (x,y) (not diagonally adjacent).
   * @param x the x coordinate
   * @param y the y coordinate
   * @param p the percept to add
   */
  private void addAdjacentPercepts(int x, int y, int p) {
    if (!outOfBounds(x + 1, y)) map[x + 1][y].addContent(p);
    if (!outOfBounds(x - 1, y)) map[x - 1][y].addContent(p);
    if (!outOfBounds(x, y + 1)) map[x][y + 1].addContent(p);
    if (!outOfBounds(x, y - 1)) map[x][y - 1].addContent(p);
  }
  
  /**
   * Returns whether the coordinate (x,y) is out of bounds
   * @param x the x coordinate
   * @param y the y coordinate
   * @return true if the coordinate is out of bounds, false if not
   */
  private boolean outOfBounds(int x, int y) {
    return (x < 0 || x >= size) || (y < 0 || y >= size);
  }
  
  /**
   * Returns the size of the map
   * @return
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Returns the appropriate number of dashes for the environment toString()
   * @return
   */
  public static String getDashes(int size) {
    String str = "";
    for (int i = 0; i <= (size * 3); i++)
      str += "-";
    
    return str + "\n";
  }
  
  /**
   * Returns the map of the environment in string form
   */
  public String toString() {
    String str = getDashes(size);
    for (int i = 0; i < size; i++) {
      str += "|";
      for (int j = 0; j < size; j++) {
        str += map[i][j].toString() + "|";
      }
      str += "\n" + getDashes(size);
    }
    
    return str;
  }

}

