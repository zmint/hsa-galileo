/*This class represents a Robot !
 * currently it just holds its name, ip and port
 * extend it if you'll need it!
 * 
 */

package util;

import network.NetworkSettings;

public enum Robot {
	EV1("EV1", NetworkSettings.getEv1Ip(), NetworkSettings.getEv1Port()),
	EV2("EV2", NetworkSettings.getEv2Ip(), NetworkSettings.getEv2Port()),
	EV3("EV3", NetworkSettings.getEv3Ip(), NetworkSettings.getEv3Port());
	
	private final String name;
	private final String ip;
	private final int port;

	private Robot(String name, String ip, int port) {
		NetworkSettings.readConfigFile(); // doesnt work :(
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	

}
