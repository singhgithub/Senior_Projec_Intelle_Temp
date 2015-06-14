package screensframework;

import java.util.Arrays;

import com.enzo.packagee.Gauge;

public class User_Set_Temp_Class {
	
	//static User_Set_Temp_Class user_s_t_c_obj;			//i think i got a null pointer error for Exericse30-b cause of this 
	static User_Set_Temp_Class user_s_t_c_obj = new User_Set_Temp_Class();

	public User_Set_Temp_Class() {
		System.out.println("[User_Set_Temp_Class Class] Constructor is Called");
		
	}

	public static User_Set_Temp_Class getUser_s_t_c_obj() {
		return user_s_t_c_obj;
	}
	
	//remember to pass in room1 guage obj in the g params, and for s it should be the serial_obj that i created in ScreenFramework
	public void get_room1_threshold_send_to_arduino(Gauge g, Serial_Example s){
		System.out.println("[User_Set_Temp_Class Class] get_room1_threshold_send_to_arduino() is Called");
		
		/*let change this to byte 
		 * int size_of_c = 2;
		double[] c = new double[size_of_c];
		c[0] = 88;		//88 is an indentifier for that its threshold data from room1
		c[1] = g.getValue();*/
		
		int size_of_c = 2;
		byte[] c = new byte[size_of_c];
		c[0] = 88;		//88 is an indentifier for that its threshold data from room1
		c[1] = (byte)g.getThreshold();
		
		System.out.println("[User_Set_Temp_Class Class] get_room1_threshold_send_to_arduino() c []: "+ Arrays.toString(c));
		
		try {
			s.serial.write(c);
			System.out.println("[User_Set_Temp_Class Class] get_room1_threshold_send_to_arduino() serial.write COMPLETE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//remember to pass in room1 guage obj in the g params, and for s it should be the serial_obj that i created in ScreenFramework
	public void get_room2_threshold_send_to_arduino(Gauge g, Serial_Example s){
		System.out.println("[User_Set_Temp_Class Class] get_room2_threshold_send_to_arduino() is Called");
		
		int size_of_c = 2;
		byte[] c = new byte[size_of_c];
		c[0] = 77;		//77 is an indentifier for that its threshold data from room2
		c[1] = (byte)g.getThreshold();
		
		System.out.println("[User_Set_Temp_Class Class] get_room2_threshold_send_to_arduino() c []: "+ Arrays.toString(c));
		
		try {
			s.serial.write(c);
			System.out.println("[User_Set_Temp_Class Class] get_room2_threshold_send_to_arduino() serial.write COMPLETE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//remember to pass in room1 guage obj in the g params, and for s it should be the serial_obj that i created in ScreenFramework
	public void get_room3_threshold_send_to_arduino(Gauge g, Serial_Example s){
		System.out.println("[User_Set_Temp_Class Class] get_room3_threshold_send_to_arduino() is Called");
		
		int size_of_c = 2;
		byte[] c = new byte[size_of_c];
		//c[0] = 55;		//55 is an indentifier for that its threshold data from room5
		c[0] = 66;			//it should have been 66 not 55
		c[1] = (byte)g.getThreshold();
		
		System.out.println("[User_Set_Temp_Class Class] get_room3_threshold_send_to_arduino() c []: "+ Arrays.toString(c));

		try {
			s.serial.write(c);
			System.out.println("[User_Set_Temp_Class Class] get_room3_threshold_send_to_arduino() serial.write COMPLETE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
