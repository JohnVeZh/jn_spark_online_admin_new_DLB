package com.easecom.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间工具类
 * @author Administrator
 *
 */
public class DateTimeUtils {
	/**
	 * 获取当前周的周一在月份中的日期
	 * @return
	 */
	public static String getCurrentWeekEndDate(){
		String weekEndDateString = "";
		
		try {
			Calendar cl = Calendar.getInstance(Locale.CHINA);
			int dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
			int daySpace = (7-dayOfWeek);
			if(daySpace == 6){
				daySpace = 0;
			}else{
				daySpace += 1;
			}
			
			cl.add(Calendar.DAY_OF_MONTH, daySpace);
			
			int year = cl.get(Calendar.YEAR);
			int month = cl.get(Calendar.MONTH)+1;
			int day = cl.get(Calendar.DAY_OF_MONTH);
			
			weekEndDateString = year + "-" + 
								(month<10 ? "0"+month : month) + "-" + 
								(day<10 ? "0"+day : day) + " " + "23:59:59";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weekEndDateString;
	}

	/**
	 * 获取当前周的周末在月份中的日期
	 */
	public static String getCurrentWeekStartDate(){
		String weekStartDateString = "";
		
		try {
			Calendar cl = Calendar.getInstance(Locale.CHINA);
			
			int dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
			int daySpace = dayOfWeek - 2;
			if(daySpace == -1){
				daySpace=-6;
			}else{
				daySpace = daySpace-2*daySpace;
			}
			
			cl.add(Calendar.DAY_OF_MONTH, daySpace);
			
			int year = cl.get(Calendar.YEAR);
			int month = cl.get(Calendar.MONTH)+1;
			int day = cl.get(Calendar.DAY_OF_MONTH);
			
			weekStartDateString = year + "-" + 
								  (month<10 ? "0"+month : month) + "-" + 
								  (day<10 ? "0"+day : day) + " " + "00:00:00";
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return weekStartDateString;
	}
	
	/**
	 * 当月开始的时间
	 * @return
	 */
	public static String getCurrentMonthStartDate(){
		String monthStartDateString = "";
		
		try {
			Calendar cl = Calendar.getInstance(Locale.CHINA);
			int year = cl.get(Calendar.YEAR);
			int month = cl.get(Calendar.MONTH)+1;
			
			monthStartDateString = year + "-" + 
								   (month<10 ? "0"+month : month) + "-" + "01 00:00:00";
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return monthStartDateString;
	}
	
	/**
	 * 当月结束的时间
	 * @return
	 */
	public static String getCurrentMonthEndDate(){
		String monthEndDateString = "";
		
		Calendar cl = Calendar.getInstance(Locale.CHINA);
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH)+1;
		
		monthEndDateString = year + "-" + 
							(month<10 ? "0"+month : month) + "-" + 
							(getMonthDays(year , month) < 10 ? "0"+getMonthDays(year , month) 
															: getMonthDays(year , month)) + " 23:59:59";
		
		return monthEndDateString;
	}
	
	/**
	 * 判断指定月份的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year , int month){
		int days = 0;
		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
			default:
				break;
		}
		
		if(month == 2){
			if(isLeapYear(year)){
				days = 29;
			}else{
				days = 28;
			}
		}
		
		return days;
	}
	/**
	 * 判断是否是闰年
	 */
	public static boolean isLeapYear(Integer year){
		boolean is = false;
		
		if(year % 100 == 0){
			if(year % 400 == 0){
				is = true;
			}
		}else if(year % 4 ==0){
			is = true;
		}
		
		return is;
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static int getCurrentYear(){
		Calendar cl = Calendar.getInstance(Locale.CHINA);
		int year = cl.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 根据时间获取日期字符串
	 * @param time 长整形格式的 毫秒数
	 * @return xxxx-xx-xx xx:xx:xx
	 * @author 孙晓宇
	 * 2013-6-15 下午04:02:56
	 */
	public static String getDateStrFromTime(long time){
		String dateStr = "";
		try {
			Date date = new Date(time);
			dateStr = DateUtils.format(date, DateUtils.formatStr_yyyyMMddHHmmss);
		} catch (Exception e) {
			return null;
		}
		return dateStr;
	}
	
	
	/**
	 * 根据时间获取long类型数据
	 * @author 孙晓宇
	 * Jul 2, 20133:45:32 PM
	 * @param time 例如：yyyy-MM-dd HH:mm:ss
	 * @return 
	 * @throws ParseException
	 */
	public static long getDateLongFromTime(String time) throws ParseException{
		if(time==null||"".equals(time)||""==time)
			return 0l;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String temp = time;
			Date date = sdf.parse(temp);
			return date.getTime();
		} catch (Exception e) {
			return 0l;
		}
	}
	
	/**
	 * 获取long时间
	 * @param time
	 * @return
	 * @throws ParseException
	 * @author 李来源
	 * Jul 4, 20132:30:37 PM
	 */
	public static String getDateSLongFromTime(String time){
		if(time==null||"".equals(time)||""==time)
			return null;
		try {
			String[] strs =  time.split("\\s+");
			String str = strs[1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String temp = time;
			Date date = sdf.parse(temp);
			return date.getTime()+"";
		} catch (Exception e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String temp = time;
				Date date = sdf.parse(temp);
				return date.getTime()+"";
				
			} catch (Exception ex) {
				return null;
			}
		}
	}
	
	
	/**
	 * 获取当前时间毫秒数
	 * @return
	 * @author author 周志君
	 * 2013-6-15 下午04:12:59
	 */
	public static long getCurrTime(){
		return new Date().getTime();
	}
	
	/**
	 * 获取当前时间
	 * @author 孙晓宇
	 * Aug 2, 201310:33:45 AM
	 * @return
	 */
	public static String getStrByTime(){
		String mDateTime ="";
		SimpleDateFormat formatter = new SimpleDateFormat(
		"yyyyMMddHHmmss");
		mDateTime = formatter.format(new Date());
		return mDateTime;
	}
	public static void main(String args[]){
//		System.out.println(getDateSLongFromTime("2013-10-03 11:47:28"));
		
		System.out.println(getDateStrFromTime(1392797704130L));

	}
}
