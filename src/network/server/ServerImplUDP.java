/*This class implements the Interface network/server/Server.java with UDP
 * 
 */

package network.server;

import network.NetworkSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ServerImplUDP implements Server {
	private static final int BUFFER_SIZE = NetworkSettings.getBufferSize();
	private static final String EV2_IP = NetworkSettings.getEv2Ip();
	private static final int EV2_PORT = NetworkSettings.getEv2Port();
	
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
		
//		try {
//			new ServerImplUDPController().start();
//		} catch (SocketException e) {
//			System.err.println("Couldn't start "
//					+ e.getMessage());
//		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(int command) {
		send(EV2_IP, EV2_PORT, command);
	}
	
	private void send(String robotIP, int robotPORT, int command) {
		try {
				// get a datagram socket
			DatagramSocket socket = new DatagramSocket();

				// fill package
			byte[] buf = new byte[BUFFER_SIZE];
			
			buf = (command + "").getBytes();
			
				// get address and create packet
			InetAddress address = InetAddress.getByName(robotIP);
			DatagramPacket packet = new DatagramPacket(buf, buf.length,
					address, robotPORT);
				// send packet
			socket.send(packet);
			
			// debug message
			System.out.println("package send to " + robotIP + ":" + robotPORT);

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Unknown host " + robotIP
					+ "\r\n  Message: " + e.getMessage());
			System.err.println("Maybe you need to configure the netconfigs"
					+ " in the file res/net.properties ?");
		} catch (SocketException e) {
			System.err.println("Couldn't create Socket\r\n  Message: "
					+ e.getMessage());
		} catch (IOException e) {
			System.err
				.println("Problem sending package, resend?\r\n  Message: "
						+ e.getMessage());
		}
	}
	
}
