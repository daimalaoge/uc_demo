package com.ucomponent.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public class DateTools {
  /**
   * 昨天
   * @return
   */
	public static String getYesterday() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(5);
		c.set(5, day - 1);
		return dateFormater.format(c.getTime());
	}
	/**
	 * 之前几天
	 * @param inputdate
	 * @param day
	 * @return
	 */
	public static String getLastday(String inputdate, int day) {
		if (day < 0) {
			return inputdate;
		}
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date ddate = dateFormater.parse(inputdate);
			Calendar c = Calendar.getInstance();
			c.setTime(ddate);
			int iday = c.get(5);
			c.set(5, iday - day);
			return dateFormater.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 当前日期前几天
	 * @param day
	 * @return
	 */
	public static String getLastday(int day) {
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (day < 0) {
			return dateFormater.format(c.getTime());
		}		
		int iday = c.get(5);
		c.set(5, iday - day);
		return dateFormater.format(c.getTime());
	}
	/**
	 * 之后几天
	 * @param inputdate
	 * @param day
	 * @return
	 */
	public static String getAfterday(String inputdate, int day) {
		if (day < 0) {
			return inputdate;
		}
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date ddate = dateFormater.parse(inputdate);
			Calendar c = Calendar.getInstance();
			c.setTime(ddate);
			int iday = c.get(5);
			c.set(5, iday + day);
			return dateFormater.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 当前日期后几天
	 * @param day
	 * @return
	 */
	public static String getAfterday(int day) {
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (day < 0) {
			return dateFormater.format(c.getTime());
		}		
		int iday = c.get(5);
		c.set(5, iday + day);
		return dateFormater.format(c.getTime());
	}

	/**
	 * 比较日期大小
	 * date1 > date2 返回1
	 * date1 < date2 返回-1
	 * date1 = date2 返回0
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date dt1 = df.parse(date1);
      Date dt2 = df.parse(date2);
      if (dt1.getTime() > dt2.getTime()) {
      	//System.out.println("dt1 在dt2前");
        return 1;
      } else if (dt1.getTime() < dt2.getTime()) {
        //System.out.println("dt1在dt2后");
        return -1;
      } else {
        return 0;
      }
    } catch (Exception exception) {
    	exception.printStackTrace();
    }
    return 0;
	}
	public static String getAfterdayYY(int day) {
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (day < 0) {
			return dateFormater.format(c.getTime());
		}
		int iday = c.get(5);
		c.set(5, iday + day);
		return dateFormater.format(c.getTime());
	}
	/**
	 * 当前日期后几年
	 * @param year
	 * @return
	 */
	public static String getAfterYear(int year) {
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		return dateFormater.format(c.getTime());
	}

}
