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

public class MoarView extends TextView {
	Calendar mCalendar;
	SimpleDateFormat mMonth;
	String mSMonth;
	SimpleDateFormat mYear;
	int mSYear;
	SharedPreferences sharedPreferences;
	boolean mVisMoar;
	String mMoarColor;
	String mVisofSP = "VisibilityMoar";
	String mColorMoarofSP = "MoarColor";
	BroadcastReceiver mShow;
	BroadcastReceiver mHide;
	BroadcastReceiver mBGColor;
	BroadcastReceiver Vis;
	Editor mEditor;
	String MoarShow = "com.androidfire.MOAR_SHOW";
	String MoarHide = "com.androidfire.MOAR_HIDE";
	String MoarColor = "com.androidfire.MOAR_CHANGE_COLOR";
	String MoarSwitch = "com.androidfire.MOAR_SWITCH";
	String message;

	// Same As ClockView
	
	public MoarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 final Handler h = new Handler();
		    h.post(new Runnable() {
		        @Override
		        public void run() {
		            updateMoar();
		            h.postDelayed(this, 1000);
		        }

				
		    }); 
	
		sharedPreferences = context.getSharedPreferences(SPAdapter.getId(),
				Context.MODE_PRIVATE);
		mVisMoar = sharedPreferences.getBoolean(mVisofSP, true);
		mMoarColor = sharedPreferences.getString(mColorMoarofSP, "null");
		
		if (mVisMoar == true) {
			setVisibility(View.VISIBLE);
		}
		else if (mVisMoar == false) {
			setVisibility(View.GONE);
		}
		
		if (mMoarColor != "null") {
			setTextColor(Color.parseColor(mMoarColor));
		}
		else {
			setTextColor(Color.WHITE);
		}

		mShow = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				setVisibility(View.VISIBLE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisofSP, true);
				mEditor.commit();
			}
		};
		mHide = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
		setVisibility(View.GONE);
				mEditor = sharedPreferences.edit();
				mEditor.putBoolean(mVisofSP, false);
				mEditor.commit();
			}
		};
		mBGColor = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				 String BGMoar = intent.getStringExtra("moarcolor");
	          	 setTextColor(Color.parseColor(BGMoar));
	          	 mEditor = sharedPreferences.edit();
				 mEditor.putString(mColorMoarofSP, BGMoar);
				 mEditor.commit();
			}
		};
		Vis = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				message = intent.getStringExtra("vis_of_moar");
				if ("Show".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(MoarShow);
					getContext().sendBroadcast(i);
					
				}
				else if ("Hide".equalsIgnoreCase(message)) {
					Intent i = new Intent();
					i.setAction(MoarHide);
					getContext().sendBroadcast(i);
				}
			}
		};

		context.registerReceiver(mShow, new IntentFilter(MoarShow));
		context.registerReceiver(mHide, new IntentFilter(MoarHide));
		context.registerReceiver(mBGColor, new IntentFilter(MoarColor));
		context.registerReceiver(Vis, new IntentFilter(MoarSwitch));
	}


	public void updateMoar() {
		mCalendar = Calendar.getInstance();
		mMonth = new SimpleDateFormat ("MMMM");
		mSMonth = mMonth.format(mCalendar.getTime());
		mCalendar = Calendar.getInstance();
		mSYear = mCalendar.get(Calendar.YEAR);
		setText("  "+mSMonth+" "+","+" "+mSYear);
		
		
		
	}

}
