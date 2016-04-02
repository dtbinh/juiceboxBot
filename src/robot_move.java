import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class robot_move {
	
	private final static int MAX_MOTOR_PORTS = 4;
	
	public static RegulatedMotor[] initializeMotor() {
		RegulatedMotor[] motorArray = new RegulatedMotor[MAX_MOTOR_PORTS];
		
		motorArray[0] = new EV3LargeRegulatedMotor(MotorPort.A);
		motorArray[1] = new EV3LargeRegulatedMotor(MotorPort.B);
		motorArray[2] = new EV3LargeRegulatedMotor(MotorPort.C);
		motorArray[3] = new EV3LargeRegulatedMotor(MotorPort.D);
		
		return motorArray;
	}
	
	// Triggers shooting
	public static void shoot(int degree, RegulatedMotor motor) {
		try {
				motor.setAcceleration(5000);
				motor.setSpeed(5000);
				motor.rotate(degree);
				
				Delay.msDelay(2000);
				
			} catch(Exception e){
				e.printStackTrace();
			}
	}
	
	// Moves the robot Forward
	public static void moveForward(int speed, int rotation, RegulatedMotor[] motors) {
		try {
			motors[1].setAcceleration(speed);
			motors[2].setAcceleration(speed);
			
			motors[1].setSpeed(speed);
			motors[2].setSpeed(speed);
			
			motors[1].rotate(-rotation,true);
			motors[2].rotate(rotation,true);
			Delay.msDelay(100);
			
			motors[1].stop(true);
			motors[2].stop(true);
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	// Moves the robot Backwards
	public static void moveBackward(int speed, int rotation, RegulatedMotor[] motors) {
		try {
			motors[1].setAcceleration(speed);
			motors[2].setAcceleration(speed);
			
			motors[1].setSpeed(speed);
			motors[2].setSpeed(speed);
			
			motors[1].rotate(rotation,true);
			motors[2].rotate(-rotation,true);
			Delay.msDelay(100);
			
			motors[1].stop(true);
			motors[2].stop(true);
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	// Turns the robot left
	public static void turnLeft(int degree, RegulatedMotor[] motors) {
		try {
			motors[0].setAcceleration(500);
			motors[1].setAcceleration(500);
			motors[2].setAcceleration(500);
			
			motors[0].setSpeed(500);
			motors[1].setSpeed(500);
			motors[2].setSpeed(500);

			motors[0].rotate(degree,true);
			motors[1].rotate(degree,true);
			motors[2].rotate(degree,true);
			Delay.msDelay(100);
			
			motors[0].stop(true);
			motors[1].stop(true);
			motors[2].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	// Turns the robot right
	public static void turnRight(int degree, RegulatedMotor[] motors) {
		try {
			motors[0].setAcceleration(500);
			motors[1].setAcceleration(500);
			motors[2].setAcceleration(500);
			
			motors[0].setSpeed(500);
			motors[1].setSpeed(500);
			motors[2].setSpeed(500);
			
			motors[0].rotate(-degree,true);
			motors[1].rotate(-degree,true);
			motors[2].rotate(-degree,true);
			Delay.msDelay(100);
			
			motors[0].stop(true);
			motors[1].stop(true);
			motors[2].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	
	
	public static void resetMotors(RegulatedMotor[] motors) {
		System.out.println("Resetting Motors...");
		for(RegulatedMotor m : motors) {
			m.rotateTo(90,true);
			Delay.msDelay(500);
			m.stop();
		}
	}
	
	// Closes all the motor ports
	public static void closeAllMotor(RegulatedMotor[] motors) {
		for (RegulatedMotor motor : motors) {
			motor.close();
		}
	}
}
