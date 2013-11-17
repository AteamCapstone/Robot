package edu.erau.ateam.prototype.pages;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import edu.erau.ateam.prototype.LinkedPage;
import static edu.erau.ateam.prototype.Setting.*;

@SuppressWarnings("serial")
/** The page used for demonstrating the motors. To be fleshed out later*/
public class DemoMotorPage extends LinkedPage{
	public DemoMotorPage(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		ButtonGroup group = new ButtonGroup();
		
		//creates a button and space for each state
		for(MotorState state: MotorState.values()){
			add(Box.createVerticalGlue());
			
			StateButton button = new StateButton(state);
			add(button);
			group.add(button);
			button.setAlignmentX(0.5f);
		}
		add(Box.createVerticalGlue());
	}
	
	@Override
	protected String getPageName() {
		return "Motor Demo";
	}
}

/** Radio buttons that change the state of the motor */
@SuppressWarnings("serial")
class StateButton extends JRadioButton{
	StateButton(final MotorState state){
		super(state.toString());
		setFont(LARGE_FONT.deriveFont(Font.BOLD,36));
		setPreferredSize(BUTTON_SIZE);
		setMaximumSize(BUTTON_SIZE);

		if(state == MotorState.STOP)setSelected(true);//make the stop button selected by default
		//changes the motor state
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JniController.instance.setMotorState(state);
			}
		});
	}
}