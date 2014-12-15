package Move;


import java.nio.ByteBuffer;

import org.apache.commons.lang3.ArrayUtils;

import ClientControl.ClientThread;

import network.client.Client;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class Movements{
	private SensorThread sensorThread;
	private ClientThread clThread;
	private RegulatedMotor leftMotor = Motor.B;
	private RegulatedMotor rightMotor = Motor.C;
	private Tachometer tacho = new Tachometer(leftMotor, rightMotor);
	
	public Movements() {}
	
	//different bytes of control ( first byte of the array):
	//0 -> while driving forward information about lateral sensors(ultrasonic), and front sensor (infrared) 
	//1 -> while driving backward information about lateral sensors(ultrasonic), and front sensor (infrared)
	//2 -> while turning left information about lateral sensors, and front sensor (infrared)
	//3 -> while turning right information about lateral sensors, and front sensor (infrared)
	//4 -> distance at the end of the function end of function anyway 
	//5 -> touch sensor (DANGEROUS!!!!) forced end of the function.
	
	public void driveForward(int distance, Client cl) {//in cm
		// moves 36 degree = 0.01 meters aprox.
		
		//TODO check that the thread is working right and the pack are right
		//TODO for the Array of byte concatenation used commons-lang3-3.3.2.jar
		// send pack type Byte[]
		// while control if is possible continue forward
		// send info about of the sensors each 0.5cms
		tacho.resetTacho();
		byte[] pack;
		for(int i=0; i< distance*2; i++){
			// Do not touch and not give order to stop 
			if(!sensorThread.isTouched() && clThread.getReceive() != 0){
				// send pack 0 bytes
				byte[] control = ByteBuffer.allocate(2).putInt(0).array();
				byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
				byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
				byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
				
				pack = ArrayUtils.addAll(control, us1);
				pack = ArrayUtils.addAll(pack, us2);
				pack = ArrayUtils.addAll(pack, ir);
				cl.sendArray(pack);
				// movement
				this.leftMotor.rotate(18, true);
				this.rightMotor.rotate(18, true);
				
			// Is touching	
			}else if (sensorThread.isTouched()){
				// pack 5
				byte[] pack5 = {5};
				cl.sendArray(pack5);
				break;
				
			// Have order to stop	
			}else if(clThread.getReceive() == 0) {
				break;
			}
		}
		// send pack 0 and 4 distance
		// pack 0
		byte[] control = ByteBuffer.allocate(2).putInt(0).array();
		byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
		byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
		byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
		
		pack = ArrayUtils.addAll(control, us1);
		pack = ArrayUtils.addAll(pack, us2);
		pack = ArrayUtils.addAll(pack, ir);
		cl.sendArray(pack);
		
		// pack 4
		byte[] pack4 = ArrayUtils.addAll(ByteBuffer.allocate(2).putInt(0).array(),
				ByteBuffer.allocate(2).putInt(tacho.getTachoMedia()).array());
		cl.sendArray(pack4);
	}
	
	public void driveBackward(int distance, Client cl){
		// moves 36 degree = 0.01 meters aprox
		
		// send pack type Byte[]
		// while control if is possible continue forward
		// send info about of the sensors each 0.5cms
		tacho.resetTacho();
		byte[] pack;
		for(int i=0; i< distance*2; i++){
			//Do not touch and not give order to stop 
			if(!sensorThread.isTouched() && clThread.getReceive() != 0){
				//TODO send pack 1 
				byte[] control = ByteBuffer.allocate(2).putInt(1).array();
				byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
				byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
				byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
				
				pack = ArrayUtils.addAll(control, us1);
				pack = ArrayUtils.addAll(pack, us2);
				pack = ArrayUtils.addAll(pack, ir);
				cl.sendArray(pack);
				//movement
				this.leftMotor.rotate(-18, true);
				this.rightMotor.rotate(-18, true);
				
			//Is touching	
			}else if (sensorThread.isTouched()){
				//pack 5
				byte[] pack5 = {5};
				cl.sendArray(pack5);
				break;
				
			//Have order to stop	
			}else if(clThread.getReceive() == 0) {
				break;
			}
		}
		//TODO send pack 1 and 4 distance
		//pack 0
		byte[] control = ByteBuffer.allocate(2).putInt(1).array();
		byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
		byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
		byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
		
		pack = ArrayUtils.addAll(control, us1);
		pack = ArrayUtils.addAll(pack, us2);
		pack = ArrayUtils.addAll(pack, ir);
		cl.sendArray(pack);
		
		//pack 4
		byte[] pack4 = ArrayUtils.addAll(ByteBuffer.allocate(2).putInt(0).array(),
				ByteBuffer.allocate(2).putInt(tacho.getTachoMedia()).array());
		cl.sendArray(pack4);
	}
	
	public void turnLeft(int degrees, Client cl) {
		tacho.resetTacho();
		byte[] pack;
		
		for(int i=0; i< degrees*2; i=i+3){
			//Do not touch and not give order to stop 
			if(!sensorThread.isTouched() && clThread.getReceive() != 0){
				//TODO send pack 2 each 3 degrees  
				byte[] control = ByteBuffer.allocate(2).putInt(2).array();
				byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
				byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
				byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
				
				pack = ArrayUtils.addAll(control, us1);
				pack = ArrayUtils.addAll(pack, us2);
				pack = ArrayUtils.addAll(pack, ir);
				cl.sendArray(pack);
				//movement
				leftMotor.rotate(-3, true);
				rightMotor.rotate(3);
				
			//Is touching	
			}else if (sensorThread.isTouched()){
				//pack 5
				byte[] pack5 = {5};
				cl.sendArray(pack5);
				break;
				
			//Have order to stop	
			}else if(clThread.getReceive() == 0) {
				break;
			}
		}
		//TODO send pack 1 and 4 distance
		//pack 0
		byte[] control = ByteBuffer.allocate(2).putInt(1).array();
		byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
		byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
		byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
		
		pack = ArrayUtils.addAll(control, us1);
		pack = ArrayUtils.addAll(pack, us2);
		pack = ArrayUtils.addAll(pack, ir);
		cl.sendArray(pack);
		
		//pack 4
		byte[] pack4 = ArrayUtils.addAll(ByteBuffer.allocate(2).putInt(0).array(),
				ByteBuffer.allocate(2).putInt(tacho.getTachoMedia()).array());
		cl.sendArray(pack4);
		
	}
	
	public void turnRight(int degrees, Client cl) {
		byte[] pack;
		
		for(int i=0; i< degrees*2; i=i+3){
			//Do not touch and not give order to stop 
			if(!sensorThread.isTouched() && clThread.getReceive() != 0){
				//TODO send pack 3 each 3 degrees 
				byte[] control = ByteBuffer.allocate(2).putInt(3).array();
				byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
				byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
				byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
				
				pack = ArrayUtils.addAll(control, us1);
				pack = ArrayUtils.addAll(pack, us2);
				pack = ArrayUtils.addAll(pack, ir);
				cl.sendArray(pack);
				//movement
				rightMotor.rotate(-3, true);
				leftMotor.rotate(3);
				
			//Is touching	
			}else if (sensorThread.isTouched()){
				//pack 5
				byte[] pack5 = {5};
				cl.sendArray(pack5);
				break;
				
			//Have order to stop	
			}else if(clThread.getReceive() == 0) {
				break;
			}
		}
		//TODO send pack 3 and 4 distance
		//pack 0
		byte[] control = ByteBuffer.allocate(2).putInt(3).array();
		byte[] us1= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance1()).array();
		byte[] us2= ByteBuffer.allocate(4).putFloat(sensorThread.getSonarDistance2()).array();
		byte[] ir= ByteBuffer.allocate(4).putFloat(sensorThread.getIRDistance()).array();
		
		pack = ArrayUtils.addAll(control, us1);
		pack = ArrayUtils.addAll(pack, us2);
		pack = ArrayUtils.addAll(pack, ir);
		cl.sendArray(pack);
		
		//pack 4
		byte[] pack4 = ArrayUtils.addAll(ByteBuffer.allocate(2).putInt(0).array(),
				ByteBuffer.allocate(2).putInt(tacho.getTachoMedia()).array());
		cl.sendArray(pack4);
	
	}

	
	
	public String ParallelWall() {
		String msg= null;
		float error =(float) 0.02;
		tacho.resetTacho();
		
		msg = sensorThread.getSonarDistance1() + "*" + sensorThread.getSonarDistance2();
		
		if(sensorThread.getSonarDistance1() < sensorThread.getSonarDistance2()){
			do{
				rightMotor.rotate(5, true);
				leftMotor.rotate(-5);
			}
			while(sensorThread.getSonarDistance2()-sensorThread.getSonarDistance1() > error);
		}else if(sensorThread.getSonarDistance2() < sensorThread.getSonarDistance1()){
			do{
				leftMotor.rotate(5, true);
				rightMotor.rotate(-5);
			}
			while(sensorThread.getSonarDistance2()-sensorThread.getSonarDistance1() > error);
		}
		
		msg = msg + "/" + sensorThread.getSonarDistance1() + "*" + sensorThread.getSonarDistance2();
		msg = msg + "?" + sensorThread.getIRDistance();
		msg = msg + "#" + tacho.getTachoMedia();
		System.out.println(msg);
	
		return msg;
	}
	
	
	
	
}
