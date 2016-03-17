import lejos.robotics.RegulatedMotor;

public class robotMain {
	
	public static void main(String[] args) {
		run();
	}
	
	static void run() {
		RegulatedMotor[] motors = robot_move.initializeMotor();
		
		robot_move.shoot(360, motors[3]);
		robot_move.moveForward(5000, motors);
		robot_move.moveBackward(5000, motors);
		robot_move.closeAllMotor(motors);
	}
}
