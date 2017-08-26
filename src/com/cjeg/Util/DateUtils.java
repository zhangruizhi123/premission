package com.cjeg.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年2月19日 上午10:47:06
 *
 */
public class DateUtils {
	
	
	
	public static String YMDPATTERN="yyyy-MM-dd";
	/**
	 * 获取当前的日期
	 * @return
	 */
	public static Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 对日期字符串格式化
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date strTODate(String date,String pattern){
		Date d=null;
		try{
			SimpleDateFormat format=new SimpleDateFormat(pattern);
			d=format.parse(date);
		}catch(Exception e){
			d= null;
		}
		return d;
	}
	
	
}
