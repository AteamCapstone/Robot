package edu.erau.ateam.prototype;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import edu.erau.ateam.prototype.pages.*;
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
		addButton("JNI Demo", DemoJniPage.class);
		addButton("Barcode Demo", DemoBarcodePage.class);
		addButton("Motor Demo", DemoMotorPage.class);
		addButton("Sonar Demo", DemoSonarPage.class);
		addButton("Find a Professor", SelectProfessorPage.class);
		addButton("Color Examples", ExampleBranchingPage.class);
	}
	
	/** Adds a button and spacing to the home page */
	private void addButton(final String text, final Class<? extends LinkedPage> clazz){
		class OptionButton extends JButton{
			OptionButton(){
				super(text);
				setAlignmentX(0.5f);
				setFont(LARGE_FONT);
				setMaximumSize(new Dimension(600,100));
				
				//adds a listener that navigates to the given page
				addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							MainFrame.getInstance().navigateTo(clazz.newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		//adds spacing before the button */
		add(Box.createVerticalStrut(40));
		add(new OptionButton());
	}

	@Override
	protected String getPageName() {
		return "HOME";
	}
}
