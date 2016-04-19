package robotEV3;

public class EV3SensorPair {
	private int sensorValue1;
	private int sensorValue2;
	
	public EV3SensorPair(int value1,int value2) {
		sensorValue1 = value1;
		sensorValue2 = value2;
	}
	
	public int getValue1() {
		return sensorValue1;
	}
	
	public int getValue2() {
		return sensorValue2;
	}
}
