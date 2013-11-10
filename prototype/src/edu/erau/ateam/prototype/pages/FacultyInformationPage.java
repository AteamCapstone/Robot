package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;
import javax.swing.JLabel;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.Setting;
import edu.erau.ateam.prototype.data.FacultyMember;

@SuppressWarnings("serial")
/** A page that shows faculty information.  Currently a placeholder to be fleshed out later*/
public class FacultyInformationPage extends LinkedPage{
	private final FacultyMember facultyMember;
	
	/** The constructor for configuring this panel */
	FacultyInformationPage(SelectFacultyPage listPage, FacultyMember facultyMember) {
		super(listPage);
		this.facultyMember = facultyMember;
		
		//placeholder code below, @Jia feel free to delete this
		setLayout(new BorderLayout());
		JLabel placeholder = new JLabel(facultyMember.getName()+" information goes here");
		placeholder.setAlignmentX(0.5f);
		placeholder.setFont(Setting.LARGE_FONT);
		placeholder.setHorizontalAlignment(JLabel.CENTER);
		add(placeholder, BorderLayout.CENTER);
	}

	/** The name of this page is the facultyMember name */
	@Override
	protected String getPageName() {
		return facultyMember.getName();
	}
}
