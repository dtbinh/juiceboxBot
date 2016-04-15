package robotMain;

import lejos.utility.Delay;
import robotEV3.EV3LineFollower;
import robotEV3.EV3Robot;

public class JuiceBotMain {

	public static void main(String[] args) {
		EV3Robot robot = new EV3Robot();
		EV3LineFollower lineFollower = new EV3LineFollower(robot);
//		robot.moveLeft(200,true);
//		Delay.msDelay(5000);
//		robot.moveRight(200,true);
//		Delay.msDelay(5000);
		
		robot.close();
	}

}
