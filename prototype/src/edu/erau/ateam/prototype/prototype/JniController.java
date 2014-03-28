package edu.erau.ateam.prototype.prototype;

import java.io.File;

/** A class used to interface with C/C++ */
final class JniController {
	static final JniController instance = new JniController();
	/** The current state of the motors */
	private MotorState currentState = MotorState.STOP;
	
	private JniController(){}
		
	/** Tells the c code to have the motor go backwardsPWM */
	private native void motorBackwards();
	
	/** Tells the c code to have the motor go backwardsPWM */
	private native void motorForward();
	
	/** Tells the c code to have the motor go backwardsPWM */
	private native void stopMotor();

	/** Changes the state of the motor */
	void setMotorState(MotorState state){
		if(currentState == state)return;//quit if no state change
		currentState = state;
		switch(state){
			case FORWARD: motorForward();
			break;
			case BACKWARD: motorBackwards();
			break;
			default: stopMotor();
		}
	}
	
	/** static initializer loads the library */
	static{
		try {
			System.load(new File("PrototypeNative.so").getAbsolutePath());
		} catch (Error e2) {
			System.out.println("PrototypeNative.so not found");
		}
	}
	
	/** adds two numbers in C++ using Jni 
	 * @author KestlerC
	 */
	native int demoJni(int x, int y);
	
	/** Grabs the value in inches from the sonar */
	native float readSonar();
}

enum MotorState{
	FORWARD,
	STOP,
	BACKWARD;
}
