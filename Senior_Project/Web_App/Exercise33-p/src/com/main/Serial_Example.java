package com.main;

import java.sql.Array;

import org.apache.catalina.tribes.util.Arrays;
import org.eclipse.jdt.internal.compiler.ast.Javadoc;

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
	String serial_path_var = "/dev/ttyUSB0";
	byte[] x;
	static byte[] y;
	
	public Serial_Example() {
		open_Serial_Port();
		add_Listener();
		
	}


	public void add_Listener() {
		// create and register the serial data listener
		serial.addListener(new SerialDataListener() {
			@Override
			public void dataReceived(SerialDataEvent event) {
				x = event.getData().getBytes();
				
				System.out.println("[Serial_Example] add_Listener() dataReceived() x[]: "+Arrays.toString(x));
				
				y = x;
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
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public static Serial_Example getSerial_Example_Obj() {
		return serial_Example_Obj;
	}
	
}

