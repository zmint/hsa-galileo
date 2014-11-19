package followWall;

import java.io.IOException;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.PilotProps;

public class ParallelWall implements Behavior {

	private boolean supressed;
	private SensorThread sensorThread;
	private RobotController controller;
	private RegulatedMotor leftMotor, rightMotor;
	
	private static final float MAX_DISTANCE = (float) 0.09;
	private static final float CONTROL_DISTANCE = (float) 0.14;
	private static final float ERROR = (float) 0.02;
	
	public ParallelWall(RobotController controller, SensorThread sensorThread, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.sensorThread = sensorThread;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	private boolean isWallDetected() {

		if ((sensorThread.getSonarDistance1() < MAX_DISTANCE && sensorThread.getSonarDistance2() < CONTROL_DISTANCE
				|| sensorThread.getSonarDistance2() < MAX_DISTANCE && sensorThread.getSonarDistance1() < CONTROL_DISTANCE) 
				&& Math.abs(sensorThread.getSonarDistance1()- sensorThread.getSonarDistance2())> ERROR) {
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
		
		PilotProps pp = new PilotProps();
    	try {
			pp.loadPersistentValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.0"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "18.0"));
    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
    	
    	DifferentialPilot robot = new DifferentialPilot(wheelDiameter,trackWidth,leftMotor,rightMotor,reverse);
			
		if(sensorThread.getSonarDistance1() < sensorThread.getSonarDistance2()){
			do{
				robot.rotate(5);
			}
			while(sensorThread.getSonarDistance2()-sensorThread.getSonarDistance1() > ERROR);
		}else if(sensorThread.getSonarDistance2() < sensorThread.getSonarDistance1()){
			do{
				robot.rotate(-5);
			}
			while(sensorThread.getSonarDistance2()-sensorThread.getSonarDistance1() > ERROR);
		}
		
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
