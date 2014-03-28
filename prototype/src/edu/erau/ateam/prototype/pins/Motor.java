package edu.erau.ateam.prototype.pins;

import static edu.erau.ateam.prototype.pins.MotorState.*;

public class Motor{
	/** The left motor.  Located on P8 pins 14 & 16.  */
	public static final Motor leftMotor = new Motor(PwmPin.P9_14,PwmPin.P9_16);
	
	/** The right motor.  Located on P8 pins 34 & 36.  */
	public static final Motor rightMotor = new Motor(PwmPin.P8_34,PwmPin.P8_36);

	/** the two pwms associated with this motor */
	public final PwmPin forwardPWM, backwardPWM;
	
	/** the duty cycle of the motor */
	private byte duty;

	/** The direction of the motor*/
	private MotorState state = STOPPED;

	private Motor(PwmPin forward, PwmPin backward) {
		super();
		this.forwardPWM = forward;
		this.backwardPWM = backward;
	}
	
	public byte getDuty() {
		return duty;
	}
	
	/** Stop the motor */
	public void stop(){
		if(state == FORWARD){
			forwardPWM.setRunning(false);
			state = STOPPED;
		}else if(state == BACKWARD){
			backwardPWM.setRunning(false);
			state = STOPPED;
		}
	}
	
	/** Sets the speed of the motor.  The input of this method should be between -100 and 100*/
	public void setSpeed(byte percent){
		if(percent < -100 || percent > 100){
			throw new IllegalArgumentException();
		}else if(percent > 0){
			//if the backward pwm is running, stop it
			if(state == BACKWARD)backwardPWM.setRunning(false);
			
			//set the state to forward, and enable forward pwm if not already
			if(state != FORWARD){
				forwardPWM.setRunning(true);
				state = FORWARD;
			}

			//update the pwm
			forwardPWM.setDuty(percent);
			duty = percent;
		}else if(percent < 0){
			//if the forward pwm is running, stop it
			if(state == FORWARD)forwardPWM.setRunning(false);
			
			//set the state to backward, and enable backward pwm if not already
			if(state != BACKWARD){
				backwardPWM.setRunning(true);
				state = BACKWARD;
			}
			
			duty = (byte) -percent;
			//update the pwm
			backwardPWM.setDuty(duty);
		}else{//only remaining speed is 0
			stop();
			duty = 0;
		}
	}
}

enum MotorState{
	FORWARD, BACKWARD, STOPPED;
}