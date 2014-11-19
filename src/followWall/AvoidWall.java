package followWall;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

class AvoidWall implements Behavior {

	private boolean supressed;
	private SensorThread sensorThread;
	private RobotController controller;
	private RegulatedMotor leftMotor, rightMotor;
	
	public AvoidWall(RobotController controller, SensorThread sensorThread, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.sensorThread = sensorThread;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	private boolean isWallDetected() {

		if (sensorThread.isTouched1() || sensorThread.isTouched2()) {
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
		
		//turn right
		leftMotor.setSpeed(100);
		rightMotor.setSpeed(100);
		leftMotor.backward();
		rightMotor.backward();
		Delay.msDelay(1000);
		leftMotor.rotate(-170, true);
		rightMotor.rotate(170);
		
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
