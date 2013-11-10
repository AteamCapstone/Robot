package edu.erau.ateam.prototype;

import edu.erau.ateam.prototype.MainFrame.AbstractPage;

@SuppressWarnings("serial")
public abstract class LinkedPage extends AbstractPage{
	private final AbstractPage previous;
	
	protected LinkedPage(){
		previous = HomePage.instance;
	}
	
	protected LinkedPage(LinkedPage previous){
		this.previous = previous;
	}
	
	/** boolean that tracks the status of Navigation 
	private boolean navigationEnabled = true;
	
	/** Constructor that creates the Frame;
	protected MenuOption(){
		//gui stuff
		//create back button
	}
	
	/** turns off the Navigation buttons if they haven't already been disabled, 
	 * useful for Hardware demos and Navigation 
	protected final void disableNavigation(){
		
	}
	
	/** turns on the Navigation buttons if they haven't already been enabled, 
	 * useful for Hardware demos and Navigation 
	protected final void enableNavigation(){
		
	}*/

	final AbstractPage getPreviousPage() {
		return previous;
	}
}
