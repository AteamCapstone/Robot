package edu.erau.ateam.prototype.pins;

import edu.erau.ateam.prototype.pins.file.IntegerPinFile;

public enum AnalogPin{
	AIN0,//SONAR
	AIN1,//SONAR
	AIN2,//SONAR
	AIN3,//SONAR
	AIN4,
	AIN5,//SONAR
	AIN6;
	
	private final IntegerPinFile file = new IntegerPinFile("ocp.3","helper.14",toString());
	
	/** Keep in mind this value is not stored locally and will perform a full file read
	 * If performing frequent operations, this value should be stored locally in your method */
	public int readPin(){
		return file.read();
	}
}