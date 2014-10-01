package com.androidfire;


import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import com.androidfire.utill.AuthorName;
import com.androidfire.utill.SPAdapter;

@SuppressLint("WorldWriteableFiles")
public class Settings extends PreferenceActivity {
	SharedPreferences sharedPreferences;
	String color;
	ColorPickerPreference mHeader;
	ListPreference mVis;
	ColorPickerPreference mClockColor;
	ListPreference mVisClock;
	ColorPickerPreference mDayColor;
	ListPreference mDayVis;
	ColorPickerPreference mMoarColor;
	ListPreference mMoarVis;
	boolean mainVis;
	String mainBG;
	boolean ClockVis;
	String clockColor;
	boolean dayVis;
	String dayColor;
	boolean moarVis;
	String moarColor;
	PreferenceScreen mAuthor;
	
	/*
	 * Some Required Variable for Boolean String
	 * PreferenceScreen ListPreference ColorPickerPreference
	 * SharedPreferences
	 */
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_view);
        
        sharedPreferences = getSharedPreferences(SPAdapter.getId(),
        		Context.MODE_WORLD_READABLE |
        		Context.MODE_WORLD_WRITEABLE);
        /*
         * Get The Name of SharedPreferences to find
         */
	
	
        mainVis = sharedPreferences.getBoolean("Visibility", true);
        mainBG = sharedPreferences.getString("BG_COLOR", "null");
        ClockVis = sharedPreferences.getBoolean("VisibilityClock", true);
        clockColor = sharedPreferences.getString("ClockColor", "null");
        dayVis = sharedPreferences.getBoolean("VisibilityDay", true);
        dayColor = sharedPreferences.getString("ColorDay", "null");
        moarVis = sharedPreferences.getBoolean("VisibilityMoar", true);
        moarColor = sharedPreferences.getString("MoarColor", "null");
        
        /*
         * Get Value of Boolean and String from
         * sharedPreferences
         */
        ListPreference mVis = (ListPreference) findPreference("vis_af");
        
       ColorPickerPreference mHeader = (ColorPickerPreference) findPreference("backgroundColorheader");

       ListPreference mVisClock = (ListPreference) findPreference("vis_clcok");
       
       ColorPickerPreference mClockColor = (ColorPickerPreference) findPreference("clock_color");
       
       ListPreference mDayVis = (ListPreference) findPreference("vis_day");
       
       ColorPickerPreference mDayColor = (ColorPickerPreference) findPreference("day_color");
       
      ListPreference mMoarVis = (ListPreference) findPreference("vis_moar");
       
        ColorPickerPreference mMoarColor = (ColorPickerPreference) findPreference("moar_color");
       
        PreferenceScreen mAuthor = (PreferenceScreen) findPreference("author");
        
        /*
         * Finding Key from xml and set Values
         */
        mAuthor.setSummary(AuthorName.getAuthorName());
    /*
     * Adding Author Name to
     * PreferanceScreen
     */
        mAuthor.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
					String AuthorLink =  AuthorName.AuthorXdaLink();
					Uri mAuthor = Uri.parse(AuthorLink);
					final Intent click_me =  new Intent(Intent.ACTION_VIEW, mAuthor);
					click_me.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(click_me);
					return true;
            }
        });	        

      // If Press mAuthor Uri will open
       mHeader.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				color = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
				preference.setSummary(color);
				Intent i = new Intent();
				i.setAction("com.androidfire.HEADER_CHANGE_COLOR");
				i.putExtra("lvbgcolor",color.toString());
				sendBroadcast(i);
				return false;
			}

        });

       mVis.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String Vis = (String.valueOf(newValue));
				Intent i = new Intent();
				i.setAction("com.androidfire.LAYOUT_SWITCH");
				i.putExtra("vis_of_lp",Vis);
				sendBroadcast(i);
				return false;
			}
        });
       
       mClockColor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				color = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
  				preference.setSummary(color);
  				Intent i = new Intent();
  				i.setAction("com.androidfire.CLOCK_CHANGE_COLOR");
  				i.putExtra("clockcolor_view",color.toString());
  				sendBroadcast(i);
  				return false;
  			}

          });

        mVisClock.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				String Vis = (String.valueOf(newValue));
  				Intent i = new Intent();
  				i.setAction("com.androidfire.CLOCK_SWITCH");
  				i.putExtra("vis_of_clock",Vis);
  				sendBroadcast(i);
  				return false;
  			}

          });
        mDayColor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				color = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
  				preference.setSummary(color);
  				Intent i = new Intent();
  				i.setAction("com.androidfire.DAY_CHANGE_COLOR");
  				i.putExtra("day_color_view",color.toString());
  				sendBroadcast(i);
  				return false;
  			}

          });

        mDayVis.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				String Vis = (String.valueOf(newValue));
  				Intent i = new Intent();
  				i.setAction("com.androidfire.DAY_SWITCH");
  				i.putExtra("vis_of_day",Vis);
  				sendBroadcast(i);
  				return false;
  			}

          });
        mMoarColor.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				color = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
  				preference.setSummary(color);
  				Intent i = new Intent();
  				i.setAction("com.androidfire.MOAR_CHANGE_COLOR");
  				i.putExtra("moarcolor",color.toString());
  				sendBroadcast(i);
  				return false;
  			}

          });

        mMoarVis.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

  			@Override
  			public boolean onPreferenceChange(Preference preference, Object newValue) {
  				String Vis = (String.valueOf(newValue));
  				Intent i = new Intent();
  				i.setAction("com.androidfire.MOAR_SWITCH");
  				i.putExtra("vis_of_moar",Vis);
  				sendBroadcast(i);
				return false;
  			}

          });
        // Setting Values of ListPreferance and ColorPicker



        
	}
}
  	
