import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	private int sideScan = 0;
	
	public robot_scan(robot_EV3 robot,int currentSide) throws IOException {
		ev3 = robot;
		sideScan = currentSide;
		initializeField();
	}
	
	public void initializeField() throws IOException {
		File x= new File ("map.txt");
		x.createNewFile();
		FileWriter writer= new FileWriter(x);
		
		Sound.playTone(500, 1000);
		while(!ev3.isEscDown()) {
			int reading = ev3.getReading();
			
			System.out.println(reading);
			
			if(sideScan == 0) { // Left Side of the line
				if(previousNum != reading) {
					ev3.moveForwardScan(30, 100);
					writer.write("Forward 30\n");
				} else {
					if(reading >= 0 && reading < 6) {
						ev3.turnLeft(correction,50);
						writer.write("Left "+correction +"\n");
					}else if(reading == 7) {
						break;
					}else if(reading >= 6) {
						ev3.turnRight(correction,50);
						writer.write("Right "+correction +"\n");
					}else {
						ev3.moveForwardScan(5, 100);
						writer.write("Forward 5\n");
					}
				}
			} else {
				if(previousNum != reading) {
					ev3.moveForwardScan(30, 100);
					writer.write("Forward 30\n");
				} else {
					if(reading >= 0 && reading < 6) {
						ev3.turnRight(correction,50);
						writer.write("Right "+correction +"\n");
					}else if(reading == 7) {
						break;
					}else if(reading >= 6) {
						ev3.turnLeft(correction,50);
						writer.write("Left "+correction +"\n");
					}else {
						ev3.moveForwardScan(5, 100);
						writer.write("Forward 5\n");
					}
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
		writer.flush();
		writer.close();
		
		System.out.println(x.getAbsolutePath());
		scanFinish = true;
		Sound.playTone(700, 1000);

	}
	
	public boolean isScanFinished() {
		return scanFinish;
	}
}
