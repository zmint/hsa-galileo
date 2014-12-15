/*This is the part of the main program, which runs on a robot
 * 
 */

package main;

import network.client.Client;
import network.client.ClientImplUDP;

public class Main_Robot {
	public static Client cl = null;

	public static void main(String[] args) {
		cl = new ClientImplUDP();
		cl.startSending();
		ClientControl.ControlClient cc = new ClientControl.ControlClient();
		cc.run(cl);
	}
}
