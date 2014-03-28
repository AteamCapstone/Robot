package edu.erau.ateam.robot;

import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.*;

/** Utility class used to store constants used throughout the GUI */
public final class Setting {
	private Setting(){}
	
	/** Vertical dimension of the screen */
	public static final int DEFHEIGHT = 600;

	/** Horizontal dimension of the screen */
	public static final int DEFWIDTH = 800;
	
	
	public static final int LARGE_COMPONENT_WIDTH = 750;
	
	/**  Vertical dimension of the Navigation panel*/
	public static final int NAV_HEIGHT = 40;
	
	/** Vertical dimension of the panel below the Navigation panel */
	public static final int PAGE_HEIGHT = DEFHEIGHT - NAV_HEIGHT-50;
	
	/**  Vertical dimension of a large button */
	public static final int BUTTON_HEIGHT = 80;
	
	/**  Horizontal dimension of a large button */
	public static final int BUTTON_WIDTH = DEFWIDTH-100;
	
	public static final Dimension BUTTON_SIZE = new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT);
	
	/**  Width of a scrollbar */
	public static final int SCROLLBAR_WIDTH = 50;
	
	/** Width of spacing between buttons and text */
	public static final int SPACING_SIZE = 20;
	
	/** Small font text size */
	public static final Font SMALL_FONT = new Font("CONSOLAS",PLAIN,24);
	
	/** Large font text size */
	public static final Font LARGE_FONT = new Font("CONSOLAS",BOLD,24);

}
