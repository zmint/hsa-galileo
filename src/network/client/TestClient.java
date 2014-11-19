/*Testing class for running the Client	-	This should run on a robot
 * This also shows you how to use the Client
 * 
 **/

package network.client;

import util.Datatype;
import util.UDP_Packet;


public class TestClient {
	public static void main(String[] args) {
		// Client cl = new ClientImpl();
		
		// create a new client with the UDP Implementation
		Client cl = new ClientImplUDP();

		// create a UDP packet with data
		UDP_Packet packet = new UDP_Packet("EV0", 0, Datatype.info,
				"Hello - this is some information packet");
		
		// send the packet
		cl.send(packet);
	}
}
