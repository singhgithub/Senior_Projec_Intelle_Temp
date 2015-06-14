package com.example.exercise34_b;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.androidplot.xy.XYPlot;

public class ViewPagerAdapter extends PagerAdapter {
	int size;
	Activity act;
	View layout;
	TextView pagenumber;
	Button click;
	private XYPlot mySimpleXYPlot;
	private GaugeView mGaugeView1;
	private GaugeView mGaugeView2;
	private GaugeView mGaugeView3;
	private Button button_id;
	private final Random RAND = new Random();
	String gauge_url = "http://10.0.0.8:8080/Exercise33-i/All_Rooms_Data_Servlet";
	String room1_graph_url = "http://10.0.0.8:8080/Exercise33-i/Room1_Servlet";
	String room2_graph_url = "http://10.0.0.8:8080/Exercise33-i/Room2_Servlet";
	String room3_graph_url = "http://10.0.0.8:8080/Exercise33-i/Room3_Servlet";
	
	public ViewPagerAdapter(){
		System.out.println("[ViewPagerAdapter Class] 1st Constructor is Called");
	}
	
	public ViewPagerAdapter(MainActivity mainActivity, int noofsize) {
		System.out.println("[ViewPagerAdapter Class] 2nd Constructor is Called");
		
		size = noofsize;
		act = mainActivity;
		
		//can't do this 
		//static ViewPagerAdapter view_pa_obj = new ViewPagerAdapter();
	}

	@Override
	public int getCount() {
		//System.out.println("[ViewPagerAdapter Class] getCount() is Called");
		return size;
		//return 2;
	}

	//this method returns a reference to the layout xml file, by doing inflatation, and storing it in a View obj
	@Override
	public Object instantiateItem(View container, int position) {
		System.out.println("[ViewPagerAdapter Class] instantiateItem() is Called");
		
		System.out.println("[ViewPagerAdapter Class] instantiateItem() position: " +position);
		
		if (position == 0) {
			LayoutInflater inflater = (LayoutInflater) act
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.gauge, null);
			draw_The_Gauges();
			
		}else if(position == 1){
			LayoutInflater inflater = (LayoutInflater) act
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.graph, null);
			draw_The_Graph();	
		}else if (position == 2){
			SeekBars seekbar_obj = new SeekBars(act);
			((ViewPager) container).addView(seekbar_obj.getLayout(), 0);		//for params if i chnage to postion, no effect
			return seekbar_obj.getLayout();
			
		}
		
		/*pagenumber = (TextView) layout.findViewById(R.id.pagenumber);
		int pagenumberTxt=position + 1;
		pagenumber.setText("Now your in Page No  " + pagenumberTxt);*/
		
		((ViewPager) container).addView(layout, 0);		//for params if i chnage to postion, no effect
		return layout;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		System.out.println("[ViewPagerAdapter Class] instantiateItem() is Called");
		
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		//System.out.println("[ViewPagerAdapter Class] isViewFromObject() is Called");
		
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		System.out.println("[ViewPagerAdapter Class] saveState() is Called");
		
		return null;
	}

	public void draw_The_Graph(){
		System.out.println("[ViewPagerAdapter Class] draw_The_Graph() is Called");
		
		// initialize our XYPlot reference:
        mySimpleXYPlot = (XYPlot) layout.findViewById(R.id.mySimpleXYPlot);
 
        //this sets out a chain reaction for room2,3
        new Room1_Graph_Async(mySimpleXYPlot).execute(room1_graph_url);
        
        //this causes problem, the same problem i faced in the web app, where if not syncrous, i get 0 or servlet not found error
        //new Room1_Graph_Async(mySimpleXYPlot).execute(room1_graph_url);
        //new Room2_Graph_Async(mySimpleXYPlot).execute(room2_graph_url);
        
        /*//this seems not to be working for some reason 
        button_id = (Button) layout.findViewById(R.id.button_id);
	    
        button_id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("[ViewPagerAdapter Class] You pressed the Button");
				System.out.println("[ViewPagerAdapter Class] onClick() is Called");
				
				new Room1_Graph_Async(mySimpleXYPlot).execute(room1_graph_url);
				
			}
		});*/
        
	}
	
	/*//when i click on the button diplay the graphs
	public void populate_Graph_Method(View v){
		System.out.println("[ViewPagerAdapter Class] You pressed the Button");
		System.out.println("[ViewPagerAdapter Class] populate_Graph_method() is Called");
		
		new Room1_Graph_Async(mySimpleXYPlot).execute(room1_graph_url);
	}*/
	    

	public XYPlot getMySimpleXYPlot() {
		return mySimpleXYPlot;
	}

	public void draw_The_Gauges(){
		System.out.println("[ViewPagerAdapter Class] draw_The_Gauges() is Called");
		
		//Guages stuff
		mGaugeView1 = (GaugeView) layout.findViewById(R.id.gauge_view1);
		mGaugeView2 = (GaugeView) layout.findViewById(R.id.gauge_view2);
		mGaugeView3 = (GaugeView) layout.findViewById(R.id.gauge_view3);
		mTimer.start();		//if i uncomment thid i get a nullpointer error 
	
	}
	
	private final CountDownTimer mTimer = new CountDownTimer(50000, 10000) {
		
		@Override
		public void onTick(final long millisUntilFinished) {
			System.out.println("[ViewPagerAdapter Class] onTick() is Called");
			
			/*mGaugeView1.setTargetValue(RAND.nextInt(101));
			mGaugeView2.setTargetValue(RAND.nextInt(101));
			mGaugeView3.setTargetValue(RAND.nextInt(101));*/
			
			new Gauge_Async(mGaugeView1, mGaugeView2, mGaugeView3).execute(gauge_url);
		}

		@Override
		public void onFinish() {
			System.out.println("[MainActivity Class] onFinish() is Called");
		}
	};
	
}
