package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;
import javax.swing.JLabel;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.Setting;
import edu.erau.ateam.prototype.data.Professor;

@SuppressWarnings("serial")
/** A page that shows information */
public class ProfessorInformationPage extends LinkedPage{
	private final Professor professor;
	
	ProfessorInformationPage(SelectProfessorPage listPage, Professor professor) {
		super(listPage);
		this.professor = professor;
		
		//placeholder code below, @Yuxiang feel free 
		setLayout(new BorderLayout());
		JLabel placeholder = new JLabel(professor.getName()+" information goes here");
		placeholder.setAlignmentX(0.5f);
		placeholder.setFont(Setting.LARGE_FONT);
		placeholder.setHorizontalAlignment(JLabel.CENTER);
		add(placeholder, BorderLayout.CENTER);
	}

	@Override
	protected String getPageName() {
		return professor.getName();
	}
}
