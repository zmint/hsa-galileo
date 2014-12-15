/*This is part of the Server Implementation ServerImplUDP.java
 * 
 * 2DO:
 *   currently runs in an infinite loop, change that
 */

package network.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import network.NetworkSettings;
import util.CSVFile;
import util.Datatype;
import util.UDP_Packet;

import java.io.IOException;
import java.net.SocketException;


public class ServerImplUDPListener extends Thread {
	private static final int SERVER_PORT = NetworkSettings.getServerPort();
	private static final int BUFFER_SIZE = NetworkSettings.getBufferSize();

	protected DatagramSocket socket = null;

	// CONSTRUCTOR
	public ServerImplUDPListener() throws SocketException {
		this("UDPServer Listener");
	}

	private ServerImplUDPListener(String name) throws SocketException {
		super(name);
		try {
			socket = new DatagramSocket(SERVER_PORT);
		} catch (SocketException e) {
			throw new SocketException(this.getName() + "\r\n  Message: "
						+ e.getMessage() + ", Port: " + SERVER_PORT);
		}
	}

	// Server Thread loop
	/** Waits for UDP packets. When received they are written into a .csv file
	 * 										and forwarded to the converter */
	public void run() {
		System.out.println("###" + this.getName() + " running on port " + SERVER_PORT );
		while (true) {
			try {
				byte[] buf = new byte[BUFFER_SIZE];
				
				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				// trim data
				String data = new String(packet.getData());
				data = data.substring(0, packet.getLength());
				
				// debug msg
				System.out.println("package received: " + data);
				
				// if dataType == mofi
				if (data.substring(4, 8) == Datatype.mofi.toString())
					pathfinding.setWaitingForACK(false);

				// write content into .csv file
				CSVFile.write(data);

				// if we need to send data back
				// InetAddress address = packet.getAddress();
				// int port = packet.getPort();
				// packet = new DatagramPacket(buf, buf.length, address, port);
				// socket.send(packet);
			} catch (IOException e) {
				System.err
						.println("IOException while listening for UDP packets on port "
								+ SERVER_PORT
								+ "\r\n  Message: "
								+ e.getMessage());
			}

		} // end of while(true)
		// socket.close();
	}
}
