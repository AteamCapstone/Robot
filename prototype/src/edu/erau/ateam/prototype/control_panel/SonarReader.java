package edu.erau.ateam.prototype.control_panel;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import com.google.common.util.concurrent.AtomicDouble;

import edu.erau.ateam.prototype.pins.AnalogPin;

/** A class used to read information from a sonar sensor */
class SonarReader{
	/** A timer used to run the readers */
	static final Timer timer = new Timer(500,new ReaderTimer());
	
	/** The pin for this reader */
	final AnalogPin pin;
	
	/** the display for this reader */
	private final SonarPanel display;
	
	/** The last read value of this reader */
	private final AtomicDouble value = new AtomicDouble(11.0);

	@SuppressWarnings("unused")//TODO label the panel
	private final SonarDirection direction;
	
	SonarReader(SonarDirection direction, AnalogPin pin) {
		this.pin = pin;
		this.direction = direction;
		this.display = new SonarPanel();
	}
	
	/** Reads the sonar sensor */
	private void read() {
		value.set(pin.readPin());
	}
	
	/** Gets the display of this reader.  This is encapsulated and 
	 * returned as a JPanel to prevent issues with future changes */
	SonarPanel getDisplay(){
		return display;
	}
	
	/** 
	 * A class used to read the sonar data in another thread 
	 */
	static final class SonarUpdater extends SwingWorker<Void, Void>{
		@Override
		protected Void doInBackground() throws Exception {
			for(SonarDirection direction : SonarDirection.working){
				direction.reader.read();
			}
			return null;
		}
		
		
		public void done(){
			if(timer.isRunning()){
				for(SonarDirection direction : SonarDirection.working){
					if(direction.reader == null){
						System.out.println("null reader for "+direction);
						timer.stop();
						return;
					}
					
					if(direction.reader.display == null){
						System.out.println("null display for "+direction);
						timer.stop();
						return;
					}
					direction.reader.display.update();
				}
			}
		}
	}
	
	
	/** 
	 * A class used to display information from a SonarReader 
	 */
	@SuppressWarnings("serial")
	final class SonarPanel extends JPanel{
		final JLabel label = new JLabel();
		
		public SonarPanel(){
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

			//the font used for the labels
			final Font font = new Font("Consolas", Font.BOLD, 14);
			
			//for spacing vertically
			add(Box.createGlue());
			
			//label the display with the direction
			JLabel dirLabel = new JLabel(pin.toString());
			dirLabel.setFont(font);
			dirLabel.setAlignmentX(0.5f);
			add(dirLabel);

			//for spacing vertically
			add(Box.createGlue());
			
			//add the label for displaying the number
			label.setFont(font);
			label.setAlignmentX(0.5f);
			add(label);

			//for spacing vertically
			add(Box.createGlue());
			
			setDisabled();
		}
		
		/** Updates the display to reflect the current value */
		private void update(){
			final Color color;
			final double value = SonarReader.this.value.get();
			if(value < 13)
				color = RED;
			else if(value < 16)
				color = YELLOW;
			else 
				color = GREEN;
			setBackground(color);
			
			DecimalFormat format = new DecimalFormat("000.00");//adjust to just one decimal place later
			label.setText(format.format(value) + " inches");
			updateUI();
		}

		void setDisabled() {
			setBackground(WHITE);
			label.setText("000.00 inches");
		}
	}
}

/** A timer that refreshes all the readers */
class ReaderTimer implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent event) {
		new SonarReader.SonarUpdater().execute();
	}
}

/** A class used to enable and disable sonar reading on the control panel */
@SuppressWarnings("serial")
final class ToggleSonarSensorsButton extends JToggleButton implements ActionListener{
	ToggleSonarSensorsButton(){
		super("Enable Sonar");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isSelected()){
			setText("Disable Sonar");
			SonarReader.timer.start();
		}else{
			setText("Enable Sonar");
			SonarReader.timer.stop();
			for(SonarDirection direction : SonarDirection.values())direction.reader.getDisplay().setDisabled();
		}
		updateUI();
	}
}