package wumpusworld;


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
  
  /**
   * Constructs and initializes a new Environment with a map size
   * equal to s
   * @param s the length and width of the map
   */
  public Environment(int s) {
    map  = new Cell[s][s];
    size = s;
    mapGenerated = false;
    initMap();
  }
  
  /**
   * Initializes every cell in the map to a new cell
   */
  private void initMap() {
    for (int i = 0; i < map.length; i++)
      for (int j = 0; j < map[i].length; j++)
        map[i][j] = new Cell();
  }
  
  /**
   * Generates the map of the environment. There is exactly one wumpus
   * and one piece of gold in the map. Also, each cell that does not contain
   * a wumpus or gold, and is not the starting cell can contain a pit with
   * P(.2)
   */
  private void generateMap() {
    if (!mapGenerated) { // ensure we only generate the map once
      // generate exactly one wumpus
      int wX = (int)(Math.random() * 4);
      int wY = (int)(Math.random() * 4);
      map[wX][wY].addContent(Cell.WUMPUS);
      addAdjacentPercepts(wX, wY, Cell.STENCH);
      
      // generate exactly one gold piece
      map[(int)(Math.random() * 4)][(int)(Math.random() * 4)].addContent(Cell.GLITTER);
      
      // generate a pit on every cell that doesn't contain gold or a wumpus
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          if (!map[i][j].contains(Cell.WUMPUS) && !map[i][j].contains(Cell.GLITTER)) {
            if (Math.random() < 0.2) {
              map[i][j].addContent(Cell.PIT);
              addAdjacentPercepts(i, j, Cell.BREEZE);
            }
          }
        }
      }
      
      mapGenerated = true;
    }
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

}

