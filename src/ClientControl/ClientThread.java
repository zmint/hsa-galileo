package ClientControl;

import network.client.Client;
import network.client.ClientImplUDP;

public class ClientThread extends Thread{
	private int rv;
	private boolean control = true;
	
	public void run() {
		// create a new client with the UDP Implementation
		Client cl = new ClientImplUDP();
		
		while (control) {
			
			rv = Integer.parseInt(cl.receive());
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public int getReceive(){
		return rv;
	}
	
	public void stopThread(){
		control = false;
	}
}
