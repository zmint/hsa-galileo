/*This is the part of the main program, which runs on a Laptop or something
 * 
 */

package main;

import network.server.Server;
import network.server.ServerImplUDP;
import mapping.Map;
import mappingHistory.*;


public class Main_Computer {
	private static boolean fromHistory = true;
	private static MappingEV3 mapVisualization = null;
	private static Map map = null;
	
	public static void main(String[] args) {
		Server server = new ServerImplUDP();
		
		map = new Map();
		
		if (fromHistory){
			mapVisualization = new MappingEV3();
		}
		
		mapVisualization.start();
		
		// run the Server
		server.run();
	}
	
	public static Map getMap() {
		return map;
	}

}
