/* This is an Interface for server-side  networking stuff
 * 
 * 
 *  An example on how to use this can be found in 
 *  src/network/server/TestServer.java
 **/

package network.server;


public interface Server {
	public void run();					// starts the server
	public void close();				// terminates the server, currently unimplemented
	public void send(int command);		// send an command to EV2
}
