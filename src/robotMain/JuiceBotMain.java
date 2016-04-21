package robotMain;

import lejos.hardware.Button;
import lejos.utility.Delay;
import robotEV3.EV3Board;
import robotEV3.EV3LineFollower;
import robotEV3.EV3Robot;

public class JuiceBotMain {

	public static void main(String[] args) {
		EV3Robot robot = new EV3Robot();
//		EV3LineFollower lineFollower = new EV3LineFollower(robot);
//		EV3Board board = new EV3Board(lineFollower.getData(), robot);
		
//		board.runFullScale();
		
		robot.setSpeedAndAcceleration(50,50);
		
		while(!Button.ESCAPE.isDown()) {
			System.out.println(robot.getCompassReading());
			robot.turnRight(1,true);
		}
	}

}
