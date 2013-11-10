package edu.erau.ateam.prototype.pages;

import edu.erau.ateam.prototype.LinkedPage;

/** A leaf page for the color example pages */
@SuppressWarnings("serial")
public final class ExampleLeafPage extends LinkedPage{
	private final String name;
	
	/** creates the page, sets the name and background as the color */
	public ExampleLeafPage(ExampleBranchingPage prev, ExampleColors color){
		super(prev);
		
		setBackground(color.background);
		name = color.toString();
	}

	@Override
	protected String getPageName() {
		return name;
	}
}
