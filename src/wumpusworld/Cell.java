package wumpusworld;

/**
 * Cell.java
 * @author Jake Wilson
 * @version Nov 28, 2014
 */
public class Cell {
	
  /** Possible states of a cell */
  public static final int EMPTY   = 0,
                          BREEZE  = 1,
                          PIT     = 2,
                          STENCH  = 4,
                          WUMPUS  = 8,
                          GLITTER = 16;
  
  private int content; // what is contained in the cell
  
  /**
   * Constructs and initializes a new Cell with EMPTY content
   */
  public Cell() {
    content = EMPTY;
  }
  
  /**
   * Adds content to the cell
   * @param c the content to add
   */
  public void addContent(int c) {
    content += c;
  }
  
  /**
   * Returns the content of the cell
   * @return the content of the cell
   */
  public int getContent() {
    return content;
  }

}

