package com.example.exercise34_b;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

public class Post_SeekBar_Async extends AsyncTask<String, String, String>{
	String params_String_parameter;
	ArrayList<String> array = new ArrayList<String>();
	String id;
	int temp;
	
	
	public Post_SeekBar_Async(String id, int temp) {
		System.out.println("[Post_SeekBar_Async Class] Constructor is Called");
		this.id = id;
		this.temp = temp;
	}
	
	@Override
	protected String doInBackground(String... params) {
		System.out.println("[Post_SeekBar_Async Class] doInBackground() called");
		
		HttpURLConnection con = null;
		URL url;
		InputStream is = null;
		StringBuilder sb = null;
		String urlParameters = "id="+id+"&temp="+temp;

		try {
			// setting up a httpurl connection (section)
			params_String_parameter = params[0];
			url = new URL(params_String_parameter);
			con = (HttpURLConnection) url.openConnection();
			// con.setReadTimeout(10000 /* milliseconds */);
			// con.setConnectTimeout(15000 /* milliseconds */);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Language", "en-US");
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			// Start the query
			//con.connect();

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
			System.out.println("[Post_SeekBar_Async Class] doInBackground() no Exception thrown");
		} catch (IOException e) {
			// handle the exception !
			e.printStackTrace();
			System.out.println("[Post_SeekBar_Async Class] doInBackground() thrown an Exception");
		}
		
		return sb.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
		System.out.println("[Post_SeekBar_Async Class] onPostExecute() is called");
		System.out.println("[Post_SeekBar_Async Class] onPostExecute() Response from Servlet: "+ result);
	}

}
