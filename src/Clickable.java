//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Treasure Hunt
// Course:   CS 300 Spring 2022
//
// Author:   Prasoon Tandon
// Email:    ptandon3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A clickable object is an object that responds to the mouse being pressed and released.
 * 
 * @author prasoontandon
 */
public interface Clickable {

  /**
   * Draws this Clickable object to the screen
   */
  public void draw();

  /**
   * Defines the behavior of this Clickable object each time the mouse is pressed
   */
  public void mousePressed();

  /**
   * Defines the behavior of this Clickable object each time the mouse is released
   */
  public void mouseReleased();

  /**
   * Returns true if the mouse is over this clickable object
   * 
   * @return true if mouse is over, false otherwise
   */
  public boolean isMouseOver();
}
