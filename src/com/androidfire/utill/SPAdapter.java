package com.androidfire.utill;

import android.os.Build;

public class SPAdapter {

	
	
	public static String fd;
	
	public static String sd;
	
	public static String td;
	
	static String ftd;
	
	public static String OutPut;
	
	
	

	public static String getId() {
		if (firstDigit() != null) {
			if (secondDigit() !=  null) {
				if (thirdDigit() != null) {
					if (fourthDigit() != null) {
						OutPut = fourthDigit()+firstDigit()+secondDigit()+thirdDigit();
					}
				}
			}
		}
		else {
			OutPut = "Unknown";
		}
		return OutPut;
		
	}
	
	public static String firstDigit() {
		if (Build.VERSION.RELEASE !=  null) {
			fd = Build.VERSION.RELEASE;

		}
		
		
		return fd;
	}
	
	public static String secondDigit() {
		if (Build.BRAND.equalsIgnoreCase("samsung")) {
			sd = "SG";
		}
		else if (Build.BRAND.equalsIgnoreCase("htc")) {
			sd = "HC";
		}
		else if (Build.BRAND.equalsIgnoreCase("lg")) {
			sd = "LG";
		}
		else if (Build.BRAND.equalsIgnoreCase("sony")) {
			sd = "SY";
		}
		else if (Build.BRAND.equalsIgnoreCase("asus")) {
			sd = "AS";
		}
		else if (Build.BRAND.equalsIgnoreCase("micromax")) {
			sd = "MX";
		}
		else {
			sd = "?";
		}
		
		return sd;
		
	}
	public static String thirdDigit() {
		td = Build.USER.toUpperCase();
		return td;
		
	}
	public static String fourthDigit() {
		if (Build.DEVICE != null) {
			ftd = Build.DEVICE;
		}
		return ftd;
		
	}
}
