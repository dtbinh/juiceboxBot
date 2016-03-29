import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class robot_sensor {
	
	private final static int MAX_SENSOR_PORTS = 4;
	
	public static EV3ColorSensor[] initializeSensor() {
		EV3ColorSensor[] sensors = new EV3ColorSensor[1];
		
		sensors[0] = new EV3ColorSensor(SensorPort.S2);
		
		return sensors;
	}
	
	public static int getReading(EV3ColorSensor[] sensors) {
		return sensors[0].getColorID();
	}
	
	public static void closeAllSensor(EV3ColorSensor[] sensors) {
		for (EV3ColorSensor sensor : sensors) {
			sensor.close();
		}
	}
}
