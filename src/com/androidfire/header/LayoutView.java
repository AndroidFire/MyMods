package com.androidfire.header;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.androidfire.utill.SPAdapter;

public class LayoutView extends LinearLayout {




	BroadcastReceiver mShow;
	BroadcastReceiver mHide;
	BroadcastReceiver mBG;
	BroadcastReceiver Vis;

	SharedPreferences sharedPreferences;
	boolean mVisShPref;
	Editor mEditor;
	String AFStroage ="AndroidFire";
	String HeaderColor;
	String Default_Color = "#00000000";
	String mBGStrofSP = "BG_COLOR";
	String mVisBoolofSP = "Visibility";
	String HeaderShow = "com.androidfire.HEADER_HIDE";
	String HeaderHide = "com.androidfire.HEADER_SHOW";
	String HeaderBGCR = "com.androidfire.HEADER_CHANGE_COLOR";
	long lastClick = System.currentTimeMillis();
	int clicks;
	String message;
	String VisList = "com.androidfire.LAYOUT_SWITCH";

	// Same As ClockView
	

	/**
	 *@author AndroidFire
	 */
	public LayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		
			
		
		
		sharedPreferences = context.getSharedPreferences(SPAdapter.getId(),
				Context.MODE_PRIVATE);
		/*
		 * 
		 */
		
		setBackgroundColor(Color.parseColor(Default_Color));

		mVisShPref = sharedPreferences.getBoolean(mVisBoolofSP,true);
		HeaderColor = sharedPreferences.getString(mBGStrofSP, "00000000");
		
		if (mVisShPref==true) {
			setVisibility(View.VISIBLE);
		}
		else if (mVisShPref==false){
			setVisibility(View.GONE);
		}
		
		if (HeaderColor != "00000000") {
			setBackgroundColor(Color.parseColor(HeaderColor));
		}
		else {
			setBackgroundColor(Color.parseColor("#00000000"));
		}
		mShow = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.VISIBLE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisBoolofSP, true);
				mEditor.commit();
			}
		};
		mHide = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.GONE);

				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisBoolofSP, false);
				mEditor.commit();
			}
		};
		mBG = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
			  String BG = intent.getStringExtra("lvbgcolor");
          	  setBackgroundColor(Color.parseColor(BG));
          	 mEditor = sharedPreferences.edit();
			 mEditor.putString(mBGStrofSP, BG);
			 mEditor.commit();
			}
		};
		Vis = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				message = intent.getStringExtra("vis_of_lp");
				if ("Show".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(HeaderShow);
					getContext().sendBroadcast(i);
					
				}
				else if ("Hide".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(HeaderHide);
					getContext().sendBroadcast(i);
				}
			}
		};
		context.registerReceiver(mShow, new IntentFilter(HeaderShow));
		context.registerReceiver(mHide, new IntentFilter(HeaderHide));
		context.registerReceiver(mBG, new IntentFilter(HeaderBGCR));
		context.registerReceiver(Vis, new IntentFilter(VisList));

	}
	

}
