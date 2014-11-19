package followWall;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class SensorThread extends Thread {
	
	private EV3TouchSensor touch1 = new EV3TouchSensor(SensorPort.S4);
	private boolean touchActivated1 = false;
	private EV3TouchSensor touch2 = new EV3TouchSensor(SensorPort.S1);
	private boolean touchActivated2 = false;
	
	EV3UltrasonicSensor sonar1 = new EV3UltrasonicSensor(SensorPort.S3);
	float[] sonarDistance1 = new float[1];
	EV3UltrasonicSensor sonar2 = new EV3UltrasonicSensor(SensorPort.S2);
	float[] sonarDistance2 = new float[1];
	
	
	
	public void run() {
		
		while (true) {
			sonar1.getDistanceMode().fetchSample(sonarDistance1, 0);
			sonar2.getDistanceMode().fetchSample(sonarDistance2, 0);
			
			float[] sample1 = new float[touch1.sampleSize()];
			touch1.fetchSample(sample1, 0);
			
			touchActivated1 = sample1[0] > 0;

			float[] sample2 = new float[touch2.sampleSize()];
			touch2.fetchSample(sample2, 0);
			
			touchActivated2 = sample2[0] > 0;
			
			System.out.println("Control: Distance1: " + sonarDistance1[0]+"  Distance2: " + sonarDistance2[0]+
					" touch "+touchActivated1);
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public float getSonarDistance1() {
		return sonarDistance1[0];
	}
	
	public float getSonarDistance2() {
		return sonarDistance2[0];
	}
	
	public boolean isTouched1() {
		return touchActivated1;
	}
	
	public boolean isTouched2() {
		return touchActivated2;
	}
	
	public void message(){
		System.out.println("Control: Distance1: " + sonarDistance1[0]+"  Distance2: " + sonarDistance2[0]+
					" touch "+touchActivated1);
	}
}
