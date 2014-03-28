package edu.erau.ateam.prototype;

import static java.awt.BorderLayout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

/** The JFrame that holds the entire GUI.  This frame contains a 
 * navigation panel on top and a panel (called a page) below it. 
 * The page can change, which will update the navigation panel.  
 * You can control the page transitions from this class*/
@SuppressWarnings("serial")
public final class MainFrame extends JFrame{
	/** This class is a singleton, so it only has one instance */
	private static final MainFrame instance = new MainFrame();
	
	/** The panel behind all other panels on the main frame */
	private final JPanel main = new JPanel();
	
	/** The navigation panel */
	private final NavigationPanel nav = new NavigationPanel();
	
	/** The current panel showing */
	private AbstractPage currentPage = HomePage.instance;
	
	/** creats the main frame */
	private MainFrame(){
		super("A-Team Robot Butler Prototype");
		//create GUI
		
		//add and configure the background panel
		main.setLayout(new BorderLayout());
		add(main);
		
		//add the navigation panel
		main.add(nav, PAGE_START);
		
		//add the home page
		main.add(currentPage, CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Setting.DEFWIDTH, Setting.DEFHEIGHT);
	}
	
	/** The main method for the whole demo.  Shows the splash, updates the Database, then shows the GUI*/
	public static void main(String[] args) throws InterruptedException{
		
		//SPLASH SCREEN CODE HERE. Comment it out if it give you trouble 
		SplashScreen splash = SplashScreen.getSplashScreen();
		
		//DataStore.getInstance().updateScheduleData();//parses calendar data
		
		//Thread.sleep(200);//remove this after calendar parsing is added.
		
		if(splash!=null)splash.close();//vm argument to add to run configurations: -splash:images/splash.png
		//END OF SPLASHSCREEN CODE
		
		instance.setVisible(true);
	}
	
	/** Should be used for navigating to pages other than Home.
	 * This will change the lower panel and update the navigation panel*/
	public void navigateTo(LinkedPage page){
		main.remove(currentPage);
		currentPage = page;
		main.add(page);
		
		//update the Navigation panel
		nav.setNavTo(page);
		
		//refresh the whole frame
		main.updateUI();
	}
	
	/** Tells the frame to quit out of all panels and go to the home screen
	 * This will change the lower panel and update the navigation panel */
	public void goHome(){
		//swap the currentpage with the home page
		main.remove(currentPage);
		currentPage = HomePage.instance;
		main.add(currentPage);
		
		//update the Navigation panel
		nav.resetNavToHome();
		
		//refresh the whole frame
		main.updateUI();
	}

	/** Gets the singleton instance of the MainFrame */
	public static MainFrame getInstance(){
		return instance;
	}
	
	/** The panel at the top used to hold navigation to other screens */
	final class NavigationPanel extends JPanel{
		
		/** A universal button used to go home */
		private JButton backHome = new JButton("HOME");
		
		/** Creates the panel */
		private NavigationPanel(){
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
			resetNavToHome();
			setPreferredSize(new Dimension(Setting.DEFWIDTH,Setting.NAV_HEIGHT));
			
			//configure the home button to reset the navigate to the home screen
			backHome.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					goHome();
				}
			});
			backHome.setFont(Setting.LARGE_FONT);
		}
		
		/** sets the navigation panel to the given page */
		private void setNavTo(LinkedPage page){
			//create a list to store the components of the nav panel
			LinkedList<Component> pages = new LinkedList<Component>();
			
			//add a text label for the current screen
			pages.add(makeLabel(page));
			//add spacing
			pages.addFirst(Box.createHorizontalStrut(Setting.SPACING_SIZE));
			
			//loop through all previous pages, adding a button for each, and quitting when home is found
			AbstractPage prev = page.getPreviousPage();
			while(prev != HomePage.instance && prev instanceof LinkedPage){
				pages.addFirst(new NavButton((LinkedPage) prev));
				pages.addFirst(Box.createHorizontalStrut(Setting.SPACING_SIZE));
				prev = ((LinkedPage) prev).getPreviousPage();
			}
			//add the homebutton last
			pages.addFirst(backHome);
			
			//clear the contents of the nav panel
			removeAll();
			
			//add the new contents 
			while(!pages.isEmpty())add(pages.pop());
		}
		
		/** Resets the navigation panel to home */
		private void resetNavToHome(){
			removeAll();
			JLabel label = new JLabel(" HOME");
			label.setFont(Setting.LARGE_FONT);
			add(label);
			updateUI();
		}
		
		/** Creates a label that is appended to the end */
		private final JLabel makeLabel(AbstractPage page){
			JLabel label = new JLabel(page.getPageName());
			label.setFont(Setting.LARGE_FONT);
			return label;
		}
		
		/** A button used to navigate between pages */
		class NavButton extends JButton{			
			NavButton(final LinkedPage page){
				super(page.getPageName());
				
				setFont(Setting.LARGE_FONT);
				
				addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						navigateTo(page);
					}
				});
			}
		}
	}
}