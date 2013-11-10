package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;
import javax.swing.JLabel;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.Setting;
import edu.erau.ateam.prototype.data.Professor;

@SuppressWarnings("serial")
/** A page that shows faculty information.  Currently a placeholder to be fleshed out later*/
public class ProfessorInformationPage extends LinkedPage{
	private final Professor professor;
	
	/** The constructor for configuring this panel */
	ProfessorInformationPage(SelectProfessorPage listPage, Professor professor) {
		super(listPage);
		this.professor = professor;
		
		//placeholder code below, @Jia feel free to delete this
		setLayout(new BorderLayout());
		JLabel placeholder = new JLabel(professor.getName()+" information goes here");
		placeholder.setAlignmentX(0.5f);
		placeholder.setFont(Setting.LARGE_FONT);
		placeholder.setHorizontalAlignment(JLabel.CENTER);
		add(placeholder, BorderLayout.CENTER);
	}

	/** The name of this page is the professor name */
	@Override
	protected String getPageName() {
		return professor.getName();
	}
}
