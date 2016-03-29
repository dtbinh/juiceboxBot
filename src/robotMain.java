import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class robotMain {
	
	public static void main(String[] args) {
		run();
	}
	
	static void run() {
		RegulatedMotor[] motors = robot_move.initializeMotor();
		EV3ColorSensor[] colorSensors = robot_sensor.initializeSensor();
		
//		robot_move.shoot(360, motors[3]);
//		robot_move.moveForward(500, motors);
//		robot_move.moveBackward(500, motors);
//		robot_move.turnLeft(360, motors);
//		robot_move.turnRight(360, motors);
		
// 6 = White
// 7 = Black
// 2 = Middle
		robot_move.resetMotors(motors);
		
		while(Button.getButtons() != Button.ID_DOWN) {
			int sample = robot_sensor.getReading(colorSensors);
			System.out.println("                      "+sample);
			if(sample == 7) {
				robot_move.turnRight(10, motors);
				robot_move.moveForward(500, 5, motors);
			}
			
			if(sample == 6) {
				robot_move.turnLeft(10, motors);
				robot_move.moveForward(500, 5, motors);
			}
			
			if(sample == 2) {
				robot_move.moveForward(500, 5, motors);
			}
		}
		
		robot_sensor.closeAllSensor(colorSensors);
		robot_move.closeAllMotor(motors);
	}
}
