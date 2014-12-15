package Move;

import lejos.robotics.RegulatedMotor;


public class Tachometer {
	private RegulatedMotor rightMotor;
	private RegulatedMotor leftMotor;
	
	public Tachometer(RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	public void resetTacho(){
		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();
	}
	
	public int getTachoMedia(){
		int left = leftMotor.getTachoCount();
		int right = rightMotor.getTachoCount();
		int media = (left+right)/2;
		return media;
	}
	
	public int getLeftTacho(){
		return leftMotor.getTachoCount();
	}
	
	public int getRightTacho(){
		return rightMotor.getTachoCount();
	}
}
