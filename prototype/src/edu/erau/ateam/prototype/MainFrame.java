package edu.erau.ateam.prototype;

import static java.awt.BorderLayout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame{
	public static final MainFrame instance = new MainFrame();
	
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
		
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Setting.DEFWIDTH, Setting.DEFHEIGHT);
	}
	
	/** The main method for the whole demo 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException{
		
		//SPLASH SCREEN CODE HERE. Comment it out if it give you trouble 
		SplashScreen splash = SplashScreen.getSplashScreen();
		//TODO INITIALIZE HARDCODED DATA
		//TODO CALENDAR PARSING GOES HERE 
		Thread.sleep(200);
		splash.close();
		//END OF SPLASHSCREEN CODE
		
		instance.setVisible(true);
	}
	
	/** should be used for navigating to pages other than Home */
	public void navigateTo(LinkedPage page){
		main.remove(currentPage);
		currentPage = page;
		main.add(page);
		nav.setNavTo(page);
		main.updateUI();
	}
	
	/** Tells the frame to quit out of all panels and go to the home screen */
	public void goHome(){
		main.remove(currentPage);
		currentPage = HomePage.instance;
		main.add(currentPage);
		nav.resetNavToHome();
		main.updateUI();
	}
	
	/** A JPanel used by other pages */
	static abstract class AbstractPage extends JPanel{		
		protected abstract String getPageName();
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
			LinkedList<Component> pages = new LinkedList<Component>();
			pages.add(makeLabel(page));
			pages.addFirst(Box.createHorizontalStrut(Setting.SPACING_SIZE));
			
			AbstractPage prev = page.getPreviousPage();
			while(prev != HomePage.instance && prev instanceof LinkedPage){
				pages.addFirst(new NavButton((LinkedPage) prev));
				pages.addFirst(Box.createHorizontalStrut(Setting.SPACING_SIZE));
				prev = ((LinkedPage) prev).getPreviousPage();
			}
			pages.addFirst(backHome);
			
			removeAll();
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