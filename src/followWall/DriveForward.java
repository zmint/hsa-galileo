package followWall;

import java.io.IOException;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.PilotProps;

class DriveForward implements Behavior {

	private boolean suppressed = false;
	private RegulatedMotor leftMotor;
	private RegulatedMotor rightMotor;
	private RobotController controller;
	
	public DriveForward(RobotController controller, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.controller = controller;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	public boolean takeControl() {
			/*if ((Button.waitForAnyPress() & Button.ID_ESCAPE) != 0)
	        {
	            Button.LEDPattern(0);
	            System.exit(1);
	        }*/
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		
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
			
    	robot.setAcceleration(2000);
		robot.setTravelSpeed(10); // cm/sec
		robot.setRotateSpeed(90); // deg/sec
		robot.forward();
		
		while (!suppressed) {
			Thread.yield();
		}

		controller.endAction();
	}
}

