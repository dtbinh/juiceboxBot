package robotEV3;

import java.util.LinkedList;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class EV3Board {
	
	private LinkedList<EV3SensorPair> data = new LinkedList<EV3SensorPair>();
	private EV3Robot robot;
	
	int multiplier = 4;
	
	public EV3Board(LinkedList<EV3SensorPair> x, EV3Robot ev3) {
		data = x;
		robot = ev3;
		
		System.out.println("Press Enter to Run");
		while(!Button.ENTER.isDown()) {
			
		}
	}
	
	public void runFullScale() {
		robot.dropArm();
		Delay.msDelay(2000);
		
		for(EV3SensorPair command : data) {
			if(Button.ESCAPE.isDown()) {
				break;
			}
			switch(command.getCommand()) {
			case "Forward":
				robot.moveForward(command.getRotation() * multiplier,true);
				Delay.msDelay(100);
				break;
			case "Left":
				robot.turnTwoLeft(command.getRotation() * (3/2),true);
				Delay.msDelay(100);
				break;
			case "Right":
				robot.turnTwoRight(command.getRotation() * (3/2),true);
				Delay.msDelay(100);
				break;
			default:
				if(command.getCommand().equals("Hole")) {
					break;
				}
			}
		}
		
		//robot.playSongOfStorms();
	}
}
