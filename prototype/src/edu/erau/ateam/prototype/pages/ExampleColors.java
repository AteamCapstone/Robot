package edu.erau.ateam.prototype.pages;

import java.awt.Color;

public enum ExampleColors {
	BLUE(Color.BLUE),
	RED(Color.RED),
	GREEN(Color.GREEN),
	YELLOW(Color.YELLOW),
	ORANGE(Color.ORANGE),
	PINK(Color.PINK);
	
	public final Color background;
	
	private ExampleColors(Color color){
		this.background = color;
	}
}
