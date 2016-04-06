
public class robot_scan {
	
	private boolean scanFinish = false;
	
	private int[][] field = new int[50][50];
	
	private robot_EV3 ev3;
	
	public robot_scan(robot_EV3 robot) {
		ev3 = robot;
		initializeField();
	}
	
	public void initializeField() {
		
		while(!ev3.isEscDown()) {
			System.out.println(ev3.getReading());
		}
		
//		ev3.setAllMotorsAccel(50);
//		ev3.setAllMotorsSpeed(50);
//		Delay.msDelay(1000);
//		ev3.moveForward(1080, 5000);
//		Delay.msDelay(1000);
//		ev3.moveBackward(1080, 5000);
//		Delay.msDelay(1000);

	}
	
	public boolean isScanFinished() {
		return scanFinish;
	}
}
