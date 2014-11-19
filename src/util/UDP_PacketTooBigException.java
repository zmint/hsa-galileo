/*This Exception is intendet to inform ourselves that we need to increase the UDP package size.
 * 
 */

package util;

public class UDP_PacketTooBigException extends Exception {
	
	public UDP_PacketTooBigException(String message){
		super(message);
	}

}
