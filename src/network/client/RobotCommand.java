/*This class contains different commands send to the robot
 * 
 */
package network.client;

public enum RobotCommand {
	SP,	// Start Pathfinding
	DF,	//	Drive Forward
	DB,	//	Drive Backwards
	BR, //	Break
	EB	//	Emergency Break
}

/*Description of the abbreviations, and explanation of their purpose
 * ****************************************************************************
 * SP		=	Start Pathfinding		: if we keep the robot as stupid as possible, like bodisco said,
 * 										: i think this will just be moving forward until the robot finds a wall
 * 										: after that, the computer directs him through the playground, with 
 * 										: the information from the map progress
 * DF		=	Drive Forward			:
 * DB		=	Drive Backwards			:
 * BR		=	Break					: stop the robot from moving 
 * 
 * EB		=	Emergency break			: let the robot stop, no matter what. use only in case of emergency,
 * 										: may be implemented as button on the gui
 * 
 * */