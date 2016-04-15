package robotEV3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class EV3Robot implements EV3RobotMovement{
	
	private List<EV3Motor> motorList;
	private List<EV3ColorSensor> colorSensorList;
	
	private final int TONE_DURATION = 1000;
	private final int TONE_FREQ = 500;
	
	public EV3Robot() {
		System.out.println("Init Sensors...");
		initSensors();
		System.out.println("Init Motors...");
		initMotors();
		System.out.println("Finished Init...");
		playStartUpSong();
		Button.LEDPattern(3);
		while(!Button.ENTER.isDown()) {} // Waits for user input.
	}
	
	// Should close the sensors and motors.
	public void close() {
		for(EV3Motor motor : motorList) {
			motor.close();
		}
		
		for(EV3ColorSensor sensor : colorSensorList) {
			sensor.close();
		}
	}
	
	private void playRandom() {	
		while(!Button.ENTER.isDown()) {
			Sound.playTone(getRandom(), 100);
		}
	}
	
	private int getRandom() {
		Random random = new Random();
		int rand = random.nextInt(1000);
		System.out.println(rand);
		return rand;
	}
	
	// Plays start up song (Created by Sam :))
	private void playStartUpSong() {
		Sound.playTone(659, 150);
        Sound.playTone(784, 150);
        Sound.playTone(1319, 600);
        Sound.playTone(659, 150);
        Sound.playTone(784, 150);
        Sound.playTone(1319, 600);
        Sound.playTone(1480, 450);
        Sound.playTone(1568, 150);
        Sound.playTone(1480, 150);
        Sound.playTone(1568, 150);
        Sound.playTone(1480, 150);
        Sound.playTone(1175, 150);
        Sound.playTone(988, 600);
        Sound.playTone(988, 300);
        Sound.playTone(659, 300);
        Sound.playTone(784, 150);
        Sound.playTone(880, 150);
        Sound.playTone(988, 900);
        Sound.playTone(988, 300);
        Sound.playTone(659, 300);
        Sound.playTone(784, 150);
        Sound.playTone(880, 150);
        Sound.playTone(740, 900);

        Sound.playTone(659, 150);
        Sound.playTone(784, 150);
        Sound.playTone(1319, 600);
        Sound.playTone(659, 150);
        Sound.playTone(784, 150);
        Sound.playTone(1319, 600);
        Sound.playTone(1480, 450);
        Sound.playTone(1568, 150);
        Sound.playTone(1480, 150);
        Sound.playTone(1568, 150);
        Sound.playTone(1480, 150);
        Sound.playTone(1175, 150);
        Sound.playTone(988, 600);
        Sound.playTone(988, 300);
        Sound.playTone(659, 300);
        Sound.playTone(784, 150);
        Sound.playTone(880, 150);
        Sound.playTone(988, 600);
        Sound.playTone(988, 300);
        Sound.playTone(659, 1800);
	}
	
	// Initializes the Sensors
	private void initMotors() {
		motorList = new LinkedList<EV3Motor>();
		
		motorList.add(new EV3Motor(MotorPort.A, "Back"));
		motorList.add(new EV3Motor(MotorPort.B, "Left"));
		motorList.add(new EV3Motor(MotorPort.C, "Right"));
		motorList.add(new EV3Motor(MotorPort.D, "Shoot"));
		
		Button.LEDPattern(2);
	}
	
	// Initializes the Motors
	private void initSensors() {
		colorSensorList = new LinkedList<EV3ColorSensor>();
		
		colorSensorList.add(new EV3ColorSensor(SensorPort.S1));
		colorSensorList.add(new EV3ColorSensor(SensorPort.S2));
		Button.LEDPattern(1);
	}

	@Override
	public void moveForward(int rotation, boolean immediateReturn) {
		System.out.println("Moving Forward");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			if(tag.equals("Left")) {
				motor.rotate(rotation, immediateReturn);
			}
			
			if(tag.equals("Right")) {
				motor.rotate(-rotation, immediateReturn);
			}
		}
	}

	@Override
	public void moveBack(int rotation, boolean immediateReturn) {
		System.out.println("Moving Backwards");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			if(tag.equals("Left")) {
				motor.rotate(-rotation, immediateReturn);
			}
			
			if(tag.equals("Right")) {
				motor.rotate(rotation, immediateReturn);
			}
		}
	}

	@Override
	public void moveLeft(int rotation, boolean immediateReturn) {
		int speed = 0, acceleration = 0;
		
		System.out.println("Moving Left");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			
			if(tag.equals("Left") || tag.equals("Right")) {
				speed = motor.getSpeed();
				acceleration = motor.getAcceleration();
				motor.setSpeed_setAcceleration(speed/2,acceleration/2);
				motor.rotate(-rotation, immediateReturn);
			}
			
			if(tag.equals("Back")) {
				motor.rotate(rotation * 2, immediateReturn);
			}
		}
		
		for(EV3Motor motor : motorList) {
			motor.setSpeed_setAcceleration(speed, acceleration);
		}
	}

	@Override
	public void moveRight(int rotation, boolean immediateReturn) {
		int speed = 0, acceleration = 0;
		
		System.out.println("Moving Left");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			
			if(tag.equals("Left") || tag.equals("Right")) {
				speed = motor.getSpeed();
				acceleration = motor.getAcceleration();
				motor.setSpeed_setAcceleration(speed/2,acceleration/2);
				motor.rotate(rotation, immediateReturn);
			}
			
			if(tag.equals("Back")) {
				motor.rotate(-rotation * 2, immediateReturn);
			}
		}
		
		for(EV3Motor motor : motorList) {
			motor.setSpeed_setAcceleration(speed, acceleration);
		}
	}
	
	@Override
	public void turnLeft(int rotation, boolean immediateReturn) {
		System.out.println("Turning Left");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			if(tag.equals("Left") || tag.equals("Right") || tag.equals("Back")) {
				motor.rotate(-rotation, immediateReturn);
			}
		}
	}

	@Override
	public void turnRight(int rotation, boolean immediateReturn) {
		System.out.println("Turning Right");
		for(EV3Motor motor : motorList) {
			String tag = motor.getTag();
			if(tag.equals("Left") || tag.equals("Right") || tag.equals("Back")) {
				motor.rotate(rotation, immediateReturn);
			}
		}
	}

	@Override
	public void setSpeedAndAcceleration(int speed, int acceleration) {
		System.out.println("Setting Speed and Acceleration");
		for(EV3Motor motor : motorList) {
			motor.setSpeed_setAcceleration(speed,acceleration);
		}
	}

	@Override
	public void stopMotors() {
		for(EV3Motor motor : motorList) {
			motor.stop();
		}
	}

	@Override
	public void shoot(int strength) {
		for(EV3Motor motor : motorList) {
			if(motor.getTag().equals("Shoot")) {
				motor.setSpeed_setAcceleration(strength,strength);
				motor.rotate(10000,true);
			}
		}
	}

	@Override
	public int getReading(int port) {
		int reading = 0;
		
		reading = colorSensorList.get(port).getColorID();
		
		return reading;
	}
}
