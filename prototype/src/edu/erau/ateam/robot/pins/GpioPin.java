package edu.erau.ateam.robot.pins;

import edu.erau.ateam.robot.pins.file.BooleanPinFile;

/** In use gpio pins.  Keep in mind the syntax for declaring the constant is used in commands */
public enum GpioPin{
	gpio31,
	gpio67,
	gpio68;
	
	public final BooleanPinFile 	value = new BooleanPinFile("gpio",toString(),"value"),
									direction = new BooleanPinFile("gpio",toString(),"direction");
}
