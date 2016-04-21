package robotEV3;

public class EV3SensorPair {
	
	private String command;
	private int rotationAmount;
	
	public EV3SensorPair(String action, int rotation) {
		command = action;
		rotationAmount = rotation;
	}
	
	public int getRotation() {
		return rotationAmount;
	}
	
	public String getCommand() {
		return command;
	}
}
