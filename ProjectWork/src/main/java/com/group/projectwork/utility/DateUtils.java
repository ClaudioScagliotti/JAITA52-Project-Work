package com.group.projectwork.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	
	private DateUtils() {}
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	 
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
	private static final SimpleDateFormat DATE_TIME_INPUT_FORM_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	
	public static java.util.Date parseFromInput(String date) {
	    try {
	        return DATE_TIME_INPUT_FORM_FORMAT.parse(date);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	
	public static java.util.Date parseDate(String date) {
	    try {
	        return DATE_FORMAT.parse(date);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	 
	public static java.util.Date parseTimestamp(String timestamp) {
	    try {
	        return DATE_TIME_FORMAT.parse(timestamp);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
