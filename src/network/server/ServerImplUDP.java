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
		System.out.println("Starting Server...");
		try {
			new ServerImplUDPListener().start();
		} catch (SocketException e) {
			System.err.println("Couldn't start "
					+ e.getMessage());
		}
		
		try {
			new ServerImplUDPController().start();
		} catch (SocketException e) {
			System.err.println("Couldn't start "
					+ e.getMessage());
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
	
}
