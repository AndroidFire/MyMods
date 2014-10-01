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

public class DayView extends TextView {
	Calendar mCalendar;
	SimpleDateFormat mDay;
	String mSDay;
	SharedPreferences sharedPreferences;
	boolean mVisDay;
	String DayColor;
	String DayVisofSP = "VisibilityDay";
	String DayColorofSP = "ColorDay";
	BroadcastReceiver mHide;
	BroadcastReceiver mShow;
	BroadcastReceiver mDayColor;
	BroadcastReceiver Vis;
	Editor mEditor;
	String DayShow = "com.androidfire.DAY_SHOW";
	String DayHide = "com.androidfire.DAY_HIDE";
	String BGColor = "com.androidfire.DAY_CHANGE_COLOR";
	String DaySwitch = "com.androidfire.DAY_SWITCH";
	String message;
	
	// Same As ClockView
	
	public DayView(final Context context, AttributeSet attrs) {
		
		super(context, attrs);
		
		final Handler h = new Handler();
	    h.post(new Runnable() {
	        @Override
	        public void run() {
	            updateDay(context);
	            h.postDelayed(this, 1000);
	        }

			
	    }); 	
	
		
		sharedPreferences = context.getSharedPreferences(SPAdapter.getId(),
				Context.MODE_PRIVATE);
		mVisDay = sharedPreferences.getBoolean(DayVisofSP,true);
		DayColor = sharedPreferences.getString(DayColorofSP, "null");
		if (mVisDay == true) {
			setVisibility(View.VISIBLE);
		}
		else if (mVisDay == false) {
			setVisibility(View.GONE);
		}
		if ("null" != DayColor) {
			setTextColor(Color.parseColor(DayColor));
		}
		else {
			setTextColor(Color.WHITE);
		}
		
		mShow = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.VISIBLE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(DayVisofSP, true);
				mEditor.commit();
			}
		};
		mHide = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.GONE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(DayVisofSP, false);
				mEditor.commit();
			}
		};
				Vis = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				message = intent.getStringExtra("vis_of_day");
				if ("Show".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(DayShow);
					getContext().sendBroadcast(i);
					
				}
				else if ("Hide".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(DayHide);
					getContext().sendBroadcast(i);
				}
			}
		};
		mDayColor = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
			  String BGClock = intent.getStringExtra("day_color_view");
          	  setTextColor(Color.parseColor(BGClock));
          	 mEditor = sharedPreferences.edit();
			 mEditor.putString(DayColorofSP, BGClock);
			 mEditor.commit();
			}
		};
		context.registerReceiver(mShow, new IntentFilter(DayShow));
		context.registerReceiver(mHide, new IntentFilter(DayHide));
		context.registerReceiver(mDayColor, new IntentFilter(BGColor));
		context.registerReceiver(Vis, new IntentFilter(DaySwitch));
	}
	private void updateDay(Context context) {
		mCalendar = Calendar.getInstance();
		 mDay = new SimpleDateFormat("EEEEEEEE");
		 mSDay = mDay.format(mCalendar.getTime());
		 setText("  "+mSDay.toUpperCase());
	}

}