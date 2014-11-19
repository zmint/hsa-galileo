/*This is part of the Server Implementation ServerImplUDP.java
 * 
 * 2DO:
 *   currently runs in an infinite loop, change that
 */

package network.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import network.NetworkSettings;
import util.CSVFileWriter;
import java.io.IOException;
import java.net.SocketException;


public class ServerImplUDPThread extends Thread {
	private static final int SERVER_PORT = NetworkSettings.getServerPort();
	private static final int BUFFER_SIZE = NetworkSettings.getBufferSize();

	protected DatagramSocket socket = null;

	// CONSTRUCTOR
	public ServerImplUDPThread() throws SocketException {
		this("GalileoUDPServerThread");
	}

	public ServerImplUDPThread(String name) throws SocketException {
		super(name);
		try {
			socket = new DatagramSocket(SERVER_PORT);
		} catch (SocketException e) {
			throw new SocketException(e.getMessage() + " ,Port: " + SERVER_PORT);
		}
	}

	// Server Thread loop
	/** Waits for UDP packets. When received they are written into a .csv file */
	public void run() {
		System.out.println("Server started. Running on port " + SERVER_PORT );
		while (true) {
			try {
				byte[] buf = new byte[BUFFER_SIZE];
				
				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				// trim data
				String data = new String(packet.getData());
				data = data.substring(0, packet.getLength());

				// write content into .csv file
				CSVFileWriter.write(data);

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
