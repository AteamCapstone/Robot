package edu.erau.ateam.prototype.control_panel;

import static edu.erau.ateam.prototype.pins.AnalogPin.AIN0;
import static edu.erau.ateam.prototype.pins.AnalogPin.AIN1;
import static edu.erau.ateam.prototype.pins.AnalogPin.AIN2;
import static edu.erau.ateam.prototype.pins.AnalogPin.AIN3;
import static edu.erau.ateam.prototype.pins.AnalogPin.AIN5;
import edu.erau.ateam.prototype.pins.AnalogPin;

/**A map of directions the sonar sensors will face */
enum SonarDirection{
	LEFT(AIN1),
	FRONTLEFT(AIN0),
	FRONT(AIN5),
	FRONTRIGHT(AIN2),
	RIGHT(AIN3);
	
	/** The reader object associated with this direction */
	final SonarReader reader;
	
	static final SonarDirection[] working = {SonarDirection.LEFT, SonarDirection.FRONTLEFT};
	
	private SonarDirection(AnalogPin pin){
		//create a reader for this direction using the given pin
		reader = new SonarReader(this,pin);
	}
}