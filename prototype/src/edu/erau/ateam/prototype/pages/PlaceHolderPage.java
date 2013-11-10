package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.Setting;

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
