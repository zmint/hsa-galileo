/*Those are Datatypes for the UDP packets, send from clients to the server
 * 
 * PURPOSE:
 * This is included in the UDP packet, so the computer knows what kind of data he receives!
 * 
 * INFO:
 * To keep the packages small, i'd recommend to keep the length of the names to a maximum
 *   of 4 bytes. 
 *   Description of the abbreviation below.
 * 
 */

package util;

public enum Datatype {
	sens, info, warn, erro;
}

/*Description of the abbreviations, and explanation of their purpose
 * ****************************************************************************
 * sens		=	sensor					: this is used to send sensor data
 * info		=	info					: inform the server about something
 * 
 *** not sure if we need the ones below 
 * warn		=	warning					: inform the server about a warning
 * err		=	error					: inform the server about an error
 * 
 * */
