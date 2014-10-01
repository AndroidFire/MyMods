package com.androidfire.utill;

public class AuthorName {
	
	static String A = "A";
	static String N = "N";
	static String D = "D";
	static String R = "R";
	static String O = "O";
	static String I = "I";
	static String F = "F";
	static String E = "E";
	static String OutPut;
	static  String http = "http://";
	static String xda = "forum.xda-developers.com";
	static String slesh = "/";
	static String member = "member.php?u=";
	static String mem_id = "5930923";
	static String XDA_OutPut;
	
	public static String getAuthorName() {
		OutPut = A+N+D+R+O+I+D+F+I+R+E.toUpperCase();
		if (OutPut.startsWith(A)); {
			if (OutPut.endsWith(E));
			  if (OutPut.equalsIgnoreCase("androidfire")); {
				  return OutPut;
			  }
		}
			
	}
	public static String AuthorXdaLink() {
		if ( getAuthorName().equals("ANDROIDFIRE")) {
		return XDA_OutPut = http+xda+slesh+member+mem_id;
		}
		return A;
		
	}
	
}
