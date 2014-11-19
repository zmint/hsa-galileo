package followWall;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

class ProgramControl implements Behavior {
	
	long startTime = -1;
	
	@Override
	public boolean takeControl() {
		
		if (Button.readButtons() > 0) {      
			return true;
		}
		
		if(startTime != -1 && System.currentTimeMillis() - startTime > MyMain.MAX_PROGRAM_TIME) {
			return true;
		}
		
		return false;
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public void action() {
		System.exit(1);
	}

	@Override
	public void suppress() {}
	
}