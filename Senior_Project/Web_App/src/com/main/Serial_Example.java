package com.main;

import org.apache.catalina.tribes.util.Arrays;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

/**
 * This example code demonstrates how to perform serial communications using the
 * Raspberry Pi.
 * 
 * @author Robert Savage
 */
public class Serial_Example {
	
	static Serial_Example serial_Example_Obj= new Serial_Example();
	
	final Serial serial = SerialFactory.createInstance();;
	String serial_path_var = "/dev/ttyUSB1";
	byte[] x;
	
	public Serial_Example() {
		System.out.println("[Serial_Example] Constructor() is Called");
		open_Serial_Port();
		add_Listener();
		
	}


	public void add_Listener() {
		System.out.println("[Serial_Example] add_Listener() is Called");
		// create and register the serial data listener
		serial.addListener(new SerialDataListener() {
			@Override
			public void dataReceived(SerialDataEvent event) {
				x = event.getData().getBytes();
				
				System.out.println("[Serial_Example] add_Listener() dataReceived() Array recieved from Arduino x[]: "+Arrays.toString(x));
			}
		});
	}


	public void open_Serial_Port(){
		System.out.println("[Serial_Example] open_Serial_Port() is Called");
		  try {
			  serial.open(serial_path_var, 9600);
			  
			  //System.out.println("Serial Port Opened");
			  // open the default serial port provided on the GPIO header
			  // serial.open(Serial.DEFAULT_COM_PORT, 38400);
			 
			  System.out.println("[Serial_Example] open_Serial_Port() serialport is opened");
			  
		  } catch (SerialPortException ex) {
			  System.out.println("[Serial_Example] open_Serial_Port() Serial Exception : " + ex.getMessage());
			  
			  /*//newly add stuff after the incident of PI keep changing the name of arduino between ttyUSB0 & ttyUSB1
			  serial_path_var = "/dev/ttyUSB0";
			  open_Serial_Port();*/
			  
			  return;
		  }
	  }
	
	/**
	 * this method will be called in the servlet to send the data that the servlet recieved via post to arduino
	 * @param x	id of the value like 88,77,66
	 * @param y value itself
	 */
	public void send_Data_Serial_Port(byte x, byte y) {
		System.out.println("[Serial_Example] send_Data_Serial_Port() is Called");
		
		try {
			byte[] a = {x,y};
			serial.write(a);
			System.out.println("[Serial_Example] send_Data_Serial_Port() data send to Arduino Success");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public static Serial_Example getSerial_Example_Obj() {
		return serial_Example_Obj;
	}
	
}

