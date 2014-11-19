package followWall;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CornerWall implements Behavior {

	private boolean supressed;
	private SensorThread sensorThread;
	private RobotController controller;
	private RegulatedMotor leftMotor, rightMotor;
	
	private static final float MAX_DISTANCE = (float) 0.12;
	private static final float CONTROL_DISTANCE = (float) 0.5;
	
	public CornerWall(RobotController controller, SensorThread sensorThread, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.sensorThread = sensorThread;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	private boolean isWallDetected() {

		if (sensorThread.getSonarDistance1() > MAX_DISTANCE && sensorThread.getSonarDistance2() < CONTROL_DISTANCE) {
			return true;
		}
		
		else {
			return false;
		}
	}

	public boolean takeControl() {
		return isWallDetected();
	}

	public void suppress() {
		supressed = true;
	}

	public void action() {
		
		//try to follow near the wall
			
		leftMotor.setSpeed(100);
		rightMotor.setSpeed(100);
		leftMotor.forward();
		rightMotor.forward();
		Delay.msDelay(3000);
		leftMotor.rotate(180, true);
		rightMotor.rotate(-180);
		
		while(rightMotor.isMoving() || leftMotor.isMoving()) {
			if(supressed) {
				controller.endAction();
				return;
			}
			
			Thread.yield();
		}
		
		controller.endAction();
	}

}
