/*This is an example on how to use the Server
 * it should run on a computer, not on a robot
 * 
 */

package network.server;

public class TestServer {
	public static void main(String[] args) {
		// Server server = new ServerImpl(3143);

		// create a new Server with the UDP Implementation
		Server server = new ServerImplUDP();
		// run the Server
		server.run();
		
		// now the server listens on the port specified in
		// the config file './src/network/host.properties'
		
		// currently the server is closed by just shutting it down...
		server.close();
	}
}