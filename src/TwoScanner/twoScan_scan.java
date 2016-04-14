package TwoScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lejos.hardware.Sound;
import lejos.hardware.device.NXTCam;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class twoScan_scan {

	private boolean scanFinish = false;

	private twoScan_EV3 ev3;

	private int correction = 2;
	private int previousNumLeft = 0;
	private int previousNumRight = 0;
	private int sideScan = 0;

	public twoScan_scan(twoScan_EV3 robot, int currentSide) throws IOException {
		ev3 = robot;
		sideScan = currentSide;
		initializeField();
	}

	public void initializeField() throws IOException {
		// File x= new File ("map.txt");
		// x.createNewFile();
		// FileWriter writer= new FileWriter(x);

		Sound.playTone(500, 1000);
		while (!ev3.isEscDown()) {
			int readingRight = ev3.getReadingRight();
			int readingLeft = ev3.getReadingLeft();

			System.out.println(readingRight + " + " + readingLeft);
			if (previousNumLeft != readingLeft && previousNumRight != readingRight) {
				ev3.moveForwardScan(30, 100);
			} else {
				if ((readingRight == 0 && readingLeft == 6) || (readingRight == 2 && readingLeft == 6)) {
					ev3.turnLeft(30, 50);
				} else if ((readingRight == 6 && readingLeft == 6)) {

					ev3.moveForward(30, 100);
				} else if ((readingRight == 6 && readingLeft == 0) || (readingRight == 6 && readingLeft == 2)) {
					ev3.turnRight(30, 50);
				} else if (readingRight == 7 && readingLeft == 7) {
					break;
				} else if ((readingRight == 0 && readingLeft == 0) || (readingRight == 2 && readingLeft == 2)
						|| (readingRight == 0 && readingLeft == 2) || (readingRight == 2 && readingLeft == 0)) {

					ev3.moveForwardScan(10, 100);
				} else {

					ev3.moveForwardScan(30, 100);
				}
			}

			previousNumLeft = readingLeft;

			previousNumRight = readingRight;
		}

		// ev3.setAllMotorsAccel(50);
		// ev3.setAllMotorsSpeed(50);
		// Delay.msDelay(1000);
		// ev3.moveForward(1080, 5000);
		// Delay.msDelay(1000);
		// ev3.moveBackward(1080, 5000);
		// Delay.msDelay(1000);
		// writer.flush();
		// writer.close();

		// System.out.println(x.getAbsolutePath());
		scanFinish = true;
		Sound.playTone(700, 1000);

	}

	public boolean isScanFinished() {
		return scanFinish;
	}
}
