package com.androidfire.utill;

import android.content.Context;
import android.content.SharedPreferences;

public class Constatnt {
	
	static SharedPreferences sharedPreferences;
	static Context context;
	static String AF;
	
	public Constatnt(String[] args0) {
		if (AF.equals(null)) {
			AF="AndroidFire";
		}
	}

}
