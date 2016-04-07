package ReshowMovement;


import java.io.FileNotFoundException;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class reshow_Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		run();
	}
	
	/*
	 * ====== Color Sensor ======
	 * 0 = Red
	 * 2 = Middle
	 * 6 = White
	 * 7 = Black
	 * ====== Color Sensor ======
	 * 
	 * ====== Motors ======
	 * (Facing the front of the robot.)
	 * 0 = Back Motor
	 * 1 = Right Motor
	 * 2 = Left Motor
	 * ====== Motors ======
	 * 
	 * ====== Notes ======
	 * - Default speed and acceleration is 100
	 * ====== Notes ======
	 */
	static void run() throws FileNotFoundException  {	
		reshow_moveclass ev3 = new reshow_moveclass();
		
//		while(scan.isScanFinished()) {
//			
//		}
		
//		ev3.closeAllColorSensors();
		ev3.closeAllMotor();
	}
}
