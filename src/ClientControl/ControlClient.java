package ClientControl;

import Move.Movements;
import network.client.Client;
import network.client.ClientImplUDP;
import util.Datatype;
import util.UDP_Packet;

public class ControlClient {
	public static void main(String[] args) {
		//String info;
		String imput = null;
		Client cl = new ClientImplUDP();
		Movements movements = new Movements();
		// create a UDP packet with data
		int i =5;
		while ( i != 0){
			      //0 to stop the client the value should be 
			      //0 to stop the current process, while it's running 
			imput = cl.receive();      
			i= Integer.parseInt(imput);
			      // to drive forward the value of the entry should be between 101 and 200 
			      // 101: drive 1 cm forward;.......;200: drive 100 cm forward
	           if ( (101<=i)&&(i<=200) ) {
	            	movements.driveForward(i-100,cl);
	              // to drive backward the value of the entry should be between 201 and 300 
				  // 201: drive 1 cm backward;.......;300: drive 100 cm backward
	           } else if ( (201<=i)&&(i<=300) ) {
	                movements.driveBackward(i-200,cl);
	              // to turn left the value of the entry should be between 301 and 500 
	              // 301 turn 1 degree left;.......;500: turn 200 degrees left 
	           }else if ( (301<=i)&&(i<=500) ) {
                    movements.turnLeft(i-300 , cl);
                 // to turn right the value of the entry should be between 501 and 700 
  	              // 501 turn 1 degree right;.......;700: turn 200 degrees right 
               }else if ( (501<=i)&&(i<=700) ) {
                    movements.turnRight(i-500 , cl);
               }  
			
		}
		// send the packet
		
	}

	
	
}
