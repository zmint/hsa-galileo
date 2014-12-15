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
	private static int readBufIndex = 0;
	public static String[] packetBuffer = new String[256];
	private boolean sensorSenderRunning = false;
	private Thread sensorSender = null;
	
	public ClientImplUDP(){
		NetworkSettings.readConfigFile();
		runListener();
	}

	private void send(String data) {
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
			
			// debug message
			System.out.println("package send to " + SERVER_IP + ":" + SERVER_PORT);

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
	
	@Override
	public String receive() {
		String packet = packetBuffer[readBufIndex];
		
		if (readBufIndex == (packetBuffer.length -1))
			readBufIndex = 0;
		else
			readBufIndex++;
		
		return packet;
	}
	
	public void startSending() {
		System.out.println("Start sending sensor data");
		
		if (sensorSender == null)
			sensorSender = new SensorSendingThread();
		
		sensorSender.start();
		sensorSenderRunning = true;
	}
	
	@SuppressWarnings("deprecation")
	public void stopSending() {
		System.out.println("Stop sending sensor data");
		
		sensorSender.stop();
		sensorSenderRunning = false;
	}
	
	private void runListener(){
		System.out.println("Starting Listener on client...");
		try {
			new ClientImplUDPListener().start();
		} catch (SocketException e) {
			System.err.println("Couldn't start "
					+ e.getMessage());
		}
	}
	
	public boolean isSensorSenderRunning(){
		return sensorSenderRunning;
	}

}
