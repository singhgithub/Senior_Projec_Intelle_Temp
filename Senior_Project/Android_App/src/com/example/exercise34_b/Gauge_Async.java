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

import android.os.AsyncTask;

public class Gauge_Async extends AsyncTask<String, String, String> {

	String params_String_parameter;
	ArrayList<String> array = new ArrayList<String>();
	private GaugeView mGaugeView1;
	private GaugeView mGaugeView2;
	private GaugeView mGaugeView3;
	String room1_graph_url = "http://10.164.144.96:8080/Exercise33-i/Room1_Servlet";
	
	public Gauge_Async(GaugeView x,GaugeView y, GaugeView z ) {
		System.out.println("[Gauge_Async Class] Constructor is Called");
			mGaugeView1 = x;
			mGaugeView2 = y;
			mGaugeView3 = z;
	}

	@Override
	protected String doInBackground(String... params) {
		System.out.println("[Gauge_Async Class] doInBackground() called");
		
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
			System.out.println("[Gauge_Async Class] doInBackground() no Exception thrown");
		} catch (IOException e) {
			// handle the exception !
			e.printStackTrace();
			System.out.println("[Gauge_Async Class] doInBackground() thrown an Exception");
		}
		
		//this is here because for some reason the GET to servlet is not working, this cause the app to crash
		//also its the first GET request that gets error where as the rest of them in the tick() method runs fine every 5sec
		//for now i have placed the below code to prevent that 
		try {
			if (sb == null) {
				System.out.println("[Gauge_Async Class] doInBackground() problem with All_Rooms_Data_Servlet");
				return "0";
			}
		} catch (Exception e) {
			System.out.println("[Gauge_Async Class] doInBackground() ignor the exception");
		}
		
		return sb.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
		System.out.println("[Gauge_Async Class] onPostExecute() is called");
		
		try {
			JSONObject json_obj = new JSONObject(result);
			JSONArray json_array = json_obj.getJSONArray("array");
			
			System.out.println("[Gauge_Async Class] onPostExecute() json_obj: "+ json_obj.toString());
			System.out.println("[Gauge_Async Class] onPostExecute() json_array[0]: "+ json_array.getString(0));
			
			//this cause the error of can't conver integer to string 
			//System.out.println("[Gauge_Async Class] onPostExecute() array [0]: "+ json_array.getJSONObject(0).toString());
			
			
			for (int i = 0; i < json_array.length(); i++) {
				array.add(json_array.getString(i)); 
			}
			System.out.println("[Gauge_Async Class] onPostExecute() json to array converstion success");
			System.out.println("[Gauge_Async Class] onPostExecute() array: "+ array );
			System.out.println("..............................");
			
			mGaugeView1.setTargetValue(Float.valueOf(array.get(0)));
			mGaugeView2.setTargetValue(Float.valueOf(array.get(1)));
			mGaugeView3.setTargetValue(Float.valueOf(array.get(2)));
			
			//new Room1_Graph_Async(new ViewPagerAdapter().getMySimpleXYPlot()).execute(room1_graph_url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
