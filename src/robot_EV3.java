import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.OmniPilot;
import lejos.utility.Delay;

public class robot_EV3 {
	
	private final int MAX_MOTOR_PORTS = 4;
	private final int MAX_COLOR_SENSORS = 1; // Subjected to change
	
	private RegulatedMotor[] motorArray;
	private EV3ColorSensor sensor;
	
	private int currentRotation = 0;
	
	public robot_EV3() {
		
		initializeMotors();
		System.out.println("Initialized Motors");
		initializeSensors();
		System.out.println("Initialized Sensors");
		
		Button.LEDPattern(1);
		Sound.setVolume(100);
		Sound.playTone(100, 1000);
		System.out.println("Initialization Complete");
		
		while(!isEscDown()) {
			// Endless loop till user presses ESC on the EV3
		}
		Delay.msDelay(2000);
	}
	
	//================== Start Initialization Methods ==================
	// Creates the initial motors (Should only be called once.)
	public void initializeMotors() {
		motorArray = new RegulatedMotor[MAX_MOTOR_PORTS];
		
		motorArray[0] = new EV3LargeRegulatedMotor(MotorPort.A);
		motorArray[1] = new EV3LargeRegulatedMotor(MotorPort.B);
		motorArray[2] = new EV3LargeRegulatedMotor(MotorPort.C);
		motorArray[3] = new EV3LargeRegulatedMotor(MotorPort.D);
		
		for (RegulatedMotor motor: motorArray) {
			motor.setAcceleration(100);
			motor.setSpeed(100);
		}
	}
	
	// Creates the initial sensors (Should only be called once.)
	public void initializeSensors() {
		sensor = new EV3ColorSensor(SensorPort.S2);
		
		//colorSensorArray[1] = new EV3ColorSensor(SensorPort.S3);
	}
	//================== End of Initialization Methods ==================
	
	//================== Start of Other Methods ==================
	public boolean isEscDown() {
		return Button.ESCAPE.isDown();
	}
	//================== End of Other Methods ==================
	
	//================== Start of Motor Methods ==================
	public void shoot(int delay) {
		try {
				motorArray[0].rotate(360);
				
				Delay.msDelay(delay);
				
			} catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void moveForward(int rotation, int delay) {
		try {
			currentRotation++;
			motorArray[1].rotateTo(-rotation,true);
			motorArray[2].rotateTo(rotation);
			Delay.msDelay(delay);
		
			motorArray[1].stop(true);
			motorArray[2].stop(true);
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
	public void moveBackward(int rotation, int delay) {
		try {
			currentRotation--;
			motorArray[1].rotateTo(rotation,true);
			motorArray[2].rotateTo(-rotation);
			Delay.msDelay(delay);
			
			motorArray[1].stop(true);
			motorArray[2].stop(true);
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
	public void moveLeft(int rotation, int delay) {
		try {
			motorArray[3].rotate(rotation - rotation/3,true);
			motorArray[2].rotate(rotation - rotation/3,true);
			motorArray[3].rotate(rotation);
			Delay.msDelay(delay);
			motorArray[1].stop(true);
			motorArray[2].stop(true);
			motorArray[3].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
	public void moveRight(int rotation, int delay) {
		try {
			motorArray[3].rotate(-rotation - rotation/3,true);
			motorArray[2].rotate(-rotation - rotation/3,true);
			motorArray[3].rotate(rotation);
			Delay.msDelay(delay);
			motorArray[1].stop(true);
			motorArray[2].stop(true);
			motorArray[3].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor();
		}
	}

	public void turnLeft(int degree, int delay) {
		try {
			motorArray[0].rotateTo(degree,true);
			motorArray[1].rotateTo(degree,true);
			motorArray[2].rotateTo(degree,true);
			Delay.msDelay(delay);
			
			motorArray[0].stop(true);
			motorArray[1].stop(true);
			motorArray[2].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
	public void turnRight(int degree, int delay) {
		try {
			motorArray[0].rotateTo(-degree,true);
			motorArray[1].rotateTo(-degree,true);
			motorArray[2].rotateTo(-degree,true);
			Delay.msDelay(delay);
			
			motorArray[0].stop(true);
			motorArray[1].stop(true);
			motorArray[2].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
	public void setMotorSpeed(int motor, int speed) {
		motorArray[motor].setSpeed(speed);
	}
	
	public void setAllMotorsSpeed(int speed) {
		for(RegulatedMotor motor : motorArray) {
			motor.setSpeed(speed);
		}
	}
	
	public void setMotorAcceleration(int motor, int acceleration) {
		motorArray[motor].setAcceleration(acceleration);
	}
	
	public void setAllMotorsAccel(int acceleration) {
		for(RegulatedMotor motor : motorArray) {
			motor.setAcceleration(acceleration);
		}
	}
	
	public void closeAllMotor() {
		for (RegulatedMotor motor : motorArray) {
			motor.close();
		}
	}
	//================== End of Motor Methods ==================
	
	//================== Start of Sensor Methods ==================
	public int getReading() {
		int reading = sensor.getColorID();
		return reading;
	}
	
	public void closeAllColorSensors() {
		for(int i=0; i<MAX_COLOR_SENSORS-1; i++) {
			sensor.close();
//			colorSensorArray[i].close();
		}
	}
	//================== End of Motor Methods ==================
}
