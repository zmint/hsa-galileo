/*This class holds the Network Settings
 *  
 *DESCRIPTION:
 *  It reads the config file located at 'propertiesFile',
 *  writes them into its Attributes.
 *  Other classes can acces the values through Getter methods
 * 
 */

package network;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;

import util.Robot;

public final class NetworkSettings {
	public static final String propertiesFile = "./res/net.properties";
	private static int BUFFER_SIZE = 256;			// default values
	private static String SERVER_IP = "127.0.0.1";	// if reading from
	private static int SERVER_PORT = 10000; 		// file wont work
	
	private static String EV1_IP	= "141.82.48.202"; //wrong! need to lookup the ips
	private static int EV1_PORT		= 10001;
	private static String EV2_IP	= "141.82.48.202"; //wrong! need to lookup the ips
	private static int EV2_PORT		= 10002;
	private static String EV3_IP	= "141.82.48.202"; //wrong! need to lookup the ips
	private static int EV3_PORT		= 10003;
	
	private static String EV0_IP	= "127.0.0.1";
	private static int EV0_PORT		= 9999;

	private NetworkSettings() {
		readConfigFile();
	}

	// READ CONFIG FILE
	public static void readConfigFile() {
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream(propertiesFile));
			properties.load(stream);
			stream.close();

			// SET VALUES
			String buffersize = properties.getProperty("buffersize");
			NetworkSettings.BUFFER_SIZE = Integer.parseInt(buffersize);

			String serverip = properties.getProperty("serverip");
			NetworkSettings.SERVER_IP = serverip;

			String serverport = properties.getProperty("serverport");
			NetworkSettings.SERVER_PORT = Integer.parseInt(serverport);

			// EV1
			String ev1ip = properties.getProperty("ev1ip");
			NetworkSettings.EV1_IP = ev1ip;
			String ev1port = properties.getProperty("ev1port");
			NetworkSettings.EV1_PORT = Integer.parseInt(ev1port);
			
			// EV2
			String ev2ip = properties.getProperty("ev2ip");
			NetworkSettings.EV2_IP = ev2ip;
			String ev2port = properties.getProperty("ev2port");
			NetworkSettings.EV2_PORT = Integer.parseInt(ev2port);
			
			// EV3
			String ev3ip = properties.getProperty("ev3ip");
			NetworkSettings.EV3_IP = ev3ip;
			String ev3port = properties.getProperty("ev3port");
			NetworkSettings.EV3_PORT = Integer.parseInt(ev3port);
			
			// EV0 - debug robot
			NetworkSettings.EV0_IP = properties.getProperty("ev0ip");
			NetworkSettings.EV0_PORT = Integer.parseInt(properties.getProperty("ev0port"));
			
			
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't read properties file located at '"
					+ propertiesFile + "'");
			System.err
					.println("  Using default values instead! You can change them in the,\r\n"
						   + "  file network/NetworkSettings.. but that's not recommended");
		} catch (IOException e) {
			System.err.println("IOException while reading properties file '"
					+ propertiesFile + "'\r\n  Message: " + e.getMessage());
		}

	}

	// GETTER
	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public static String getServerIp() {
		return SERVER_IP;
	}

	public static int getServerPort() {
		return SERVER_PORT;
	}

	// ROBOTS
	public static String getEv1Ip() {
		return EV1_IP;
	}
	public static int getEv1Port() {
		return EV1_PORT;
	}
	
	public static String getEv2Ip() {
		return EV2_IP;
	}
	public static int getEv2Port() {
		return EV2_PORT;
	}
	
	public static String getEv3Ip() {
		return EV3_IP;
	}
	public static int getEv3Port() {
		return EV3_PORT;
	}
	
	public static String getEv0Ip() {
		return EV0_IP;
	}
	public static int getEv0Port() {
		return EV0_PORT;
	}

}