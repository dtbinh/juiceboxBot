package robotMain;

import lejos.utility.Delay;
import robotEV3.EV3LineFollower;
import robotEV3.EV3Robot;

public class JuiceBotMain {

	public static void main(String[] args) {
		EV3Robot robot = new EV3Robot();
		EV3LineFollower lineFollower = new EV3LineFollower(robot);
		robot.setSpeedAndAcceleration(500,500);
//		robot.moveLeft(360,true);
//		Delay.msDelay(2000);
//		robot.stopMotors();
		
		robot.close();
	}

}
