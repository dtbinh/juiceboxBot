package TwoScanner;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.OmniPilot;
import lejos.utility.Delay;

public class twoScan_EV3 {
	
	private final int MAX_MOTOR_PORTS = 4;
	
	private RegulatedMotor[] motorArray;
	
	private EV3ColorSensor colorSensor1; //right
	private EV3ColorSensor colorSensor2;//Left
	
	private int currentRotation = 0;
	private int side = 0; // 0 == LEFT, 1 == RIGHT
	
	public twoScan_EV3() {
		
		initializeMotors();
		System.out.println("Init Motors");
		initializeSensors();
		System.out.println("Init Sensors");
		
		System.out.println("Init Complete");
		System.out.println("Continue = ESC\nLeft Side Scan\n=LEFT\nRight Side Scan\n=RIGHT");
		
		Sound.setVolume(50);
		Sound.playTone(100, 1000);
		
		for(int i=0; i<4; i++) {
			Button.LEDPattern(i);
			Delay.msDelay(200);
		}
		Button.LEDPattern(0);
		
		while(!isEscDown()) {
			if(Button.LEFT.isDown()) {
				side = 0;
				System.out.println("SCAN == Two Scanner");
			}
//			
//			if(Button.RIGHT.isDown()) {
//				side = 1;
//				System.out.println("SCAN == RIGHT");
//			}
			// Endless loop till user presses ESC on the EV3
		}
		Delay.msDelay(2000);
	}
	
	//================== Start Initialization Methods ==================
	// Creates the initial motors (Should only be called once.)
	public void initializeMotors() {
		motorArray = new RegulatedMotor[4];
		
		
		//B Left A back C right
		motorArray[0] = new EV3LargeRegulatedMotor(MotorPort.A);//new EV3LargeRegulatedMotor(MotorPort.A);
		motorArray[1] = new EV3LargeRegulatedMotor(MotorPort.B);
		motorArray[2] = new EV3LargeRegulatedMotor(MotorPort.C);
		motorArray[3] = new EV3MediumRegulatedMotor(MotorPort.D);
		
		for (RegulatedMotor motor: motorArray) {
			motor.setAcceleration(100);
			motor.setSpeed(100);
		}
	}
	
	// Creates the initial sensors (Should only be called once.)
	public void initializeSensors() {
		colorSensor1 = new EV3ColorSensor(SensorPort.S1);
		colorSensor1.setCurrentMode("RGB");
		colorSensor2 = new EV3ColorSensor(SensorPort.S2);
		colorSensor2.setCurrentMode("RGB");
	}
	//================== End of Initialization Methods ==================
	
	//================== Start of Other Methods ==================
	public int getCurrentRotation() {
		return currentRotation;
	}
	
	public boolean isEscDown() {
		return Button.ESCAPE.isDown();
	}
	
	public int getSideScan() {
		return side;
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
			currentRotation += 30;
			System.out.println("Rotation: "+currentRotation);
			
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
			currentRotation += -30;
			System.out.println("Rotation: "+currentRotation);
			
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
			motorArray[0].rotate(degree,true);
			motorArray[1].rotate(degree,true);
			motorArray[2].rotate(degree,true);
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
			motorArray[0].rotate(-degree,true);
			motorArray[1].rotate(-degree,true);
			motorArray[2].rotate(-degree,true);
			Delay.msDelay(delay);
			
			motorArray[0].stop(true);
			motorArray[1].stop(true);
			motorArray[2].stop(true);
		} catch(Exception e) {
			e.printStackTrace();
			closeAllMotor();
		}
	}
	
//	
//	public void rotateFrontRight(int degree, int delay) {
//		try {
//			motorArray[0].rotate(-degree,true);
//			motorArray[1].rotate(-degree,true);
//			motorArray[2].rotate(-degree,true);
//			Delay.msDelay(delay);
//			
//			motorArray[0].stop(true);
//			motorArray[1].stop(true);
//			motorArray[2].stop(true);
//		} catch(Exception e) {
//			e.printStackTrace();
//			closeAllMotor();
//		}
//	}
//	
	
	
	
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
	
	public void stopMotors(){
		Motor.A.stop();
		Motor.B.stop();
		Motor.C.stop();
		Motor.D.stop();
	}
	
	
	public void closeAllMotor() {
		for (RegulatedMotor motor : motorArray) {
			motor.close();
		}
	}
	
	//Motor Scan Methods
	public void moveForwardScan(int rotation, int delay) {
		try {
			currentRotation += 15;
			
			motorArray[2].rotateTo(-currentRotation,true);
			motorArray[0].rotateTo(currentRotation,true);
			Delay.msDelay(delay);
		
			motorArray[2].stop();
			motorArray[0].stop();
		} catch(Exception e){
			e.printStackTrace();
			closeAllMotor();
		}
	}
	//================== End of Motor Methods ==================
	
	//================== Start of Sensor Methods ==================
	public int getReadingRight() {
		int reading = colorSensor1.getColorID();
		return reading;
	}
	public int getReadingLeft() {
		int reading = colorSensor2.getColorID();
		return reading;
	}
	
	public void closeAllColorSensors() {
		colorSensor1.close();
		colorSensor2.close();
	}
	//================== End of Motor Methods ==================
}
