package com.androidfire.header;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.androidfire.utill.SPAdapter;

public class ClockView extends TextView {
	Handler mHandler;
	Calendar mCalendar;
	String mHour;
	String mMin;
	BroadcastReceiver mShow;
	BroadcastReceiver mHide;
	BroadcastReceiver mChCr;
	BroadcastReceiver Vis;
	Editor mEditor;
	String textFont;
	SharedPreferences sharedPreferences;
	boolean mVisClock;
	String mVisClockosSP = "VisibilityClock";
	String mBGColorofSP = "ClockColor";
	String mBGColor;
	String ClockHide = "com.androidfire.CLOCK_HIDE";
	String ClockShow = "com.androidfire.CLOCK_SHOW";
	String ClockColor = "com.androidfire.CLOCK_CHANGE_COLOR";
	String ClockSwitch = "com.androidfire.CLOCK_SWITCH";
	SimpleDateFormat mH;
	SimpleDateFormat mM;
	SimpleDateFormat mA;
	String message;
	 /*
	  * Some Important Variable
	  */
	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
      
		
		sharedPreferences = context.getSharedPreferences(SPAdapter.getId(),
				Context.MODE_PRIVATE);
		/*
         * Get The Name of SharedPreferences to find
         */
		mVisClock = sharedPreferences.getBoolean(mVisClockosSP,true);
		mBGColor = sharedPreferences.getString(mBGColorofSP, "null");
		 /*
         * Get Value of Boolean and String from
         * sharedPreferences
         */
      final Handler h = new Handler();
       h.post(new Runnable() {
      @Override
      public void run() {
          updateTime();
          h.postDelayed(this, 1000);
      }
           }); 
       /*
        * Per Minutes it Will Update 
        */
		if (mVisClock == true) {
			setVisibility(View.VISIBLE);
		}
		else if (mVisClock == false) {
			setVisibility(View.GONE);
		}
		if (mBGColor != "null") {
			setTextColor(Color.parseColor(mBGColor));
		}
		else {
			setTextColor(Color.WHITE);
		}
		mShow = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.VISIBLE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisClockosSP, true);
				mEditor.commit();
			}
		};
		mHide = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.GONE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisClockosSP, false);
				mEditor.commit();
			}
		};
		mChCr = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
			  String BGClock = intent.getStringExtra("clockcolor_view");
          	  setTextColor(Color.parseColor(BGClock));
          	 mEditor = sharedPreferences.edit();
			 mEditor.putString(mBGColorofSP, BGClock);
			 mEditor.commit();
			}
		};
		Vis = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				message = intent.getStringExtra("vis_of_clock");
				if ("Show".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(ClockShow);
					getContext().sendBroadcast(i);
					
				}
				else if ("Hide".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(ClockHide);
					getContext().sendBroadcast(i);
				}
			}
		};
		context.registerReceiver(mShow, new IntentFilter(ClockShow));
		context.registerReceiver(mHide, new IntentFilter(ClockHide));
		context.registerReceiver(mChCr, new IntentFilter(ClockColor));
		context.registerReceiver(Vis, new IntentFilter(ClockSwitch));

			
	}

	/**
	 * Important BroadcastReceiver
	 */
	private void updateTime() {
		mCalendar = Calendar.getInstance();
		mH = new SimpleDateFormat ("h");
		mA = new SimpleDateFormat ("aa");
		mM = new SimpleDateFormat ("mm");
		
		mHour = mH.format(mCalendar.getTime());
		mMin = mM.format(mCalendar.getTime());
		setTextSize(45);
		setText(" "+mHour+":"+mMin);
		/*
		 * Add the Text
		 */
        
	}

}
