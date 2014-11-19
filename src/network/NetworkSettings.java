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

public final class NetworkSettings {
	public static final String propertiesFile = "./res/host.properties";
	private static int BUFFER_SIZE = 256; // default values
	private static String SERVER_IP = "127.0.0.1"; // if reading from
	private static int SERVER_PORT = 10000; // file wont work
	private static int CLIENT_PORT = 9999;

	private NetworkSettings() {
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

			String clientport = properties.getProperty("clientport");
			NetworkSettings.CLIENT_PORT = Integer.parseInt(clientport);
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

	public static int getClientPort() {
		return CLIENT_PORT;
	}

}
