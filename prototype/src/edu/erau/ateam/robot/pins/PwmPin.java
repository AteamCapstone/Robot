package edu.erau.ateam.robot.pins;

import edu.erau.ateam.robot.pins.file.BooleanPinFile;
import edu.erau.ateam.robot.pins.file.IntegerPinFile;

enum PwmPin {
	/** Controls right motor forward speed */
	P9_14,
	/** Controls right motor backwardsPWM speed */
	P9_16,
	/** Controls left motor forward speed */
	P8_34,
	/** Controls left motor backwardsPWM speed */
	P8_36;
	
	/** Controls if the pwm is running */
	private final BooleanPinFile runFile = new BooleanPinFile("ocp.3","pwm_test_"+toString()+".14","run");
	
	/** File that controls the duty cycle */
	private final IntegerPinFile dutyFile = new IntegerPinFile("ocp.3","pwm_test_"+toString()+".14","duty");
	
	/** The period for all motor pwms */
	private static final int period = 1000000000;
	
	/** The value in the run file */
	private boolean running = false;
	
	/** The value in percent of the current duty cycle */
	private byte duty = 0;
	
	private PwmPin(){
		IntegerPinFile periodFile = new IntegerPinFile("ocp.3","pwm_test_"+toString()+".14","period");
		periodFile.write(period);
	}
	
	/** Sets the duty cycle
	 * Should be a value between 0 and 100 */
	public void setDuty(byte duty) {
		if(this.duty != duty){
			this.duty = duty;//save the new value
			
			//convert the value to nanoseconds
			float fDuty = 0.01f * duty;
			final int dutyInNs = (int) (period * fDuty);
			System.out.println("byte="+duty+"  float="+fDuty+"  int="+dutyInNs+"ns");
			
			//save it to a file
			dutyFile.write(dutyInNs);
		}
	}

	/** If this pwm is running */
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean run) {
		if(run != running){
			runFile.write(run);
			this.running = run;
		}
	}
}