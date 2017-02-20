package com.zlw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String getDateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println("data:" + df.format(new Date()));
		return df.format(new Date());
	}

	public static Date getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println("data:" + df.format(new Date()));
		return (new Date());
	}
}
