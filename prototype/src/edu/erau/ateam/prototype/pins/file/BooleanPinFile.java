package edu.erau.ateam.prototype.pins.file;

/** A class that represents a hardware file that holds Binary data */
public class BooleanPinFile extends PinFile{
	public BooleanPinFile(String...locations) {
		super(locations);
	}
	
	public void write(boolean value){
		writeToFile(value?"1":"0");
	}
}