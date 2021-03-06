/**
 * Cell.java
 * 
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
    if (!contains(c))
      content += c;
  }

  /**
   * Removes content from the cell
   * @param c the content to remove
   */
  public void removeContent(int c) {
    if (contains(c))
      content -= c;
  }
  
  /**
   * Returns true if the cell contains p
   * @param p the percept to test
   * @return true if the cell contains p
   */
  public boolean contains(int p) {
    return (content & p) != 0;
  }
  
  /**
   * Returns the content of the cell
   * @return the content of the cell
   */
  public int getContent() {
    return content;
  }
  
  /**
   * Returns the content of the cell
   */
  public String toString() {
    return String.format("%2d", content);
  }

}

