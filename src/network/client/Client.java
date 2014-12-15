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
	void send(UDP_Packet data);
	void sendACK();
	
	boolean hasNextPacket();
	String receive();
	void startSending();
//	void stopSending();
	
	boolean isSensorSenderRunning();
}
