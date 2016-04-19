package robotEV3;

public interface EV3RobotMovement {
	void shoot(int strength);
	void moveForward(int rotation,boolean immediateReturn);
	void moveBack(int rotation,boolean immediateReturn);
	void moveLeft(int rotation,boolean immediateReturn);
	void turnTwoLeft(int rotation,boolean immediateReturn);
	void moveRight(int rotation,boolean immediateReturn);
	void turnTwoRight(int rotation,boolean immediateReturn);
	void turnLeft(int rotation,boolean immediateReturn);
	void turnRight(int rotation,boolean immediateReturn);
	void setSpeedAndAcceleration(int speed, int acceleration);
	void stopMotors();
	
	int getReading(int port);
}
