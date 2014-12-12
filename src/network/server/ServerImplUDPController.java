package network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import network.NetworkSettings;
import network.client.RobotCommand;

public class ServerImplUDPController extends Thread {
	private static final int BUFFER_SIZE = NetworkSettings.getBufferSize();
	private static final String EV1_IP = NetworkSettings.getEv1Ip();
	private static final int EV1_PORT = NetworkSettings.getEv1Port();
	private static final String EV2_IP = NetworkSettings.getEv2Ip();
	private static final int EV2_PORT = NetworkSettings.getEv2Port();
	private static final String EV3_IP = NetworkSettings.getEv3Ip();
	private static final int EV3_PORT = NetworkSettings.getEv3Port();
	private static final String EV0_IP = NetworkSettings.getEv0Ip();
	private static final int EV0_PORT = NetworkSettings.getEv0Port();

	protected DatagramSocket socket = null;

	// CONSTRUCTOR
	public ServerImplUDPController() throws SocketException {
		this("UDPServer Robot Controller");
	}

	private ServerImplUDPController(String name) throws SocketException {
		super(name);
	}

	// Server Thread loop
	/**
	 * Waits for input from GUI. When there's command in gui is clicked
	 *   it should send it to the specified client (robot)
	 */
	public void run() {
		System.out.println("###" + this.getName() + " started.");
		
			// I THINK HERE'S A GOOD PLACE TO CONNECT 
			// THE GUI COMMANDS WITH METHODS HERE
			
			// let gui specify which robot it should use
			//
			// then sth like this:
			//   send(EV1_IP, EV1_PORT, "command");
			// of course we have to implement, that the client
			// knows these commands, and then executes stuff
		
		// delay the send, to make sure the clientListener runs
		// in normal use we have to click in the gui and don't need this
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println("Error while forcing thread to sleep.. in ServerImplUDPController");
		}
		//testing packet
		//send(EV1_IP, EV1_PORT, RobotCommand.ROUTINE_1);
	}
	
	private void send(String robotIP, int robotPORT, RobotCommand command) {
		try {
				// get a datagram socket
			DatagramSocket socket = new DatagramSocket();

				// fill package
			byte[] buf = new byte[BUFFER_SIZE];
			buf = command.toString().getBytes();
			
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
