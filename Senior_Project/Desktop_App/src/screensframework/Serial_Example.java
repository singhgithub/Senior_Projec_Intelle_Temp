package screensframework;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import com.enzo.packagee.DemoGauge;
import com.enzo.packagee.Gauge;

public class Serial_Example {
	
	// path of the serial device
	//String serial_path_var = "/dev/ttyUSB0"; // if i get an error this is
	
	//when showing prof alim the code, pi misterisly changed the ttyUSB from 0 to 1
	String serial_path_var = "/dev/ttyUSB0"; 
	
	// the first thing i should check
	// create an instance of the serial communications class
	final Serial serial = SerialFactory.createInstance();
	static byte[] x;
	//Gauge gauge_class_obj = new Gauge();
	//static double[] y;
	
	static double[] y = {1,1,1,1};
	//static double[] y;			//doing this after the Exercise33-b14_0_3.jar was run and i got a outofbound exception cause first value
								//of array was 99 and in there i am doing y[] = x[] , and y is only size 4  
	static Database database_obj;
	static Pull_Display_Gauges_Class update_Guages;
	
	byte[] z = {1,1};
	
	//constructor
	public Serial_Example() {
		System.out.println("[Serial_Example] Constructor is Called");
		
		/*open_Serial_Port();
		add_Serial_Listener();*/
		
		database_obj = new Database();
        database_obj.connect_to_the_database();
        update_Guages = new Pull_Display_Gauges_Class(DemoGauge.control, DemoGauge.control1, DemoGauge.control2, database_obj);
	}
	

		public void add_Serial_Listener(/* dont' have to do this Gauge a, Gauge b, Gauge c*/){
			System.out.println("[Serial_Example] add_Serial_Listener() is Called");
			
			// create and register the serial data listener
			serial.addListener(new SerialDataListener() {
				@Override 
				public void dataReceived(SerialDataEvent event) { // print out the data received to the console
					
					 System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() is Called");
					
					//its added into Exercise30_0_7.jar 
					//newly add to view the print statments on arduino 
					System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() From Arduino: "+ event.getData());
					
					//getdata method returns a string, think hw things effect things 
					//haven't tested what would happen if i send a string from arduino instead
					 x = event.getData().getBytes();
					 
					//System.out.print("x: "+ Arrays.toString(x)); 
					System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() byte x[]: "+ Arrays.toString(x)); 
					
					/*String s = event.getData();
			  		  int x = s - '0';*/
					
					if (x[0] != 99) {		//got a null pointer error here for redo3 Exercise30-b
						//z = x;
						System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived()  1st if statement is Called");
						
						//i expect this to execute as i press the guages and arudino write string on the serial port 
						if (x[0] == 88) {
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st if statement is Called");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st if CONFORMATION back from arduino for Gauge1");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st if statm "+ Arrays.toString(x));
							
						}else if (x[0] == 77) {
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st elseif statement is Called");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st elseif CONFORMATION back from arduino for Gauge2");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 1st elseif statm "+ Arrays.toString(x));
						//}else if (x[0] == 66) {
						}else if (x[0] == 66) {
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 2st elseif statement is Called");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 2st elseif CONFORMATION back from arduino for Gauge3");
							System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() (1st if statem's) 2st if statm "+ Arrays.toString(x));
						}
						//x = null;			//i got a null pointer error here, i did this for Exercise30-b14_0_5.jar
					}
					
					//this means that the data coming from the arduino is for the guages 
					if (x[0] == 99) {
						System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() 2nd if statement is Called");
						
						/*a.setValue((double) x[1]);
						b.setValue((double) x[2]);
						b.setValue((double) x[3]);*/
						
						/*a.setValue(70.0);
						b.setValue(75.0);
						c.setValue(80.0);*/
						
						/*this worked perfect
						 * DemoGauge.control.setValue(70.0);
						DemoGauge.control1.setValue(80.0);
						DemoGauge.control2.setValue(85.0);*/
						
						/*this didn't worked
						 * DemoGauge.control.setValue((double) x[1]);
						DemoGauge.control1.setValue((double) x[2]);
						DemoGauge.control2.setValue((double) x[3]);*/
						
						//the latest one that works, 
						//didn't worked, got a null pointer error at line 76, there is a screen shot
						//but later when i ran the Exercise29-a12.jar it worked, but i have to change the y[]={1,1,1,1}
						//for (int i = 0; i < x.length; i++) {		//[didn't do it]i was getting a outofbouce error for Exercise32-b, as arduino i sending conformation arrays
						
						//for (int i = 0; i < x.length; i++) {
						for (int i = 0; i < 4; i++) {			//changed after Exercise30_b14_0_4.jar as i got a null pointer exception as i did "static double y;"
							y[i] = x[i];
						}
						System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() byte x[] converted to double y[]" );
						System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() double y[]: "+ Arrays.toString(y) );
						
						/*don't need this any more, cause i am pulling data from the database nw to update the guages, not arduino
						 * DemoGauge.control.setValue(y[1]);
						DemoGauge.control1.setValue(y[2]);
						DemoGauge.control2.setValue(y[3]);*/
						
						/*didn't worked got an error on line 85, out of bounce error
						 * for (int i = 1; i < x.length; i++) {
							y[i] = ByteBuffer.wrap(x).getDouble(i);
						}
						DemoGauge.control.setValue(y[1]);
						DemoGauge.control1.setValue(y[2]);
						DemoGauge.control2.setValue(y[3]);*/
						
						/*didn't worked got an error on line 94, out of bounce error
						 * for (int i = 0; i < x.length; i++) {
							System.out.println(ByteBuffer.wrap(x).getDouble(i));
						}*/
						
						/*didn't worked, got an error java.nio.BufferUnderflowException, there is an image
						 * for (int i = 0; i < x.length; i++) {
							System.out.println(ByteBuffer.wrap(x).getDouble());
						}*/	
						
						/*//didn't worked, didn't go the exact number, and also random number were like 75.9874853749, jar14
						 * System.out.println("[Serial_Example Class] add_Serial_Listener() dataReceived() Random: "+ (65 + ((double)(Math.random()*(80 - 65)))) );
						DemoGauge.control.setValue((65 + ((double)(Math.random()*(80 - 65)))));
						DemoGauge.control1.setValue((65 + ((double)(Math.random()*(80 - 65)))));
						DemoGauge.control2.setValue((65 + ((double)(Math.random()*(80 - 65)))));*/
						
						database_obj.insert_query_to_db_room1(y[1]);
						database_obj.insert_query_to_db_room2(y[2]);
						database_obj.insert_query_to_db_room3(y[3]);
						
						//database_obj.pull_Data_For_Room1_Graph();
						//database_obj.pull_Data_For_Room2_Graph();
						//database_obj.pull_Data_For_Room3_Graph();
						
						update_Guages.update_All_Gauges();
						
						x = null;
						
					}
					
					//didn't worked either even though i did recieved 99,22,22,23 for a while and then once i got 99,22,22,23 in the front then a outofbound exception
					//the outofbounce occured because the first value is 99 and then i went into 2nd if statement, and in there y []= x[] and y is only of size 4
					//x = null;		//becuase if i get unwanted data at the end i just wanna set the array to null, so unwanted stuff don't get added to it
				} 
			
			});
		//x = null; 	//it didn't worked when put here, same result as we first run the richard code as... amm.. NOT SURE
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
			  
		public static byte[] getX() {
			return x;
		}
		

		//getter for the Database object so it could be used in the screen2controller class, since a connection is established with this obj etc
		//i am hoping when i call this method the constructor dosn't run 
	    public static Database getDatabase_obj() {
	    	System.out.println("[Serial_Example] getDatabase_obj() is Called");
			return database_obj;
		}
		
		
}

// END SNIPPET: serial-snippet