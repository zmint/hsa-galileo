/*This is the part of the main program, which runs on a robot
 * 
 */

package main;

import followWall.RobotController;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import network.client.Client;
import network.client.ClientImplUDP;
import util.Datatype;
import util.UDP_Packet;

public class Main_Robot {

	public static void main(String[] args) {
		sendSensorData();
		// moveRobot();
	}

	private static void moveRobot() {
		RobotController controller = new RobotController();
		controller.start();
	}
	
	private static void sendSensorData() {
		Client cl = new ClientImplUDP();

		// init robot
		Brick brick = BrickFinder.getDefault();
		// Port s1 = brick.getPort("S1");
		Port s2 = brick.getPort("S2");
		Port s3 = brick.getPort("S3");
		Port s4 = brick.getPort("S4");
		
		// Ultrasonic Sensor
		EV3UltrasonicSensor uss_sback = new EV3UltrasonicSensor(s2);
		SampleProvider sample_uss_sback = uss_sback.getMode("Distance");
		EV3UltrasonicSensor uss_sfront = new EV3UltrasonicSensor(s3);
		SampleProvider sample_uss_sfront = uss_sfront.getMode("Distance");
		EV3UltrasonicSensor uss_front = new EV3UltrasonicSensor(s4);
		SampleProvider sample_uss_front = uss_front.getMode("Distance");

		float uss_sfront_sample[] = new float[1];
		float uss_sback_sample[] = new float[1];
		float uss_front_sample[] = new float[1];

		Key escape = brick.getKey("Escape");

		while (!escape.isDown()) {
			sample_uss_sback.fetchSample(uss_sfront_sample, 0);
			sample_uss_sfront.fetchSample(uss_sback_sample, 0);
			sample_uss_front.fetchSample(uss_front_sample, 0);

			UDP_Packet packet = new UDP_Packet("EV2", Datatype.sens, 0, 
					"uss_sback: " + uss_sback_sample[0] + " uss_fback: "
							+ uss_sfront_sample[0] + " uss_front: " + uss_front_sample[0]);

			// send the packet
			cl.send(packet);
		}

	}
}
