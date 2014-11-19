/*This class implements the Client functionality with UDP packets
 * 
 */

package network.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import network.NetworkSettings;
import util.UDP_Packet;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ClientImplUDP implements Client {
	private static final String SERVER_IP	= NetworkSettings.getServerIp();
	private static final int	SERVER_PORT	= NetworkSettings.getServerPort();
	private static final int	BUFFER_SIZE = NetworkSettings.getBufferSize();
	
	public ClientImplUDP(){
		NetworkSettings.readConfigFile();
	}

	@Override
	public void send(String data) {
		try {
				// get a datagram socket
			DatagramSocket socket = new DatagramSocket();
				// send request
			byte[] buf = new byte[BUFFER_SIZE];
				// fill package
			buf = data.getBytes();
				// get address and create packet
			InetAddress address = InetAddress.getByName(SERVER_IP);
			DatagramPacket packet = new DatagramPacket(buf, buf.length,
					address, SERVER_PORT);
				// send packet
			socket.send(packet);
			System.out.println("package send");

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Unknown host " + SERVER_IP + "\r\n  Message: "
					+ e.getMessage());
		} catch (SocketException e) {
			System.err.println("Couldn't create Socket\r\n  Message: "
					+ e.getMessage());
		} catch (IOException e) {
			System.err
					.println("Problem sending package, resend?\r\n  Message: "
							+ e.getMessage());
		}
	}

	@Override
	public void send(UDP_Packet data) {
		send(data.toString());
	}

}
