package network.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import ClientControl.ClientThread;
import network.NetworkSettings;

public class ClientImplUDPListener extends Thread {
	// ??unsure??currently just works with the debug robot ev0...
	private static final int SERVER_PORT = NetworkSettings.getEv2Port();
	private static final int BUFFER_SIZE = NetworkSettings.getBufferSize();
	private int writeBufIndex = 0;

	protected DatagramSocket socket = null;

	// CONSTRUCTOR
	public ClientImplUDPListener() throws SocketException {
		this("UDPClient Listener");
	}

	private ClientImplUDPListener(String name) throws SocketException {
		super(name);
		try {
			socket = new DatagramSocket(SERVER_PORT);
		} catch (SocketException e) {
			throw new SocketException(this.getName() + "\r\n  Message: "
						+ e.getMessage() + ", Port: " + SERVER_PORT);
		}
	}
	
	public void run(){
		System.out.println("###" + this.getName() + " started. Running on port " + SERVER_PORT );
		while (true) {
			try {
				byte[] buf = new byte[BUFFER_SIZE];
				
				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				// trim data
				String data = new String(packet.getData());
				data = data.substring(0, packet.getLength());
				
				ClientImplUDP.hasNext = true;
				// save packets into Buffer
//				ClientControl.ControlClient cc = new ClientControl.ControlClient();
//				cc.run(cl);
				//System.out.println("package received " + data);
				
				ClientImplUDP.packetBuffer[writeBufIndex] = data;
				
				if ( writeBufIndex == (ClientImplUDP.packetBuffer.length -1))
					writeBufIndex = 0;
				else
					writeBufIndex++;
					
				//socket.close();
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
