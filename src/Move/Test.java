package Move;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class Test {
	private static RegulatedMotor leftMotor = Motor.B;
	private static RegulatedMotor rightMotor = Motor.C;
	private static SensorThread sensorThread;
	
	public static void main(String[] args) {
		Tachometer tacho = new Tachometer(leftMotor, rightMotor);
		tacho.resetTacho();
		leftMotor.rotateTo(0);
		rightMotor.rotateTo(0);
		
		
		sensorThread = new SensorThread();
		sensorThread.setDaemon(true);
		sensorThread.start();
		
		//Movements mv = new Movements();

	}

}
