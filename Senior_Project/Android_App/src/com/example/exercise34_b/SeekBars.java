package com.example.exercise34_b;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBars {

	View layout;
	Activity act;
	SeekBar seekbar1;
	TextView textview1;
	SeekBar seekbar2;
	TextView textview2;
	SeekBar seekbar3;
	TextView textview3;
	
	//use when posting to servlet hosted on PI
	//String all_rooms_post_url = "http://10.0.0.8:8080/Exercise33-i/Post_Servlet";
	
	//use when posting to servlet on this laptop	
	//String all_rooms_post_url = "http://10.0.2.2:8080/Exercise33-m/Exercise33m_Servlet";	
	
	//use when the arduio name changes to ttyUSB1
	//String all_rooms_post_url = "http://10.0.0.8:8080/Exercise33-i_3_USB1/Post_Servlet";	
	
	//use this when arduino name is ttyUSB0
	String all_rooms_post_url = "http://10.0.0.8:8080/Exercise33-i_2/Post_Servlet";
	
	int room1_progress_value;
	int room2_progress_value;
	int room3_progress_value;
	String room1_id = "88";
	String room2_id = "77";
	String room3_id = "66";
	
	
	
	public SeekBars(Activity act) {
		System.out.println("[SeekBars Class] Constructor is Called");
		this.act = act;
		intialize_Everything();
		add_Listener();
	}
	
	public void intialize_Everything(){
		System.out.println("[SeekBars Class] intialize_Everything() is Called");
		LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.seekbars, null);
		
		seekbar1 = (SeekBar) layout.findViewById(R.id.seekBar1);
		textview1 = (TextView) layout.findViewById(R.id.textView1);
		seekbar2 = (SeekBar) layout.findViewById(R.id.seekBar2);
		textview2 = (TextView) layout.findViewById(R.id.textView2);
		seekbar3 = (SeekBar) layout.findViewById(R.id.seekBar3);
		textview3 = (TextView) layout.findViewById(R.id.textView3);
	}
	
	public void add_Listener(){
		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				System.out.println("[SeekBars Class] seekbar1.onStopTrackingTouch() is Called");
				new Post_SeekBar_Async(room1_id, room1_progress_value).execute(all_rooms_post_url);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				System.out.println("[SeekBars Class] seekbar1.onProgressChanged() is Called");
				room1_progress_value = progress;
				textview1.setText("Room1 Temp: " + progress);
			}
		});
	
		seekbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				System.out.println("[SeekBars Class] seekbar2.onStopTrackingTouch() is Called");
				new Post_SeekBar_Async(room2_id, room2_progress_value).execute(all_rooms_post_url);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				System.out.println("[SeekBars Class] seekbar2.onProgressChanged() is Called");
				room2_progress_value = progress;
				textview2.setText("Room2 Temp: " + progress);
			}
		});
	
		seekbar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				System.out.println("[SeekBars Class] seekbar3.onStopTrackingTouch() is Called");
				new Post_SeekBar_Async(room3_id, room3_progress_value).execute(all_rooms_post_url);
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				System.out.println("[SeekBars Class] seekbar3.onProgressChanged() is Called");
				room3_progress_value = progress;
				textview3.setText("Room3 Temp: " + progress);
			}
		});
	}
	
	public View getLayout() {
		return layout;
	}
	
}
