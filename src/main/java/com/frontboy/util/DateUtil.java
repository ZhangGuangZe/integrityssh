package com.frontboy.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化时间工具类
 */
public class DateUtil {
	/**
	 * 日期时间的格式转换，转换后，日期格式为yyyy-MM-dd HH:mm:ss
	 * @param datetime 时间格式
	 * @return 格式化后的日期时间
	 */
	public static String datetimeFormat(String datetime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = df.parse(datetime);
			String s = df.format(date);
			return s;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datetime;
	}

	/**
	 * 格式化时间字符串
	 * @param d 一个包含有完整时间信息的Date类型的对象
	 * @return 格式化的字符串，包含日期和时间 如："2015-5-2 12:6:23"
	 */
	public static String tranDate2datetime(Date d){
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = f.format(d);
		return s;
	}

	public static void main(String[] args){
		System.out.println(DateUtil.datetimeFormat("2018-4-11 9:04:23.234"));
	}
}
