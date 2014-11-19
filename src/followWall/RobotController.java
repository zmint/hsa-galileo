package followWall;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RobotController {
	private static final int DEFAULT_MOTOR_SPEED = 300;
	private static final int DEFAULT_MOTOR_ACCELERATION = 800;
	
	private RegulatedMotor leftMotor = Motor.B;
	private RegulatedMotor rightMotor = Motor.C;
	private SensorThread sensorThread;
	
	public void start() {
		
		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();
		leftMotor.rotateTo(0);
		rightMotor.rotateTo(0);
		
		sensorThread = new SensorThread();
		sensorThread.setDaemon(true);
		sensorThread.start();
		
		ProgramControl control = new ProgramControl();
		
		Behavior[] behaviorList = {
			new DriveForward(this, leftMotor, rightMotor),
			new AvoidWall(this, sensorThread, leftMotor, rightMotor),
			new ParallelWall(this, sensorThread, leftMotor, rightMotor),
			//new CornerWall(this, sensorThread, leftMotor, rightMotor),
			new QuitBehavior(), control
		};
		
		Arbitrator arbitrator = new Arbitrator(behaviorList);
		
		LCD.drawString("Push to Start",0,1);
		Button.waitForAnyPress();
		
		control.startTimer();
		endAction();
		arbitrator.start();
	}
	
	public void endAction() {
		leftMotor.stop(true); 
		rightMotor.stop(true);
		leftMotor.setSpeed(DEFAULT_MOTOR_SPEED);
		rightMotor.setSpeed(DEFAULT_MOTOR_SPEED);
		leftMotor.setAcceleration(DEFAULT_MOTOR_ACCELERATION);
		rightMotor.setAcceleration(DEFAULT_MOTOR_ACCELERATION);
	}

}
