/* This is an Interface for server-side  networking stuff
 * 
 * 
 *  An example on how to use this can be found in 
 *  src/network/server/TestServer.java
 **/

package network.server;


public interface Server {
	public void run();					// starts the server
	public void close();				// terminates the server
	
	/** If such things are needed we can implement the functionality */
//	abstract Map getMap();					// gets Map from mapper
//	abstract void sendMap(Map map);			// sends Map if client asks for it
//	abstract void sendLoc(Location robot);		// sends Location of robot
//	public void isObstacle();				// sends robot, if found object is obstacle or not
}
