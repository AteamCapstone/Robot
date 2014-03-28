package edu.erau.ateam.prototype.prototype;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import static javax.swing.JScrollBar.VERTICAL;
import static javax.swing.JScrollPane.*;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.MainFrame;
import static edu.erau.ateam.prototype.Setting.*;
import edu.erau.ateam.prototype.data.DataStore;
import edu.erau.ateam.prototype.data.FacultyMember;

/** a page with a list of faculty members */
@SuppressWarnings("serial")
public class SelectFacultyPage extends LinkedPage{
	
	public SelectFacultyPage(){
		//adds a scrolling pane
		JPanel interior = new JPanel();//the jpanel inside the scroller
		JScrollPane scroller = new JScrollPane(interior);
		scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(DEFWIDTH-20,PAGE_HEIGHT));
		
		//adjusts the scrollbar
		JScrollBar scrollbar = new JScrollBar(VERTICAL);
		scrollbar.setPreferredSize(new Dimension(SCROLLBAR_WIDTH, PAGE_HEIGHT));//this is huge because keep in mind this is a tiny touchscreen
		
		//adds the modified scrollbar
		scroller.setVerticalScrollBar(scrollbar);
		
		//adss the scrollpane
		add(scroller);
		
		//use the box layout and each new component should be used vertically
		interior.setLayout(new BoxLayout(interior,BoxLayout.Y_AXIS));
		
		//a loop that adds buttons and spacing for each professor
		for(FacultyMember facultyMember : DataStore.getInstance().getProfessors()){
			//adss spacing between each button
			interior.add(Box.createVerticalStrut(SPACING_SIZE));		
			//adds buttons for each professor
			interior.add(new ProfessorButton(facultyMember));
		}
		//extra spacing at the end
		interior.add(Box.createVerticalStrut(SPACING_SIZE));
	}
	
	@Override
	protected String getPageName() {
		return "Select Professor";
	}
	
	/** a button that will navigate to a page */
	class ProfessorButton extends JButton{
		ProfessorButton(final FacultyMember facultyMember){
			//button settings and sizes
			super(facultyMember.getName());
			setAlignmentX(0.5f);
			setFont(LARGE_FONT);
			setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			
			//adds a listener that tells the button to navigate to a page
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame.getInstance().navigateTo(new FacultyInformationPage(SelectFacultyPage.this, facultyMember));
				}
			});
		}
	}
}
