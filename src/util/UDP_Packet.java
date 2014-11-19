/*This class represents the structure of an UDP packet
 * 
 * INFO:
 *   if you add an attribute, which will be in a UDP packet
 *   add the name of it into the string[] ORDER
 *   
 */

package util;

import util.Datatype;

import java.sql.Timestamp;
import java.util.Date;
import network.NetworkSettings;


public class UDP_Packet {
	// holds the size of one packet
	private static final int PACKET_SIZE = NetworkSettings.getBufferSize();
	// has the order of one packet
	private static final String[] ORDER = new String[] {
		"robot", "sensor", "dataType", "timestamp",	"data"	};
	// content							// Individual size, listed below
	private String robot 	= "";		//  3
	private String sensor	= "";		//  1
	private String dataType = "";		//  4
	private String timestamp= "";		// 23
	private String data		= "";		//221	// need to check how much place we need
	  // space for comma separator		//  4
	
	// CONSTRUCTOR
	/*
	 * @PARAMS
	 */
	public UDP_Packet(String robot, int sensor, Datatype dataType, String data){
		Date date = new Date();
		this.timestamp = new Timestamp(date.getTime()).toString();
		
		this.robot		= robot;
		this.sensor		= sensor + "";   // converts sensor number to string
		this.dataType	= dataType.toString();
		this.data		= data;
	}
	
	/** returns content of the package
	 * The Exception is intended to inform us, that our UDP package size is to small
	 * 
	 * @throws UDP_PacketTooBigException */
	public String getContent() throws UDP_PacketTooBigException{ 
		String content = robot + "," + sensor + "," + dataType + "," + timestamp + "," + data;
		if (content.length() > PACKET_SIZE)
			throw new UDP_PacketTooBigException(
					"We need to increase the UDP package size. Please report this to Patrick");
		return content;
	}
	
	/** returns content of the package
	 * The Exception is intended to inform us, that our UDP package size is to small
	 * 
	 * @throws UDP_PacketTooBigException */
	public String toString(){
		String content = robot + "," + sensor + "," + dataType + "," + timestamp + "," +  data;
		if (content.length() > PACKET_SIZE)
			System.err.println("We need to increase the UDP package size");
		return content;
	}
	
	// GETTER
	public static int getSize(){
		return PACKET_SIZE;
	}
	
	public static String[] getOrder(){
		return ORDER;
	}

}
