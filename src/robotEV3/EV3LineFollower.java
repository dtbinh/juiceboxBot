package robotEV3;

import java.util.LinkedList;
import java.util.List;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class EV3LineFollower {
	
	private EV3Robot robot;
	private List<EV3SensorPair> dataList = new LinkedList<EV3SensorPair>();
	
	public EV3LineFollower(EV3Robot ev3) {
		robot = ev3;
		followLine();
	}
	
	private void followLine() {
		boolean check = false;
		
		String lastCommand = "";
		
		while(!Button.ESCAPE.isDown() || check) {
			int reading1 = robot.getReading(0); // Right
			int	reading2 = robot.getReading(1); // Left
			
			dataList.add(new EV3SensorPair(reading1,reading2));
			
			System.out.println("1: " + reading1 + " 2: " + reading2);
			
			if(reading1 == 6 && reading2 == 6) {
				robot.moveBack(10,true);
				lastCommand = "Back";
			}
			
			if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 6) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(2,true);
				lastCommand = "Left";
			} else if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 0) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(2,true);
				lastCommand = "Left";
			}
			
			if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 6) {
				robot.turnTwoRight(10,true);
				robot.moveBack(2,true);
				lastCommand = "Right";
			} else if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 0) {
				robot.turnTwoRight(10,true);
				robot.moveBack(2,true);
				lastCommand = "Right";
			}
			
			if(reading1 == 6 && reading2 == 3) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(2,true);
				lastCommand = "Left";
			}
			
			if(reading1 == 0 || reading2 == 0) {
				switch(lastCommand) {
				case "Left":
					robot.turnTwoLeft(30,true);
					robot.moveBack(5,true);
					lastCommand = "Left";
					break;
				case "Right":
					robot.turnTwoRight(30,true);
					robot.moveBack(5,true);
					lastCommand = "Right";
					break;
				}
			}
			
			if(reading1 == 7 || reading2 == 7) {
				//robot.playZeldaTreasureSong();
				System.out.println("Found Hole...");
				check = true;
				break;
			}		
			
			Delay.msDelay(50);
		}
	}
}
