package robotEV3;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class EV3LineFollower {
	
	private EV3Robot robot;
	
	public EV3LineFollower(EV3Robot ev3) {
		robot = ev3;
		followLine();
	}
	
	private void followLine() {
		boolean left = true;
		boolean check = true;
		
		System.out.println("Choose Side:");
		
		while(!Button.ENTER.isDown()) {
			if(Button.LEFT.isDown()) {
				left = true;
				
				if(check) {
					System.out.println("Scanning Left Side...");
					check = false;
				}
			} else if(Button.RIGHT.isDown()) {
				left = false;
				
				if(!check) {
					System.out.println("Scanning Right Side...");
					check = true;
				}
			}
		}
		
		check = false;
		
		String lastCommand = "";
		if(left) {
			while(!Button.ESCAPE.isDown() || check) {
				int reading1 = robot.getReading(0); // Right
				int	reading2 = robot.getReading(1); // Left
				
				System.out.println("1: " + reading1 + " 2: " + reading2);
				
				if(reading1 == 6 && reading2 == 6) {
					robot.moveBack(10,true);
					lastCommand = "Back";
				}
				
				if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 6) {
					robot.turnLeft(5,true);
					robot.moveRight(1,true);
					lastCommand = "Left";
				} else if(reading1 == 0 || reading1 == 2 || reading1 == 13 && reading2 == 0) {
					robot.turnLeft(5,true);
					robot.moveRight(1,true);
					lastCommand = "Left";
				}
				
				if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 6) {
					robot.turnRight(5,true);
					robot.moveLeft(1,true);
					lastCommand = "Right";
				} else if(reading2 == 0 || reading2 == 2 || reading2 == 13 && reading1 == 0) {
					robot.turnRight(5,true);
					robot.moveLeft(1,true);
					lastCommand = "Right";
				}
				
				if(reading1 == 6 && reading2 == 3) {
					robot.turnLeft(5,true);
					lastCommand = "Left";
				}
				
				if(reading1 == 0 || reading2 == 0) {
					switch(lastCommand) {
					case "Back":
						robot.moveBack(10,true);
						lastCommand = "Back";
						break;
					case "Left":
						robot.turnLeft(10,true);
						robot.moveBack(10,true);
						lastCommand = "Left";
						break;
					case "Right":
						robot.turnRight(10,true);
						robot.moveBack(10,true);
						lastCommand = "Right";
						break;
					}
				}
				
				if(reading1 == 7 || reading2 == 7) {
					System.out.println("Found Hole...");
					break;
				}
				
				Delay.msDelay(50);
			}
		}
	}
}
