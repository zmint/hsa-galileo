/* This is an Interface for client-side networking stuff
 * 
 * If you need to send data to the server please use this Interface
 *   so you don't have to mess with sockets etc..
 *   
 *   An example on how to use this can be found in 
 *   src/network/client/TestClient.java
 * 
 **/

package network.client;

import util.UDP_Packet;

public interface Client {
	void send(String message);
	void send(UDP_Packet data);		// It's recommended to use this Method
	
	/** If such things are needed we can implement the functionality */
//	String receive();
//	void Control();
//		// unsure if we need those
//	Map getMap();				// get current state of map
//	boolean borderComplete();	// asks server if everything is done?
//								// or just tells the server, yeah i'm done!
//	Location getLoc(String robot);	// gives you the location of yourself or another robot
}
