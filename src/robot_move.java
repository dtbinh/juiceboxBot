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
	
	public static void moveForward(int speed, RegulatedMotor[] motors) {
		try {
			motors[1].setAcceleration(speed);
			motors[2].setAcceleration(speed);
			
			motors[1].setSpeed(speed);
			motors[2].setSpeed(speed);
			
			motors[1].backward();
			motors[2].forward();
			
			Delay.msDelay(2000);
			
			motors[1].stop();
			motors[2].stop();
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	public static void moveBackward(int speed, RegulatedMotor[] motors) {
		try {
			motors[1].setAcceleration(speed);
			motors[2].setAcceleration(speed);
			
			motors[1].setSpeed(speed);
			motors[2].setSpeed(speed);
			
			motors[1].forward();
			motors[2].backward();
			
			Delay.msDelay(2000);
			
			motors[1].stop();
			motors[2].stop();
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor(motors);
		}
	}
	
	public static void moveLeft(int degree, RegulatedMotor motor) {
		
	}
	
	public static void moveRight(int degree, RegulatedMotor motor) {
		
	}
	
	public static void closeAllMotor(RegulatedMotor[] motors) {
		for (RegulatedMotor motor : motors) {
			motor.close();
		}
	}
	
}
