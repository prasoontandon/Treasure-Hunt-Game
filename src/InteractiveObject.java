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

import processing.core.PImage;
import java.util.NoSuchElementException;
import java.io.File;

/**
 * This class models a clickable interactive object used in Treasure Hunt application.
 * 
 * @author prasoontandon
 */
public class InteractiveObject implements Clickable {

  protected static TreasureHunt processing; // reference to the PApplet where this object will be
                                            // drawn
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is clicked the
                                      // first time, if any

  /**
   * Creates a new interactive object with no next clue and sets its image, name, x and y positions,
   * its message, and activation status. When created, an interactive object must be active, and not
   * clicked yet.
   * 
   * @param name    name to be assigned to this interactive object.
   * @param x       x-position to be assigned to this interactive object
   * @param y       y-position to be assigned to this interactive object
   * @param message message to be displayed on the console each time this interactive object is
   *                clicked.
   */
  public InteractiveObject(String name, int x, int y, String message) {
    this.image = InteractiveObject.processing.loadImage("images" + File.separator + name + ".png");
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    this.isActive = true;
  }

  /**
   * Creates a new interactive object with a next clue to be activated when this interactive object
   * is clicked for the first time. This constructor sets the image of the newly created interactive
   * object, its name, x and y positions, its message, and activation status. When created, an
   * interactive object is active, and not clicked. Also, this constructor deactivates the next clue
   * of this interactive object.
   * 
   * @param name     name to be assigned to this interactive object.
   * @param x        x-position to be assigned to this interactive object
   * @param y        y-position to be assigned to this interactive object
   * @param message  message to be displayed on the console each time this interactive object is
   *                 clicked.
   * @param nextClue a reference to a non-null InteractiveObject which represents the next clue
   *                 associated to this interactive object.
   */
  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue) {
    this(name, x, y, message);

    this.setNextClue(nextClue);
    this.nextClue.deactivate();
  }

  /**
   * Gets the x-position of this interactive object
   * 
   * @return the x-position of this interactive object
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-position of this interactive object
   * 
   * @return the y-position of this interactive object
   */
  public int getY() {
    return this.y;
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively
   * 
   * @param dx move to be added to the x position of this interactive object
   * @param dy move to be added to the y position of this interactive object
   */
  public void move​(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Checks whether this interactive object is active. This should be a getter of the isActive data
   * field
   * 
   * @return true if this interactive object is active and false otherwise
   */
  public boolean isActive() {
    return this.isActive;
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects will
   * be drawn
   * 
   * @param processing represents the reference to the TreasureHunt PApplet object where all the
   *                   interactive objects will be drawn
   */
  public static void setProcessing(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }

  /**
   * Sets the next clue associated to this interactive object
   * 
   * @param nextClue the next clue to be assigned to this interactive object
   */
  public void setNextClue(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt application
   * 
   * @throws NoSuchElementException with a descriptive error message if the nextClue of this
   *                                interactive object is null
   */
  protected void activateNextClue() {

    if (this.nextClue == null) {
      throw new NoSuchElementException();
    }
    this.nextClue.isActive = true;
    processing.add(this.nextClue); // Add nextClue to the application
  }

  /**
   * Checks whether the name of this interactive object equals to the name passed as input
   * parameter. We consider a case-sensitive comparison.
   * 
   * @param name name to compare to
   * @return true if the name of this interactive object equals the provided name, false otherwise
   */
  public boolean hasName(String name) {
    return name.equals(this.NAME);
  }

  /**
   * Activates this interactive object
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * Deactivates this interactive object
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * Gets the message of this interactive object
   * 
   * @return the message to be displayed each time this interactive object is clicked
   */
  public String message() {
    return this.message;
  }

  /**
   * Draws this interactive object (meaning drawing its image) to the display window at positions x
   * and y.
   */
  @Override
  public void draw() {
    processing.image(this.image, this.x, this.y);
  }

  /**
   * This method operates each time the mouse is pressed. This interactive object responds to the
   * mouse clicks as follows. If the mouse is clicked (meaning the mouse is over it) two operations
   * will be performed as follows: (1) The message of this interactive object must be displayed to
   * the console. (2) If this interactive object has a next clue and was not clicked, its next clue
   * will be activated and its wasClicked setting will be updated.
   */
  @Override
  public void mousePressed() {
    // Check if mouse is over the object and print the message if it is
    if (this.isMouseOver()) {
      System.out.println(this.message);

      // Update object settings per specifications
      if (this.nextClue != null && !this.wasClicked) {
        this.activateNextClue();
        this.wasClicked = true;
      }
    }
  }

  /**
   * This method operates each time the mouse is released. It implements a default behavior for an
   * interactive object which is DO NOTHING (meaning that the InteractiveObject.mouseReleased has a
   * blank implementation).
   */
  @Override
  public void mouseReleased() {
    // DO NOTHING
  }

  /**
   * Checks whether the mouse is over the image of this interactive object
   * 
   * @return true if the mouse is over the image of this interactive object, and false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // Horizontal Dimensions of Image: (this.x, this.x + image.width)
    // Vertical Dimensions of Image: (this.y, this.y + image.height)
    boolean inWidth = (processing.mouseX > this.x && processing.mouseX < this.x + image.width);
    boolean inHeight = (processing.mouseY > this.y && processing.mouseY < this.y + image.height);

    return inWidth && inHeight;
  }
}
