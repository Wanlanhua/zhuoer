package com.zhuoer.careDevice.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtilToSql {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date getTimestampUtilDate(Timestamp ts) {
		
		String time = sdf.format(ts);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Timestamp getTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public static String getSdfTime(Date date) {
		return sdf.format(date);
	}
	
	public static Date getStringUtilDate(String sdate) {
		Date date = null;
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
}
