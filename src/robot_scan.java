import lejos.utility.Delay;

public class robot_scan {
	
	private boolean scanFinish = false;
	
	private int[][] field = new int[50][50];
	
	private robot_EV3 ev3;
	
	public robot_scan(robot_EV3 robot) {
		ev3 = robot;
		initializeField();
	}
	
	public void initializeField() {
		ev3.setAllMotorsAccel(500);
		ev3.setAllMotorsSpeed(500);
		Delay.msDelay(1000);
		ev3.moveForward(720, 1000);
		Delay.msDelay(1000);
		ev3.moveBackward(720, 1000);
		Delay.msDelay(1000);

	}
	
	public boolean isScanFinished() {
		return scanFinish;
	}
}
