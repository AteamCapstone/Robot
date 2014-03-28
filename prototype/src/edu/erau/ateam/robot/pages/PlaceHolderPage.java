package edu.erau.ateam.robot.pages;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import edu.erau.ateam.robot.LinkedPage;
import edu.erau.ateam.robot.Setting;

/** A page used to display "This is a placeholder" on pages that 
 * are not yet completed.  To be removed in the final version */
@SuppressWarnings("serial")
public abstract class PlaceHolderPage extends LinkedPage{
	protected PlaceHolderPage(){
		setLayout(new BorderLayout());
		JLabel placeholder = new JLabel("Coming soon...");
		placeholder.setAlignmentX(0.5f);
		placeholder.setFont(Setting.LARGE_FONT);
		placeholder.setHorizontalAlignment(JLabel.CENTER);
		add(placeholder, BorderLayout.CENTER);
	}
}
