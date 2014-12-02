package followWall;

public class RobotControlImpl implements RobotControl {

	public static final long MAX_PROGRAM_TIME = 80000;
	
	public void start (){
		RobotController controller = new RobotController();

		controller.start();
	}
}
