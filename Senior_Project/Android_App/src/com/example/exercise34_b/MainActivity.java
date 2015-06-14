/*******************************************************************************
 * Copyright (c) 2012 Evelina Vrabie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.example.exercise34_b;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends Activity {

	private final Random RAND = new Random();
	int noofsize = 3;
	//private XYPlot mySimpleXYPlot;
	
	public MainActivity() {
		System.out.println("[MainActivity Class] Constructor is Called");
		
		/*gives a null pointer error at line 40
		 * ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this,
				noofsize);
		ViewPager myPager = (ViewPager) findViewById(R.id.reviewpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);*/
	}
	
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("[MainActivity Class] onCreate() is Called");
		
		setContentView(R.layout.activity_main);
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this,
				noofsize);
		ViewPager myPager = (ViewPager) findViewById(R.id.reviewpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);
		
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		System.out.println("[MainActivity Class] onCreateOptionsMenu() is Called");
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
