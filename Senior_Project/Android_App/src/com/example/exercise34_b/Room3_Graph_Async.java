package com.example.exercise34_b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

public class Room3_Graph_Async extends AsyncTask<String, String, String>{
	private XYPlot mySimpleXYPlot ;
	String params_String_parameter;
	ArrayList<String> array = new ArrayList<String>();
	//String room3_graph_url = "http://10.0.0.8:8080/Exercise33-i/Room3_Servlet";
	String room3_graph_url = "http://*********:8080/Exercise33-i_2/Room3_Servlet";
	
	public Room3_Graph_Async(XYPlot x) {
		System.out.println("[Room3_Graph_Async Class] Constructor is Called");
		
		 mySimpleXYPlot = x;
	}
	
	@Override
	protected String doInBackground(String... params) {
		System.out.println("[Room3_Graph_Async Class] doInBackground() called");
		
		HttpURLConnection con = null;
		URL url;
		InputStream is = null;
		StringBuilder sb = null;

		try {
			// setting up a httpurl connection (section)
			params_String_parameter = params[0];
			url = new URL(params_String_parameter);
			con = (HttpURLConnection) url.openConnection();
			// con.setReadTimeout(10000 /* milliseconds */);
			// con.setConnectTimeout(15000 /* milliseconds */);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			// Start the query
			con.connect();

			// web data as a long string (section)
			is = con.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			is.close();
			// function ran proberly
			System.out.println("[Room3_Graph_Async Class] doInBackground() no Exception thrown");
		} catch (IOException e) {
			// handle the exception !
			e.printStackTrace();
			System.out.println("[Room3_Graph_Async Class] doInBackground() thrown an Exception");
		}

		return sb.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
		System.out.println("[Room3_Graph_Async Class] onPostExecute() is called");
		
		try {
			JSONObject json_obj = new JSONObject(result);
			JSONArray json_array = json_obj.getJSONArray("array");
			
			System.out.println("[Room3_Graph_Async Class] onPostExecute() json_obj: "+ json_obj.toString());
			//System.out.println("[Room3_Graph_Async Class] onPostExecute() json_array[0]: "+ json_array.getString(0));
			
			//this cause the error of can't conver integer to string 
			//System.out.println("[Room3_Graph_Async Class] onPostExecute() array [0]: "+ json_array.getJSONObject(0).toString());
			
			
			for (int i = 0; i < json_array.length(); i++) {
				array.add(json_array.getString(i)); 
			}
			System.out.println("[Room3_Graph_Async Class] onPostExecute() json to array converstion success");
			System.out.println("[Room3_Graph_Async Class] onPostExecute() array: "+ array );
			System.out.println("..............................");
			
			draw_Room3_Graph(array);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw_Room3_Graph(ArrayList<String> x){
		System.out.println("[Room3_Graph_Async Class] draw_Room3_Graph() is called");
		
		// Create a couple arrays of y-values to plot:
        Number[] series3Numbers = convert_ArrayList_To_Int(x);
		
		// Turn the above arrays into XYSeries':
        XYSeries series3 = new SimpleXYSeries(Arrays.asList(series3Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "R3");
        
        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.MAGENTA, Color.RED, null, null);
        
        // add a new series' to the xyplot:
        mySimpleXYPlot.addSeries(series3,series3Format);
        
        // reduce the number of range labels
        mySimpleXYPlot.setTicksPerRangeLabel(3);
        
        System.out.println("[Room3_Graph_Async Class] draw_Room3_Graph() room1 graph diaplayed Succesfully");
	}
	
	public Integer[] convert_ArrayList_To_Int(ArrayList<String> y){
		System.out.println("[Room3_Graph_Async Class] convert_ArrayList_To_Int() is called");
		
		Integer[] int_array = new Integer[y.size()];
		for (int i = 0; i < 10/*y.size()*/; i++) {
			int_array[i] = Integer.valueOf(y.get(i));
		}
		System.out.println("[Room3_Graph_Async Class] convert_ArrayList_To_Int() Conversion Successful");

		return int_array;
	}
}
