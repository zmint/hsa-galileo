/*This is the part of the main program, which runs on a Laptop or something
 * 
 */

package main;

import network.server.Server;
import network.server.ServerImplUDP;
import mapping.Map;
import mappingHistory.*;
import pathfinding.*;


public class Main_Computer {
	// SETTINGS
	private static boolean fromHistory = true;
	public static boolean waitForAck = false;
	
	// INSTANCES
	private static MappingEV3 mapVisualization = null;
	private static Server server = new ServerImplUDP();
	private static Map map = new Map();
	private static RobotPath pathfinding = new RobotPath();
	
	
	public static void main(String[] args) {
		server.run();
		
		if (fromHistory){
			mapVisualization = new mappingHistory.MappingEV3();
		}
		
		mapVisualization.start();
		
		pathfinding.run(server, map);
	}
	
	public static Map getMap() {
		return map;
	}
	
	public static RobotPath getRobotPath() {
		return pathfinding;
	}

}
