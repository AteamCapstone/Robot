package edu.erau.ateam.prototype.pages;

import java.awt.Color;

/** Colors used in the example colors pages used to test the Nav panel
 * Remove from the final version of the prototype */
public enum ExampleColors {
	BLUE(Color.BLUE),
	RED(Color.RED),
	GREEN(Color.GREEN),
	YELLOW(Color.YELLOW),
	ORANGE(Color.ORANGE),
	PINK(Color.PINK);
	
	/** A reference of the AWT color of the background */
	public final Color background;
	
	private ExampleColors(Color color){
		this.background = color;
	}
}
