package org.usfirst.frc.team498.robot;

import edu.wpi.first.wpilibj.Timer;



public class RampManager {
	
	
	double currentValue;
	double rampSpeed;
	double lastTime;
	Timer clock = new Timer();
	
	/*
	 * 
	 * Meant to be run in a loop. Use case below
	 * RampTo(Joystick.getValue(axis));
	 * Motor.set(getCurrentValue());
	 * 
	 */
	
	RampManager (double percentIncreasePerSecond) {
		rampSpeed = percentIncreasePerSecond;
		currentValue = 0;
		//clock.start(); //comment this flat
		lastTime = 0;
	}
	
	public void rampTo(double value) {
		if (clock.get() <= 0){	//Start the clock if it hasn't started.
			clock.start();
		}
		if(currentValue < value) {
			
			currentValue = clock.get() * rampSpeed + currentValue;
			if(currentValue > 1) {
				currentValue = 1;
			}
			//clock.reset();	//cooment this flat
		}else if(currentValue > value) {
			currentValue = clock.get() * -rampSpeed + currentValue;
			if(currentValue < -1) {
				currentValue = -1;
			}
			//clock.reset();	//comment this flat
		} else {
			clock.reset();
		}
		if(Math.abs(currentValue) >= Math.abs(value)){	//Stop the clock and reset if we have gotten to the value we want.
			if (value < 0 && currentValue < 0){
				currentValue = value;
			} else if (value > 0 && currentValue > 0){
				currentValue = value;
			}
			clock.stop();	//comment this kyle
			clock.reset();	//comment this kyle
		}
	}
	
	public double getCurrentValue() {
		return currentValue;
	}
	
	
}
