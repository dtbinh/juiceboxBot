import lejos.robotics.RegulatedMotor;

public class robot_move {
	
	public static void shoot(int degree, RegulatedMotor motor) {
		try {
				motor.rotate(degree);
				Thread.sleep(2000);
			} catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public static void moveForward(int degree) {
		try {
		
		Thread.sleep(2000);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void moveBackward(int degree) {
		
	}
	
	public static void moveLeft(int degree) {
		
	}
	
	public static void moveRight(int degree) {
		
	}
	
}
