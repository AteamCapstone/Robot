package edu.erau.ateam.prototype;

import java.awt.Font;
import static java.awt.Font.*;

public final class Setting {
	/** Vertical dimension of the screen */
	public static final int DEFHEIGHT = 1000;

	/** Horizontal dimension of the screen */
	public static final int DEFWIDTH = 768;
	
	/** Navigation panel height */
	public static final int NAV_HEIGHT = 40;
	
	public static final int PAGE_HEIGHT = DEFHEIGHT - NAV_HEIGHT-50;
	
	public static final int BUTTON_HEIGHT = 100;
	public static final int BUTTON_WIDTH = 600;
	public static final int SCROLLBAR_WIDTH = 50;
	public static final int SPACING_SIZE = 20;
	
	/** Small font text size */
	public static final Font SMALL_FONT = new Font("CONSOLAS",PLAIN,24);
	
	/** Large font text size */
	public static final Font LARGE_FONT = new Font("CONSOLAS",BOLD,36);

}
