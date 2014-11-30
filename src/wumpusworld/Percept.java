package wumpusworld;


/**
 * Percept.java
 * @author Jake Wilson
 * @version Nov 29, 2014
 */
public class Percept {
  
  /** A Bitmask representing five possible percepts:
   * [Stench, Breeze, Glitter, Bump, Scream].
   */
  private int percept;
  
  public static final int PERCEPT_SCREAM  = 1,
                          PERCEPT_BUMP    = 2,
                          PERCEPT_GLITTER = 4,
                          PERCEPT_BREEZE  = 8,
                          PERCEPT_STENCH  = 16;
  
  /**
   * Constructs and initializes a new Percept equal to p
   * @param p the value of the percept
   */
  public Percept(int p) {
    percept = p;
  }
  
  /**
   * Returns whether a scream is a part of this percept
   * @return whether a scream is a part of this percept
   */
  public boolean scream() {
    return (percept & PERCEPT_SCREAM) != 0;
  }
  
  /**
   * Returns whether a bump is a part of this percept
   * @return whether a bump is a part of this percept
   */
  public boolean bump() {
    return (percept & PERCEPT_BUMP) != 0;
  }
  
  /**
   * Returns whether a glitter is a part of this percept
   * @return whether a glitter is a part of this percept
   */
  public boolean glitter() {
    return (percept & PERCEPT_GLITTER) != 0;
  }
  
  /**
   * Returns whether a breeze is a part of this percept
   * @return whether a breeze is a part of this percept
   */
  public boolean breeze() {
    return (percept & PERCEPT_BREEZE) != 0;
  }
  
  /**
   * Returns whether a stench is a part of this percept
   * @return whether a stench is a part of this percept
   */
  public boolean stench() {
    return (percept & PERCEPT_STENCH) != 0;
  }

}

