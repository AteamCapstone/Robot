package edu.erau.ateam.robot.pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.erau.ateam.robot.LinkedPage;
import static edu.erau.ateam.robot.Setting.*;

@SuppressWarnings("serial")
/** The page used for demonstrating the Java integration with C/C++ code. 
 *  The GUI's not pretty but it demonstrates the basics
 *  Remember to recompile the dll for your system
 */
public class DemoJniPage extends LinkedPage{
	private final JSpinner spinnerX= new JSpinner(), spinnerY = new JSpinner();
	
	private final JLabel result = new JLabel("= N/A");
	
	public DemoJniPage() {
		super();//home is the previous page
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());//equalize space
		
		//set up the split panel
		JPanel split = new JPanel();
		split.setMaximumSize(new Dimension(300,50));
		split.setLayout(new GridLayout(0,3));
		
		
		//set the models up for the spinners
		spinnerX.setModel(new SpinnerNumberModel(0,-100,100,1));
		spinnerY.setModel(new SpinnerNumberModel(1,-100,100,1));
		
		//change the font
		spinnerX.setFont(LARGE_FONT);
		spinnerY.setFont(LARGE_FONT);
		
		//a JLabel for spacing
		JLabel plus = new JLabel("+",SwingConstants.CENTER);
		plus.setFont(LARGE_FONT);
		
		//add the spinners and panel to the page
		split.add(spinnerX);
		split.add(plus);
		split.add(spinnerY);
		add(split);
		add(Box.createVerticalGlue());
		
		JButton addButton = new JButton("Compute");//create the add button
		addButton.setAlignmentX(0.5f);//center the button
		addButton.setMaximumSize(BUTTON_SIZE);//fix the size
		addButton.setPreferredSize(BUTTON_SIZE);
		addButton.setFont(LARGE_FONT);//set the universal font
		addButton.addActionListener(new AdditionListener());
		
		//add it to the GUI
		add(addButton);
		add(Box.createVerticalGlue());
		
		//modify the result label
		result.setFont(LARGE_FONT);
		result.setAlignmentX(0.5f);
		
		add(result);
		add(Box.createVerticalGlue());
		
	}
	
	@Override
	protected String getPageName() {
		return "JNI Demo";
	}
	
	/** A listener for the test button that calls the JNI code */
	private class AdditionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//get the value of the left spinner
			int x = (Integer) spinnerX.getModel().getValue();
			//get the value of the right spinner
			int y = (Integer) spinnerY.getModel().getValue();
			
			String text = x+" + "+y+" = ";//compile text for the result
			try {
				text +=JniController.instance.demoJni(x, y);
			} catch (Error e) {
				text+="ERROR";//if the library didn't load, return error
			}
			
			result.setText(text);
		}
	}
}


