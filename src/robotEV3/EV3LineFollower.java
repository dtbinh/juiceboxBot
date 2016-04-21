package robotEV3;

import java.util.LinkedList;
import java.util.List;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class EV3LineFollower {
	
	private EV3Robot robot;
	private LinkedList<EV3SensorPair> dataList = new LinkedList<EV3SensorPair>();
	
	public EV3LineFollower(EV3Robot ev3) {
		robot = ev3;
		followLine();
	}
	
	public LinkedList<EV3SensorPair> getData() {
		return dataList;
	}
	
	private void followLine() {
		boolean check = false;
		
		String lastCommand = "";
		
		while(!Button.ESCAPE.isDown() || check) {
			int reading1 = robot.getReading(0); // Right
			int	reading2 = robot.getReading(1); // Left
			
			System.out.println("1: " + reading1 + " 2: " + reading2);
			
			if(reading1 == 6 && reading2 == 6) {
				robot.moveBack(10,true);
				lastCommand = "Back";
				dataList.add(new EV3SensorPair("Forward",10));
			}
			
			if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 6) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(5,true);
				lastCommand = "Left";
				dataList.add(new EV3SensorPair("Left",10));
			} else if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 0) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(5,true);
				lastCommand = "Left";
				dataList.add(new EV3SensorPair("Left",10));
			}
			
			if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 6) {
				robot.turnTwoRight(10,true);
				robot.moveBack(5,true);
				lastCommand = "Right";
				dataList.add(new EV3SensorPair("Right",10));
			} else if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 0) {
				robot.turnTwoRight(10,true);
				robot.moveBack(5,true);
				lastCommand = "Right";
				dataList.add(new EV3SensorPair("Right",10));
			}
			
			if(reading1 == 6 && reading2 == 3) {
				robot.turnTwoLeft(10,true);
				robot.moveBack(5,true);
				lastCommand = "Left";
				dataList.add(new EV3SensorPair("Left",10));
			}
			
			if(reading1 == 0 || reading2 == 0) {
				switch(lastCommand) {
				case "Left":
					robot.turnTwoLeft(30,true);
					robot.moveBack(5,true);
					lastCommand = "Left";
					dataList.add(new EV3SensorPair("Left",30));
					break;
				case "Right":
					robot.turnTwoRight(30,true);
					robot.moveBack(5,true);
					lastCommand = "Right";
					dataList.add(new EV3SensorPair("Right",30));
					break;
				}
			}
			
			if(reading1 == 7 || reading2 == 7) {
				//robot.playZeldaTreasureSong();
				System.out.println("Found Hole...");
				check = true;
				dataList.add(new EV3SensorPair("Hole",0));
				break;
			}		
			
			Delay.msDelay(50);
		}
	}
}
