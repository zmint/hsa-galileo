/*This class implements the Interface network/server/Server.java with UDP
 * 
 */

package network.server;

import network.NetworkSettings;
import java.net.SocketException;


public class ServerImplUDP implements Server {
	
	// CONSTRUCTOR
	public ServerImplUDP(){
		NetworkSettings.readConfigFile();
	}

	@Override
	public void run() {
		try {
			System.out.println("Starting Server...");
			new ServerImplUDPThread().start();
		} catch (SocketException e) {
			System.err.println("Couldn't start Server\r\n  Message: "
					+ e.getMessage());
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
