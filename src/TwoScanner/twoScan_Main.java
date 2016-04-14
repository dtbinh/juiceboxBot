package TwoScanner;

import java.io.IOException;

public class twoScan_Main {
	
	public static void main(String[] args) throws IOException {
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
	static void run() throws IOException {	
		twoScan_EV3 ev3 = new twoScan_EV3();
		twoScan_scan scan = new twoScan_scan(ev3, ev3.getSideScan());
		
//		while(scan.isScanFinished()) {
//			
//		}
		
		ev3.closeAllColorSensors();
		ev3.closeAllMotor();
	}
}
