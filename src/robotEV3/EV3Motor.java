package robotEV3;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class EV3Motor {
	
	private EV3LargeRegulatedMotor motor;
	private String tag;
	private int currentRotation = 0;
	
	public EV3Motor(Port port, String label) {
		motor = new EV3LargeRegulatedMotor(port);
		tag = label;
	}
	
	public String getTag() {
		return tag;
	}
	
	public int getCurrentRotation() {
		return currentRotation;
	}
	
	public int getSpeed() {
		return motor.getSpeed();
	}
	
	public int getAcceleration() {
		return motor.getAcceleration();
	}
	
	public void setSpeed_setAcceleration(int speed, int acceleration) {
		motor.setSpeed(speed);
		motor.setAcceleration(acceleration);
	}
	
	public void rotate(int rotation, boolean immediateReturn) {
		currentRotation += rotation;
		motor.rotateTo(currentRotation,immediateReturn);
	}
	
	public void close() {
		motor.close();
	}
	
	public void stop() {
		motor.stop(true);
	}
}
