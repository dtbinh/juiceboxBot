import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class robotMain {
	
	public static void main(String[] args) {
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
	static void run() {	
		robot_EV3 ev3 = new robot_EV3();
		robot_scan scan = new robot_scan(ev3);
		
//		while(scan.isScanFinished()) {
//			
//		}
		
		ev3.closeAllColorSensors();
		ev3.closeAllMotor();
	}
}
