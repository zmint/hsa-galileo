/*This is the part of the main program, which runs on a Laptop or something
 * 
 */

import util.CSVFile;
import network.server.Server;
import network.server.ServerImplUDP;


public class Main_Computer {
	public static void main(String[] args) {
		Server server = new ServerImplUDP();
		
		CSVFile.print();
		
		// run the Server
		server.run();
	}

}
