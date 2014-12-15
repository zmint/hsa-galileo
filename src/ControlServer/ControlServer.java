
package ControlServer;

import java.io.BufferedReader;
import java.util.Scanner;

import lejos.utility.Delay;

import org.jfree.util.WaitingImageObserver;

import network.*;
import network.server.Server;
import network.server.ServerImplUDP;

public class ControlServer {
 
	public static void main(String[] args) {
		
		// create a new Server with the UDP Implementation
		Server server = new ServerImplUDP();
		// run the server 
		server.run();
		// now the server listens on the port specified in
		// the config file './src/network/host.properties'
		boolean in= true;
		Scanner sn = new Scanner(System.in);
		while (in){
			//AdminGUI window = new AdminGUI();
			//Delay.msDelay(2);
			//window.window.hide();
			//((Server) window).close();
			System.out.println("Select option:");
			System.out.println("Option1 Path finding");
			System.out.println("Option2 Map");
			System.out.println("Option3 More options");
			
			int i = sn.nextInt();
		
			switch (i){
			
            case 1: System.out.println("EV0 received ");// Start the method to who control the robot to find the route
                     PathFinding(server);
            break;
            case 2: System.out.println("EV01 received ");
                     Method2();//  Method to implement
            break;
            case 3: System.out.println("EV0 2received ");
                     Method3();//  Method to implement
            break;
            case 4: System.out.println("EV03 received ");
                     Method4();//  Method to implement
            break;
            case 5: System.out.println("EV04 received ");
                     Method5();//  Method to implement
            break;
            case 6: System.out.println("Bye-Bye!");// program Out
                  in = false;
            break;
			
			
		}
			
			
		}
		// currently the server is closed by just shutting it down...
		server.close();
		
	}

	
	
	 // in those methods we have to implement all the different behaviors of the robot, 
	/* to control the robot, we just have to use the method server.send(data); like this  :
	  '1'  move forward;
	  '2'  move backwards;
	  '3'  move left 90°
	  '4'  move right 90°
	  '5'  move left 45°
	  '6'  move right 45°
	  '7'  move left 10°
	  '8'  move right 10°
	  
	  we still have to implement other kind of movements, 
		
	*/	
		
		
    private static void Method5() {
		// TODO Auto-generated method stub
		
	}

	private static void Method3() {
		// TODO Auto-generated method stub
		
	}

	private static void Method4() {
		// TODO Auto-generated method stub
		
	}

	private static void Method2() {
		// TODO Auto-generated method stub
		
	}

	private static void PathFinding(Server server) {
		
		
		
	}

}
