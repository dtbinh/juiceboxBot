import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class robotMain {
	
	public static void main(String[] args) {
		run();
	}
	
	static void run() {
		RegulatedMotor shootingMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		
		robot_move.shoot(60, shootingMotor);
	}
}
