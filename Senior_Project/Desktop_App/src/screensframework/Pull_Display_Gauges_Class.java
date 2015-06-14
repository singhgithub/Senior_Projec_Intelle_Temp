package screensframework;

import com.enzo.packagee.Gauge;

public class Pull_Display_Gauges_Class {

	Gauge room1;
	Gauge room2;
	Gauge room3;
	Database db;
	
	public Pull_Display_Gauges_Class(Gauge room1, Gauge room2, Gauge room3, Database db) {
		System.out.println("[Pull_Display_Gauges_Class Class] Constructor is Called");
		
		this.room1 = room1;
		this.room2 = room2;
		this.room3 = room3;
		this.db = db;
	}
	
	public void update_All_Gauges(){
		System.out.println("[Pull_Display_Gauges_Class Class] update_All_Gauges() is Called");
		
		double[] c = new double[3];
		c = db.db_last_values_of_all_rooms();
		
		room1.setValue(c[0]);
		room2.setValue(c[1]);
		room3.setValue(c[2]);
		
	}
	
}
