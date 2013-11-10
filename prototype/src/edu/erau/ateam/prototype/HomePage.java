package edu.erau.ateam.prototype;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import edu.erau.ateam.prototype.MainFrame.AbstractPage;
import edu.erau.ateam.prototype.pages.*;
import static edu.erau.ateam.prototype.Setting.LARGE_FONT;

/** The home page with all the options to go to various demos */
@SuppressWarnings("serial")
final class HomePage extends AbstractPage{
	static final HomePage instance = new HomePage();

	private HomePage(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		addButton("JNI Demo", DemoJniPage.class);
		addButton("Barcode Demo", DemoBarcodePage.class);
		addButton("Motor Demo", DemoMotorPage.class);
		addButton("Sonar Demo", DemoSonarPage.class);
		addButton("Find a Professor", SelectProfessorPage.class);
		addButton("Color Examples", ExampleBranchingPage.class);
	}
	
	private void addButton(final String text, final Class<? extends LinkedPage> clazz){
		class OptionButton extends JButton{
			OptionButton(){
				super(text);
				setAlignmentX(0.5f);
				setFont(LARGE_FONT);
				setMaximumSize(new Dimension(600,100));
				
				addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							MainFrame.instance.navigateTo(clazz.newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}

		add(Box.createVerticalStrut(40));
		add(new OptionButton());
	}

	@Override
	protected String getPageName() {
		return "HOME";
	}
}
