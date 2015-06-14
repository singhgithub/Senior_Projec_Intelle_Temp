package com.main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;



public class Database {
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
	static Database bd = new Database();
	
	/*//used when running code on levnovo
	String url = "jdbc:mysql://10.0.0.20:3306/HelloWorld";
	String user = "*******";
	String password = null;*/
	
	//use below when running code on pi
	//String url = "jdbc:mysql://127.0.0.1:3306/HelloWorld";
	String url = "jdbc:mysql://127.0.0.1:3306/Senior_Project_Database";
	String user = "root";
	String password = "*********";

	/*//use below if running code on laptop
	String url = "jdbc:mysql://10.0.0.20:3306/Senior_Project_Database";
	String user = "*********";		//this has to registered into the pi, with your latop ip address then u can't put user, pass to get access to db 
	String password = null;*/
	
	/*//use when working with school sd card pi 
	String url = "jdbc:mysql://10.0.0.8:3306/Senior_Project_Database";
	String user = "*********";
	String password = null;*/
	
	//constructor 
	public Database() {
		System.out.println("[Database Class] Constructor is Called");
		connect_to_the_database();
	}

	//method that take care of connecting to the database
	public void connect_to_the_database(){
		System.out.println("[Database Class] connect_to_the_database() is Called");
		
		/*Don't have to do this in the newer version of JDBC Jar
		 * try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("[Database Class] connect_to_the_database() JDBC Driver Loaded");
			
		} catch (ClassNotFoundException e) {
			System.out.println("[Database Class] connect_to_the_database() 1st catch stmt Error: ");
			e.printStackTrace();
			return;
		}*/
	 
	 
		try {
			
			//NOTE that lot of the places below i am using 3360 instead of 3306
			//connection = DriverManager.getConnection("jdbc:mysql://10.0.0.20:3306/HelloWorld");
			//connection = DriverManager.getConnection("jdbc:mysql://10.0.0.20/HelloWorld","root", "sardari1");
			//connection = DriverManager.getConnection("jdbc:mysql://10.0.0.20:3306/phpmyadmin/index.php","root", "sardari1");
			//connection = DriverManager.getConnection("jdbc:mysql://10.0.0.20/phpmyadmin/index.php","root", "sardari1");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			//connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3360/test");
			//connection = DriverManager.getConnection("jdbc:mysql://10.0.0.20:3306/HelloWorld?user=root&password=sardari1");
			
			//register the jdbc drivers, i didn't need to do this in my Multiscree_Main senior project
			//if i uncomment the below line i get an error: no sutiable drivers were found 
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("[Database Class] connect_to_the_database() Connection Successful");
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("[Database Class] connect_to_the_database() catch stmt Error:  ");
			e.printStackTrace();
			return;
		}
	 
		/*if (connection != null) {
			System.out.println("[Database Class] connect_to_the_database() Connected to DB");
		} else {
			System.out.println("[Database Class] connect_to_the_database() Error Can't Connect to DB");
		}*/
	}

	
	public void insert_query_to_db(String x){
		System.out.println("[Database Class] insert_query_to_db() is Called");
		try {
			stmt = connection.createStatement();
			/*String query_string = "INSERT INTO hello"
				+ "(value) " + "VALUES"
				+ "( "+ x +" ) ";
			*/	
			//String query_string = "INSERT INTO hello (value) VALUES (hello2)";
			
			//String query_string = "INSERT INTO hello (value) VALUES ('hello2')";
			
			String query_string = "INSERT INTO hello (value) VALUES ('"+ x +"')";
			stmt.executeUpdate(query_string);
			
			System.out.println("[Database Class] insert_query_to_db() No Error");
			
		} catch (SQLException e) {
			System.out.println("[Database Class] insert_query_to_db() Error:  ");
			e.printStackTrace();
		}
		
	}
	
	public void insert_query_to_db_room1(double x){
		System.out.println("[Database Class] insert_query_to_db_room1() is Called");
		try {
			stmt = connection.createStatement();
			String query_string = "INSERT INTO Room1 (Temperature) VALUES ('"+ x +"')";
			stmt.executeUpdate(query_string);
			
			System.out.println("[Database Class] insert_query_to_db_room1() No Error");
			
		} catch (SQLException e) {
			System.out.println("[Database Class] insert_query_to_db_room1() Error:  ");
			e.printStackTrace();
		}
		
	}
	
	public void insert_query_to_db_room2(double x){
		System.out.println("[Database Class] insert_query_to_db_room2() is Called");
		try {
			stmt = connection.createStatement();
			String query_string = "INSERT INTO Room2 (Temperature) VALUES ('"+ x +"')";
			stmt.executeUpdate(query_string);
			
			System.out.println("[Database Class] insert_query_to_db_room2() No Error");
			
		} catch (SQLException e) {
			System.out.println("[Database Class] insert_query_to_db_room2() Error:  ");
			e.printStackTrace();
		}
		
	}
	
	public void insert_query_to_db_room3(double x){
		System.out.println("[Database Class] insert_query_to_db_room3() is Called");
		try {
			stmt = connection.createStatement();
			String query_string = "INSERT INTO Room3 (Temperature) VALUES ('"+ x +"')";
			stmt.executeUpdate(query_string);
			
			System.out.println("[Database Class] insert_query_to_db_room3() No Error");
			
		} catch (SQLException e) {
			System.out.println("[Database Class] insert_query_to_db_room3() Error:  ");
			e.printStackTrace();
		}
		
	}
	
	public double[] pull_Data_For_Room1_Graph(){
		System.out.println("[Database Class] pull_Data_For_Room1_Graph() is Called");
		
		//double[] a = null;
		//double[] a = {0.0};
		int number_of_values_to_pull = 50;
		//Array x = null;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room1";
			rs = stmt.executeQuery(query_string);
			
			/*for (int j = 0; j < number_of_values_to_pull; j++) {
				a[j] = rs.getDouble(j);
			}*/
			
			/*didn't work, system out for a[]=null
			 * int i=0;
			while (rs.next() && i == 50) {
				a[i] = rs.getDouble(2);			
				i++;
			}*/
			
			/*didnt' worked, go a null pointer error
			 * int i=0;
			while (rs.next() && i == 50) {
				x = rs.getArray("Temperature");			
				i++;
			}
			a = (double[]) x.getArray();			//this what i got a null pointer error for a being null
			//a = (double[]) Array.getArray();*/
			
			/*got an error "java.sql.SQLFeatureNotSupportedException"
			 * Array x = rs.getArray("Temperature");
			a = (double[]) x.getArray();*/
			
			/*again got an error "java.sql.SQLFeatureNotSupportedException"
			 * int i = 0;
			while(rs.next() && i == 50){
				Array x = rs.getArray("Temperature");
				a = (double[]) x.getArray();
			}*/
			
			/*didn't work got the same error again, "java.sql.SQLFeatureNotSupportedException"
			 * int i = 0;
			while(rs.next() && i == 50){			//without commenting out it out the system out didn't print anything
				Array x = rs.getArray("Temperature");
				System.out.println(x.toString());
			}*/
			
			/*got a diff error, java.sql.SQLException: Column Index out of range, 2 > 1
			 * int i = 0;
			while(rs.next()) {			//without commenting out it out the system out didn't print anything
				Array x = rs.getArray(2);
				System.out.println(x.toString());
				System.out.println(rs.getDouble(2));
			}*/
			
			/*same error, "java.sql.SQLFeatureNotSupportedException"
			 * while(rs.next()) {			//without commenting out it out the system out didn't print anything
				System.out.println(rs.getArray("Temperature"));
				System.out.println(rs.getDouble("Temperature"));
			}*/
			
			/*didn't worked 
			 * int i = 0;
			while(rs.next()) {			//without commenting out it out the system out didn't print anything
				Array x = rs.getArray(1);
				System.out.println(x.toString());
				System.out.println(rs.getDouble(1));
			}*/
			
			while(rs.next()) {
				double b = rs.getDouble("Temperature");
				a.add(b);
			}
			
			System.out.println("[Database Class] pull_Data_For_Room1_Graph() Size of a[] : "+ a.size());

			
			//don't think i need below, as i would know for sure when i come to adding data to graph 
			//double[] c = new double[a.size()];
			
			//for (int j = 0; j < a.size(); j++) {
			for (int j = 0; j < number_of_values_to_pull; j++) {			//since i got a nullpointer error, as the c[] size is only 50 and a[] => 110
				c[j] = a.get(j).doubleValue();
			}
			
			//System.out.println("[Database Class] pull_Data_For_Room1_Graph() a[]: "+ Arrays.toString(a.toArray()));      //too much data on the systemout, as it displays the entire database
			
			System.out.println("[Database Class] pull_Data_For_Room1_Graph() Size of c[]: "+ c.length);
			System.out.println("[Database Class] pull_Data_For_Room1_Graph() c[]: "+ Arrays.toString(c));
			
			System.out.println("[Database Class] pull_Data_For_Room1_Graph() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_Data_For_Room1_Graph() Error:  ");
			e.printStackTrace();
		}
		
		return c;
	}
	
	public double[] pull_Data_For_Room2_Graph(){
		System.out.println("[Database Class] pull_Data_For_Room2_Graph() is Called");
		
		int number_of_values_to_pull = 50;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room2";
			rs = stmt.executeQuery(query_string);
			
			while(rs.next()) {
				double b = rs.getDouble("Temperature");			//this throwing a sql exception for some reason error"sql exception after end of result set "
				a.add(b);
			}
			
			System.out.println("[Database Class] pull_Data_For_Room2_Graph() Size of a[] : "+ a.size());

			//for (int j = 0; j < a.size(); j++) {
			for (int j = 0; j < number_of_values_to_pull; j++) {			//since i got a nullpointer error, as the c[] size is only 50 and a[] => 110
				c[j] = a.get(j).doubleValue();
			}
			
			//System.out.println("[Database Class] pull_Data_For_Room1_Graph() a[]: "+ Arrays.toString(a.toArray()));      //too much data on the systemout, as it displays the entire database
			
			System.out.println("[Database Class] pull_Data_For_Room2_Graph() Size of c[]: "+ c.length);
			System.out.println("[Database Class] pull_Data_For_Room2_Graph() c[]: "+ Arrays.toString(c));
			
			System.out.println("[Database Class] pull_Data_For_Room2_Graph() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_Data_For_Room2_Graph() Error:  ");
			e.printStackTrace();
		}
		
		return c;
	}
	
	public double[] pull_Data_For_Room3_Graph(){
		System.out.println("[Database Class] pull_Data_For_Room3_Graph() is Called");
		
		int number_of_values_to_pull = 50;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room3";
			rs = stmt.executeQuery(query_string);
			
			while(rs.next()) {
				double b = rs.getDouble("Temperature");
				a.add(b);
			}
			
			System.out.println("[Database Class] pull_Data_For_Room3_Graph() Size of a[] : "+ a.size());

			//for (int j = 0; j < a.size(); j++) {
			for (int j = 0; j < number_of_values_to_pull; j++) {			//since i got a nullpointer error, as the c[] size is only 50 and a[] => 110
				c[j] = a.get(j).doubleValue();
			}
			
			//System.out.println("[Database Class] pull_Data_For_Room1_Graph() a[]: "+ Arrays.toString(a.toArray()));      //too much data on the systemout, as it displays the entire database
			
			System.out.println("[Database Class] pull_Data_For_Room3_Graph() Size of c[]: "+ c.length);
			System.out.println("[Database Class] pull_Data_For_Room3_Graph() c[]: "+ Arrays.toString(c));
			
			System.out.println("[Database Class] pull_Data_For_Room3_Graph() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_Data_For_Room3_Graph() Error:  ");
			e.printStackTrace();
		}
		
		return c;
	}

	public double[] db_last_values_of_all_rooms(){
		System.out.println("[Database Class] db_last_values_of_all_rooms() is Called");
		
		double[] c = new double[3];
		c[0] = pull_db_last_value_for_room1();
		c[1] = pull_db_last_value_for_room2();
		c[2] = pull_db_last_value_for_room3();
		
		System.out.println("[Database Class] db_last_values_of_all_rooms() c[]: "+ Arrays.toString(c));
		
		return c;
	}
	
	public double pull_db_last_value_for_room1(){
		System.out.println("[Database Class] pull_db_last_value_for_room1() is Called");
		
		int number_of_values_to_pull = 1;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room1 ORDER BY Time DESC LIMIT 1";  //ORDER BY Time DESC 
			rs = stmt.executeQuery(query_string);
			
			while (rs.next()) {
				a.add(rs.getDouble("Temperature"));
			}
			
			for (int j = 0; j < number_of_values_to_pull; j++) {			
				c[j] = a.get(j).doubleValue();
			}
			System.out.println("[Database Class] pull_db_last_value_for_room1() a[]: "+ Arrays.toString(a.toArray()));
			System.out.println("[Database Class] pull_db_last_value_for_room1() c[]: "+ Arrays.toString(c));	
			
			System.out.println("[Database Class] pull_db_last_value_for_room1() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_db_last_value_for_room1() Error:  ");
			e.printStackTrace();
		}
		return c[0];
	}
	
	public double pull_db_last_value_for_room2(){
		System.out.println("[Database Class] pull_db_last_value_for_room2() is Called");
		
		int number_of_values_to_pull = 1;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room2 ORDER BY Time DESC LIMIT 1";  //ORDER BY Time DESC 
			rs = stmt.executeQuery(query_string);
			
			while (rs.next()) {
				a.add(rs.getDouble("Temperature"));
			}
			
			for (int j = 0; j < number_of_values_to_pull; j++) {			
				c[j] = a.get(j).doubleValue();
			}
			System.out.println("[Database Class] pull_db_last_value_for_room2() a[]: "+ Arrays.toString(a.toArray()));
			System.out.println("[Database Class] pull_db_last_value_for_room2() c[]: "+ Arrays.toString(c));	
			
			System.out.println("[Database Class] pull_db_last_value_for_room2() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_db_last_value_for_room2() Error:  ");
			e.printStackTrace();
		}
		return c[0];
	}
	
	public double pull_db_last_value_for_room3(){
		System.out.println("[Database Class] pull_db_last_value_for_room3() is Called");
		
		int number_of_values_to_pull = 1;
		ArrayList<Double> a = new ArrayList<Double>();
		double[] c = new double[number_of_values_to_pull];
		
		try {
			stmt = connection.createStatement();
			String query_string = "SELECT Temperature FROM Room3 ORDER BY Time DESC LIMIT 1";  //ORDER BY Time DESC 
			rs = stmt.executeQuery(query_string);
			
			while (rs.next()) {
				a.add(rs.getDouble("Temperature"));
			}
			
			for (int j = 0; j < number_of_values_to_pull; j++) {			
				c[j] = a.get(j).doubleValue();
			}
			System.out.println("[Database Class] pull_db_last_value_for_room3() a[]: "+ Arrays.toString(a.toArray()));
			System.out.println("[Database Class] pull_db_last_value_for_room3() c[]: "+ Arrays.toString(c));	
			
			System.out.println("[Database Class] pull_db_last_value_for_room3() No Error");
			
		} catch (SQLException /*| SQLFeatureNotSupportedException*/ e) {
			System.out.println("[Database Class] pull_db_last_value_for_room3() Error:  ");
			e.printStackTrace();
		}
		return c[0];
	}
	

	public static Database getBd() {
		return bd;
	}

}
