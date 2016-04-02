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
//		robot_move.moveForward(500, 1080, motors);
//		robot_move.moveBackward(500, 1080, motors);
//		robot_move.turnLeft(360, motors);
//		robot_move.turnRight(360, motors);
		
// 6 = White
// 7 = Black
// 2 = Middle
// 0 = Red
		
// 0 = right
// 1 = left
		while(Button.getButtons() != Button.ID_DOWN) {
			int sample = robot_sensor.getReading(colorSensors[0]);
			int sample2 = robot_sensor.getReading(colorSensors[1]);
			System.out.println("                      "+sample);
			
			if(sample2 == 0 && sample != 0) {
				robot_move.turnLeft(5, motors);
			}else if(sample == 0 && sample2 !=0) {
				robot_move.turnRight(5, motors);
			}else if (sample ==0 && sample2 ==0)
			{
				robot_move.moveBackward(4, 5, motors);
			}
			else {
				robot_move.moveForward(500, 1, motors);
			}
			
						
//			if(sample == 3 || sample == 1 || sample == 2) {
//				robot_move.moveForward(500, 5, motors);
//			}
//			
//			if(sample == 7 || sample == 13) {
//				robot_move.shoot(1800, motors[3]);
//			}
		}
		robot_sensor.closeAllSensor(colorSensors);
		robot_move.closeAllMotor(motors);
	}
}
