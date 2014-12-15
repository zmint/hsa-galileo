package pathfinding;

import java.util.ArrayList;
import java.util.Vector;

import util.CSVFile;
import mapping.Map;
import network.client.RobotCommand;
import network.server.Server;

public class RobotPath { // This class implements the general behaviour
							// (pathfinding) of our robots
	
	private RobotLocation myLocation;

	private Server myServer;

	private final float PARALLELTOWALLERROR = 0.02f;

	private ArrayList<RobotCommand> commandBuffer;

	private boolean hasMapSize;

	private boolean waitingForACK;

	private boolean parallelToWall;

	public boolean isParallelToWall() {
		return parallelToWall;
	}

	public void setParallelToWall(boolean parallelToWall) {
		this.parallelToWall = parallelToWall;
	}

	public boolean updateParallelToWall() {
		if (this.currentSensorData().get(0) - this.currentSensorData().get(1) < PARALLELTOWALLERROR) {
			this.parallelToWall = true;
			return parallelToWall;
		}
		this.parallelToWall = false;
		return parallelToWall;
	}

	public boolean isWaitingForACK() {
		return waitingForACK;
	}

	public void setWaitingForACK(boolean waitingForACK) {
		this.waitingForACK = waitingForACK;
	}

	public RobotPath() { // Constructor
		this.commandBuffer = new ArrayList<RobotCommand>();
		this.hasMapSize = false;
	}

	public void run(Server s, Map m) {

		this.myServer = s;
		this.waitingForACK = false;

		
		driveTillContact(); // now i should be at an obstacle
		turnToParallel(); // now i should be parallel to an obstacle
		driveTillContact(); // now i should be at a corner
		// now i am at a corner and ready to relocate myself
		
		// initialize robot location
		
		myLocation = new RobotLocation(m);
		this.hasMapSize = false;
		
		while(this.hasMapSize==false) {
			double angle = this.turnToParallel();
			double length = this.driveAndCountTacho();
			myLocation.move(angle, length);
			if(this.myLocation.getRelativeToStart().x == 0 && this.myLocation.getRelativeToStart().y == 0) {//TODO: Insert value error here
				break;
			}
			
		}
		

		ArrayList<Float> fromSensor = this.currentSensorData();
		// commandBuffer.add(RobotCommand.TTW);
		// commandBuffer.add(RobotCommand.DF);
		// commandBuffer.add(RobotCommand.TTW);

		while (this.hasMapSize = false) {
			commandBuffer.add(RobotCommand.DF);

		}

		// now we are at the first edge. now get the shape of the room

		while (this.hasMapSize == false) {

			// this is the part where the robots drive around the wall and
			// get the size
			// of the room

			commandBuffer.add(RobotCommand.DF);

		}

		// here the robots examine the inner part of the room, also
		// obstacles
	}

	public double turnToParallel() { // repeatately sends turn to the robot till ti is parallel to an obstacle
		
		double angleThatIHaveTurned = 0.0d;
		
		myServer.send(515); // turn 15 degrees at first
			while (this.waitingForACK == true) {
				;// waiting for ACK
			}
		angleThatIHaveTurned += 15;
			
		while (true) {
			myServer.send(502); // turn 2 degrees again and again till i am parallel
				while(this.waitingForACK==true){
					;// waiting for ACK
				}
			angleThatIHaveTurned += 2;
			
			if (this.updateParallelToWall()==true) {
				break;
			}
		}
		return angleThatIHaveTurned;
	}

	public void driveTillContact() { // this function lets the robot drive 1 cm
										// forward until it's less than 3 cm
										// from the wall
		while (true) {
			myServer.send(101); // drive 1 cm forward
			while (this.waitingForACK == true) {
				;// waiting for ACK
			}
			if (this.currentSensorData().get(2) < 0.03) { // am i near to an
															// obstacle?
				break;
			}
		}
	}
	
	public double driveAndCountTacho() {
		double drivenDistance = 0.0d;
		while(true) {
			myServer.send(101);
			while(this.waitingForACK == true) {
				; // waiting for ACK
			}
			drivenDistance += 0.01;
			if(this.currentSensorData().get(2) < 0.03) {
				break;
			}
		}
		return drivenDistance;
		
		
		
	}

	// Methods for access to sensor data - dummy implementation

	public ArrayList<String[]> getHistory() {
		return CSVFile.readComplete();

	}

	public ArrayList<Float> currentSensorData() {

		// gets the sensor data out of the history file

		int historySize = getHistory().size();
		System.out.println("History size: " + historySize);
		String[] packageData = getHistory().get(historySize - 5);
		int packageDataSize = packageData.length;
		String sensorData = packageData[packageDataSize - 1];

		// now we have to parse and cast from string to float
		System.out.println(packageData[packageDataSize - 1]);
		String[] data = sensorData.split(" ");
		ArrayList<Float> convertedData = new ArrayList<Float>();
		for (int i = 1; i < data.length; i += 2) {
			System.out.println(data[i]);
			convertedData.add(Float.parseFloat(data[i])); // works not
		}

		return convertedData;

	}

	// Getters and setters

	public ArrayList<RobotCommand> getCommandBuffer() {
		return commandBuffer;
	}

	public void setCommandBuffer(ArrayList<RobotCommand> commandBuffer) {
		this.commandBuffer = commandBuffer;
	}

	public boolean getHasMapSize() {
		return hasMapSize;
	}

	public void setHasMapSize(boolean hasMapSize) {
		this.hasMapSize = hasMapSize;
	}

}
