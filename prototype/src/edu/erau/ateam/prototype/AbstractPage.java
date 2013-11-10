package edu.erau.ateam.prototype;

import javax.swing.JPanel;

/** A Jpanel that is able to be shown as the lower panel in the main frame
 * This panel has a name, which will be used by the navigation panel to display
 * where the user is */
@SuppressWarnings("serial")
abstract class AbstractPage extends JPanel{		
	
	/** gets the name of this page */
	protected abstract String getPageName();
}