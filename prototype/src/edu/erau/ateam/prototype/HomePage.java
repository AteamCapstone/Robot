package edu.erau.ateam.prototype;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import edu.erau.ateam.prototype.control_panel.ControlPanel;
import edu.erau.ateam.prototype.prototype.*;
import static edu.erau.ateam.prototype.Setting.LARGE_FONT;

/** The home page with all the options to go to various demos */
@SuppressWarnings("serial")
final class HomePage extends AbstractPage{
	/** the singleton instance of the home page */
	static final HomePage instance = new HomePage();

	/** Constructor for the home page */
	private HomePage(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//various buttons on the home page
		addButton(ControlPanel.instance);
		addButton(DemoBarcodePage.class);
		addButton(DemoSonarPage.class);
		addButton(SelectFacultyPage.class);
		add(Box.createVerticalStrut(40));
	}
	
	/** Adds a button and spacing to the home page */
	private void addButton(final Class<? extends LinkedPage> clazz){		
		try {
			addButton(clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Adds a button and spacing to the home page */
	private void addButton(LinkedPage page){		
		//adds spacing before the button */
		add(Box.createVerticalStrut(40));
		add(new OptionButton(page.getPageName(), page));
	}

	@Override
	protected String getPageName() {
		return "HOME";
	}
}

@SuppressWarnings("serial")
final class OptionButton extends JButton{	
	/** The page this button links to */
	private final LinkedPage page;
	
	OptionButton(String text, LinkedPage page){
		super(text);
		
		this.page = page;
		
		setAlignmentX(0.5f);
		setFont(LARGE_FONT);
		setMaximumSize(new Dimension(Setting.BUTTON_WIDTH,Setting.BUTTON_HEIGHT));
		
		//adds a listener that navigates to the given page
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getInstance().navigateTo(OptionButton.this.page);
			}
		});
	}
}
