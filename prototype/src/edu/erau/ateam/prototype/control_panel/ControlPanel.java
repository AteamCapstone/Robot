package edu.erau.ateam.prototype.control_panel;

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import static java.awt.GridBagConstraints.*;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.pins.Motor;
import static edu.erau.ateam.prototype.Setting.*;

@SuppressWarnings("serial")
/** The page used for demonstrating the motors. To be fleshed out later*/
public final class ControlPanel extends LinkedPage{
	
	/** The singleton instance of this page */
	public final static ControlPanel instance = new ControlPanel();
	
	private ControlPanel(){
		setLayout(new GridBagLayout());
		
		//used to keep track of y placement
		int y = 0;

		//place the sonar indicators
		int x = 0;
		for(SonarDirection direction : SonarDirection.values())placeFull(direction.reader.getDisplay(), x++, y);
		
		placeFull(new ToggleSonarSensorsButton(),x,y);
	}

	/** Creates constraints for the given postion */
	private GridBagConstraints getPosition(int x, int y){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.fill = BOTH; 
		
		gbc.insets = new Insets(5,5,5,5);
		return gbc;
	}
	
	/** Places a component and sets its preferred size */
	private void placeFull(Component component,int x, int y){
		component.setPreferredSize(new Dimension(140,50));
		
		add(component,getPosition(x, y));
	}
	
	@Override
	protected String getPageName() {
		return "Control Panel";
	}

	/** A class used to delay updates to the speed panel */
	private final class SpeedWorker extends SwingWorker<Void,Void>{//TODO
		private SpeedWorker() {

		}
		
		@Override
		protected Void doInBackground() throws Exception {
			Thread.sleep(100);
			
			return null;
		}
		
		@Override
		public void done(){

		}
	}
}

/** A painted panel used to show the speed of the motors */
class SpeedPanel extends JPanel{
	int percentofWidthInPixels = 1;
	private final Motor motor;
	
	SpeedPanel(Motor motor){
		this.motor = motor;
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(LARGE_COMPONENT_WIDTH,50));
	}
	
	/* Paints the necessary bars and shapes */
	@Override
	public void paint(Graphics g){
	    Graphics2D graphics = (Graphics2D) g;

	    final int width = getWidth(), height = getHeight();
	    
	    graphics.setColor(BLACK);
	    graphics.fillRect(0, 0, width, height);

	    graphics.setColor(WHITE);
	    graphics.drawRect(0, 0, width, height);    			
	    
	    percentofWidthInPixels = (int) (0.01f*width);

	    //draw the starter bar
	    graphics.setColor(GRAY);
	    graphics.fillRect(0, 4, percentofWidthInPixels, height-4);
	    
	    for(ColorRange range : ColorRange.values())drawRange(graphics, range, height, width);
	}
	
	private void drawRange(Graphics2D graphics, ColorRange range, int height, int width){
		int percent = motor.getDuty();
		if(percent >= range.low){
	    	graphics.setColor(range.color);
	    	
	    	final float rightLimit = (percent>range.high)?range.high:percent;
		   	
			for(float p = range.low; p <= rightLimit; p+=0.015f){
		   		int barwidth = (int) (p*width);
			    graphics.fillRect(barwidth, 4, percentofWidthInPixels, height-4);
		   	}
	    }
	}
}


enum ColorRange{
	SLOW(GREEN, 0.01f, 0.58f),
	MEDIUM(YELLOW, 0.595f, 0.88f),
	FAST(RED, 0.895f, 1f);
	
	final float high;
	final float low;
	final Color color;
	
	private ColorRange(Color color, float low, float high) {
		this.low = low;
		this.high = high;
		this.color = color;
	}
}