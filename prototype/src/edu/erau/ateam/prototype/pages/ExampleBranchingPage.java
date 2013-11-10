package edu.erau.ateam.prototype.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.MainFrame;
import edu.erau.ateam.prototype.Setting;

/** A page used in the color example */
@SuppressWarnings("serial")
public final class ExampleBranchingPage extends LinkedPage{
	/** the name of the page */
	private final String name;
	
	/** The level of nesting */
	private final int level;
	
	/** The contstuctor used to create a colored nested branching page*/
	public ExampleBranchingPage(ExampleBranchingPage prev, ExampleColors color){
		super(prev);
		
		setBackground(color.background);
		name = color.toString();
		this.level = prev.level + 1;
		
		addColoredButtons();
	}
	
	/** a constructor used to create the COLORS page, that has a black background */
	public ExampleBranchingPage(){
		setBackground(Color.BLACK);
		name = "COLORS";
		this.level = 0;
		
		addColoredButtons();
	}
	
	/** adds all the color buttons */
	private void addColoredButtons(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(ExampleColors sub : ExampleColors.values()){
			add(Box.createVerticalStrut(40));
			add(new ColorButton(sub));
		}
	}

	@Override
	protected String getPageName() {
		return name;
	}
	
	/** a button that will navigate to a page */
	class ColorButton extends JButton{
		ColorButton(final ExampleColors color){
			super(color.toString());
			setAlignmentX(0.5f);
			setFont(Setting.LARGE_FONT);
			setMaximumSize(new Dimension(Setting.BUTTON_WIDTH,Setting.BUTTON_HEIGHT));
			
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//don't worry about this. this just determines whether to continue to branch or not.
					MainFrame.instance.navigateTo((level<2) ? new ExampleBranchingPage(ExampleBranchingPage.this,color)
															: new ExampleLeafPage(ExampleBranchingPage.this,color));
				}
			});
		}
	}

}


