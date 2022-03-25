package com.group.projectwork.utility;

import java.util.Random;

public class StringUtils {
	
	private StringUtils() {}
	
	private static final String chars = 
			"abcdefghilmnopqrstuvzxwykj"
			+ "ABCDEFGHILMNOPQRSTUVZWXYJK"
			+ "0123456789"; 
	
	public static String random(int lenght) {
		StringBuilder sb = new StringBuilder(lenght);
		var nChars = chars.length();
		var rnd = new Random(System.currentTimeMillis());
		for(int i = 0; i<lenght; i++)
			sb.append(chars.charAt(rnd.nextInt(nChars)));
		return sb.toString();
	}
	
	public static String upTo(String string, int lenght) {
		return upTo(string, 0, lenght);
	}
	
	public static String upTo(String string, int startIndex, int lenght) {
		return string.substring(startIndex, Math.min(lenght, string.length()-startIndex));
	}
}
