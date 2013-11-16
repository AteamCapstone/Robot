package edu.erau.ateam.prototype.pages;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

import edu.erau.ateam.prototype.LinkedPage;
import static edu.erau.ateam.prototype.Setting.*;

@SuppressWarnings("serial")
/** The page used for demonstrating the sonar sensors. To be fleshed out later*/
public class DemoSonarPage extends LinkedPage{
	/** The label that shows the sonar reading */
	private final JLabel distance = new JLabel("", SwingConstants.CENTER);

	/** The colored panel */
	private final JPanel colored = new JPanel();
	
	/** A timer that calls the sonar reads */
	private final Timer timer = new Timer(200,new ReaderTimer());
	
	public DemoSonarPage(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		
		//create the toggle button
		final JToggleButton toggle = new JToggleButton("Enable Sonar");
		toggle.setAlignmentX(0.5f);
		toggle.setFont(LARGE_FONT);
		toggle.setPreferredSize(BUTTON_SIZE);
		toggle.setMaximumSize(BUTTON_SIZE);
		add(toggle);
		toggle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(toggle.isSelected()){
					colored.setBackground(GREEN);
					timer.start();
				}else{
					colored.setBackground(GRAY);
					timer.stop();
					distance.setText("");
				}
			}
		});
		
		add(Box.createVerticalGlue());
		
		distance.setFont(LARGE_FONT);
		
		//set up the label panel
		colored.setMaximumSize(BUTTON_SIZE);
		colored.setPreferredSize(BUTTON_SIZE);
		colored.setLayout(new BorderLayout());
		colored.setAlignmentX(0.5f);
		colored.setBackground(GRAY);
		colored.add(distance,BorderLayout.CENTER);
		add(colored);
		
		add(Box.createVerticalGlue());
	}
	
	@Override
	protected String getPageName() {
		return "Sonar Demo";
	}
	
	class ReaderTimer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			new SonarReader().execute();
		}
	}

	class SonarReader extends SwingWorker<Void, Void>{
		private volatile float latest;
		
		@Override
		protected Void doInBackground() throws Exception {
			latest = JniController.instance.readSonar();
			return null;
		}
		
		public void done(){
			if(timer.isRunning()){
				colored.setBackground((latest>13)?RED:GREEN);
				DecimalFormat format = new DecimalFormat("000.00000");//adjust to just one decimal place later
				distance.setText(format.format(latest) + " inches");
			}
		}
	}
}