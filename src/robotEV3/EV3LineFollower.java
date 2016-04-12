package robotEV3;

import lejos.hardware.Button;

public class EV3LineFollower {
	
	private EV3Robot robot;
	private int correction = 0;
	
	public EV3LineFollower(EV3Robot ev3) {
		robot = ev3;
		followLine();
	}
	
	private void followLine() {
		boolean left = true;
		boolean check = true;
		
		while(!Button.ENTER.isDown()) {
			if(Button.LEFT.isDown()) {
				left = true;
				
				if(check) {
					
				}
			} else if(Button.RIGHT.isDown()) {
				left = false;
				
				if(check) {
					
				}
			}
		}
		
		while(!Button.ESCAPE.isDown()) {
			System.out.println(robot.getReading());
		}
	}
}
