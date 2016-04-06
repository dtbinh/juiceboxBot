import lejos.hardware.Sound;
import lejos.hardware.device.NXTCam;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class robot_scan {
	
	private boolean scanFinish = false;
	
	private int[][] field = new int[50][50];
	
	private robot_EV3 ev3;
	
	private int correction = 2;
	private int previousNum = 0;
	
	public robot_scan(robot_EV3 robot) {
		ev3 = robot;
		initializeField();
	}
	
	public void initializeField() {
		Sound.playTone(500, 1000);
		while(!ev3.isEscDown()) {
			int reading = ev3.getReading();
			
			System.out.println(reading);
			
			if(previousNum != reading) {
				ev3.moveForwardScan(30, 100);
			} else {
				if(reading >= 0 && reading < 6) {
					ev3.turnLeft(correction,50);
				}else if(reading >= 6) {
					ev3.turnRight(correction,50);
				}else {
					ev3.moveForwardScan(5, 100);
				}
			}
			
			previousNum = reading;
		}	
		
//		ev3.setAllMotorsAccel(50);
//		ev3.setAllMotorsSpeed(50);
//		Delay.msDelay(1000);
//		ev3.moveForward(1080, 5000);
//		Delay.msDelay(1000);
//		ev3.moveBackward(1080, 5000);
//		Delay.msDelay(1000);

	}
	
	public boolean isScanFinished() {
		return scanFinish;
	}
}
