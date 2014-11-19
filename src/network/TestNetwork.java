package network;

import network.client.Client;
import network.client.ClientImplUDP;
import network.server.Server;
import network.server.ServerImplUDP;
import util.Datatype;
import util.UDP_Packet;

public class TestNetwork {
	public static void main(String[] args){
		// create a new Server with the UDP Implementation
		Server server = new ServerImplUDP();
		// run the Server
		server.run();
		server.close();
		
		Client cl = new ClientImplUDP();
		UDP_Packet packet = new UDP_Packet("EV0", 0, Datatype.info,
				"Hello - this is some test information");
		
		cl.send(packet);
	}
}
