package edu.erau.ateam.robot;

/** A JPanel that can appear in the lower portion of the frame. This has a name
 * and also notes the page it was previously on. If you wish to designate that this was navigated to
 * from the Homepage, use the NoArg constructor
 */
@SuppressWarnings("serial")
public abstract class LinkedPage extends AbstractPage{
	/** The page that was viewed before this page */
	private final AbstractPage previous;
	
	/** Contstructs a LinkedPage that lists the Homepage as the previous page */
	protected LinkedPage(){
		previous = HomePage.instance;
	}
	
	/** Constructs a LinkedPage and stores the previously viewed page. 
	 */
	protected LinkedPage(LinkedPage previous){
		this.previous = previous;
	}

	/** gets the page that was viewed before this page */
	final AbstractPage getPreviousPage() {
		return previous;
	}
}
