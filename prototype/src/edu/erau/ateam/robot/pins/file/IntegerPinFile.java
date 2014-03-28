package edu.erau.ateam.robot.pins.file;

/** A class that represents a hardware file that holds Binary data */
public class IntegerPinFile extends PinFile{
	public IntegerPinFile(String...locations) {
		super(locations);
	}
	
	public void write(int value){
		writeToFile(""+value);
	}

	public int read() {
		return Integer.parseInt(super.readRaw());
	}
}