/**
 * e-tap
 * 
 * Copyright (c) 2014 Shanghai TAP Software Co. Ltd.
 * 
 * 日期常用工具方法类。
 * 
 */
package com.tap.word.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期常用工具方法类
 */
public final class DateHelper {

	/**
	 * "YYYY-MM-DD"日期格式
	 */
	public static final String DATE_YYYYMMMMDD = "yyyy-MM-dd";

	/**
	 * "HH:MM:SS"时间格式
	 */
	public static final String TIME_HHCMMCSS = "HH:mm:ss";

	/**
	 * "HH:MM"时间格式
	 */
	public static final String TIME_HHCMM = "HH:MM";

	/**
	 * "HH:MM:SS AMPM"时间格式
	 */
	public static final String TIME_HHCMMCSSAMPM = "HH:MM:SS AMPM";

	private static SimpleDateFormat m_dtFormater = null;

	/**
	 * 获取系统当前的整数时间戳
	 * @return
	 */
	public static final long getCurrentTimeMillis(){
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
	 * 
	 * @return 当前日期时间
	 */
	public static String getCurDateTimeStr() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String str = timestamp.toString();
		return new StringBuffer().append(datetime).toString();
	}

	public static int getYear(String source) {
		Calendar c = Calendar.getInstance();
		Date t = parseDate(source);
		c.setTime(t);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
	 * 
	 * @return 当前日期时间
	 */
	public static String getCurDateTime() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		return (timestamp.toString()).substring(0, 19);
	}

	/**
	 * 获取当前日期。YYYY-MM-DD
	 * 
	 * @return 当前日期
	 */
	public static String getCurrentDate() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(0, 4) + "-" + (timestamp.toString()).substring(5, 7) + "-" + (timestamp.toString()).substring(8, 10);
		return currentdate;
	}

	/**
	 * 将Date类型转换为YYYY-MM-DD格式字符串。
	 * 
	 * @param newdate
	 *            Date类型日期
	 * @return String类型日期
	 */
	public static String getDate(Date newdate) {
		if (newdate == null)
			return "";
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(0, 4) + "-" + (timestamp.toString()).substring(5, 7) + "-" + (timestamp.toString()).substring(8, 10);
		return currentdate;

	}

	/**
	 * 获取当前年份。YYYY
	 * 
	 * @return 当前年份
	 */
	public static String getCurrentYear() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentyear = (timestamp.toString()).substring(0, 4);
		return currentyear;
	}

	/**
	 * 获取中文格式的当前年月日，星期，如2017年10月31日 星期二
	 * @return
	 */
	public static String getDateWeekCN(String currentDate ){
		String[] dates=currentDate.split("-");
		String result=dates[0]+"年"+dates[1]+"月"+dates[2]+"日";
		String weekStr="";
		int week=getDayOfWeek(currentDate);
		switch (week){
			case 1:
				weekStr="星期日";
				break;
			case 2:
				weekStr="星期一";
				break;
			case 3:
				weekStr="星期二";
				break;
			case 4:
				weekStr="星期三";
				break;
			case 5:
				weekStr="星期四";
				break;
			case 6:
				weekStr="星期五";
				break;
			case 7:
				weekStr="星期六";
				break;
		}
		return result+" "+weekStr;
	}

	/**
	 * 获取星期几
	 * @param week
	 * @return
	 */
	public static String getDateOfWeekZH(int week){
		String weekStr = "";
		switch (week){
			case 1:
				weekStr = "星期日";
				break;
			case 2:
				weekStr = "星期一";
				break;
			case 3:
				weekStr = "星期二";
				break;
			case 4:
				weekStr = "星期三";
				break;
			case 5:
				weekStr = "星期四";
				break;
			case 6:
				weekStr = "星期五";
				break;
			case 7:
				weekStr = "星期六";
				break;
		}
		return weekStr;
	}


	/**
	 * 获取开始时间和结束时间之间的天数。
	 * 
	 * @param datebegin
	 *            开始日期
	 * @param dateend
	 *            结束日期
	 * @return 天数
	 */
	public static long getDisDays(String datebegin, String dateend) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateBegin = sdf.parse(datebegin);
			Date dateEnd = sdf.parse(dateend);
			return (dateEnd.getTime() - dateBegin.getTime()) / (3600 * 24 * 1000) + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取当前时间(HH:MM:SS)。
	 * 
	 * @return 当前时间
	 */
	public static String getCurrentTime() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currenttime = (timestamp.toString()).substring(11, 13) + ":" + (timestamp.toString()).substring(14, 16) + ":" + (timestamp.toString()).substring(17, 19);
		return currenttime;
	}

	/**
	 * 计算两个日期之间间隔天数方法。
	 * 
	 * @param d1
	 *            开始日期.
	 * @param d2
	 *            结束日期
	 * @return 间隔天数
	 */
	public static long getDaysBetween(Calendar d1, Calendar d2) {
		return (d1.getTime().getTime() - d2.getTime().getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * 计算两个日期之间间隔(d1-d2)天数。
	 * 
	 * @param d1
	 *            开始日期(yyyy-MM-dd)
	 * @param d2
	 *            结束日期(yyyy-MM-dd)
	 * @return 间隔天数
	 */
	public static long getDaysBetween(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = sdf.parse(d1);
			Date dt2 = sdf.parse(d2);
			return (dt1.getTime() - dt2.getTime()) / (3600 * 24 * 1000);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 计算两个日期之间的时间间隔(d1-d2)天数，可选择是否计算工作日。
	 * 
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            结束日期
	 * @param onlyWorkDay
	 *            是否只计算工作日
	 * @return 间隔天数
	 */
	public static long getDaysBetween(String d1, String d2, boolean onlyWorkDay) {
		if (!onlyWorkDay) {
			return getDaysBetween(d1, d2);
		} else {
			long days = 0;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dt1 = sdf.parse(d1);
				Date dt2 = sdf.parse(d2);
				days = (dt1.getTime() - dt2.getTime()) / (3600 * 24 * 1000);
				for (calendar.setTime(dt1); calendar.getTime().after(dt2); calendar.add(Calendar.DAY_OF_YEAR, -1)) {
					int week = calendar.get(Calendar.DAY_OF_WEEK);
					if (week == Calendar.SUNDAY || week == Calendar.SATURDAY) {
						days--;
					}
				}
				if (days < 0) {
					days = 0;
				}
			} catch (Exception e) {
			}
			return days;
		}
	}

	/**
	 * 判断日期是否为工作日(周六和周日为非工作日)。
	 * 
	 * @param date
	 *            日期
	 * @return 是否为工作日
	 */
	public static boolean isWorkDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == Calendar.SUNDAY || week == Calendar.SATURDAY) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 计算两个时间之间的间隔。单位：分钟(minutes)。
	 * 
	 * @param s1
	 *            开始日期(yyyy-MM-dd/HH:mm:ss)
	 * @param s2
	 *            结束日期(yyyy-MM-dd/HH:mm:ss)
	 * @return 间隔分钟
	 */
	public static long getMinutesBetween(String s1, String s2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		try {
			Date dt1 = sdf.parse(s1);
			Date dt2 = sdf.parse(s2);
			return (dt1.getTime() - dt2.getTime()) / (60 * 1000);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 返回两个日期间隔。
	 * <p>
	 * 按月分隔的list，list里面每个月一个map,第一天begindate，最后一天enddate
	 * </p>
	 * 
	 * @param d1
	 *            开始日期(yyyy-MM-dd)
	 * @param d2
	 *            结束日期
	 * @return 按月分隔的List
	 */
	public static List<HashMap> getDateBetween(String d1, String d2) {
		ArrayList<HashMap> list = new ArrayList<HashMap>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(sdf.parse(d1));
			cal2.setTime(sdf.parse(d2));

			int monthnum = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
			for (int i = 0; i < monthnum; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("begindate", sdf.format(cal1.getTime()));
				cal1.add(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE) - cal1.get(Calendar.DATE));
				map.put("enddate", sdf.format(cal1.getTime()));
				list.add(map);
				cal1.add(Calendar.MONTH, 1);
				cal1.add(Calendar.DATE, 1 - cal1.get(Calendar.DATE));
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("begindate", sdf.format(cal1.getTime()));
			map.put("enddate", d2);
			list.add(map);
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	/**
	 * 返回两个时间段相交的天数。
	 * 
	 * @param b1
	 *            开始时间段开始日期(yyyy-MM-dd)
	 * @param e1
	 *            开始时间段结束日期
	 * @param b2
	 *            结束时间段开始日期
	 * @param e2
	 *            结束时间段结束日期
	 * @return 间隔天数
	 */
	public static long getDays(String b1, String e1, String b2, String e2) {
		long ret = 0;
		String begindate = "";
		String enddate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar begin1 = Calendar.getInstance();
			Calendar end1 = Calendar.getInstance();
			Calendar begin2 = Calendar.getInstance();
			Calendar end2 = Calendar.getInstance();
			begin1.setTime(sdf.parse(b1));
			end1.setTime(sdf.parse(e1));
			begin2.setTime(sdf.parse(b2));
			end2.setTime(sdf.parse(e2));
			// 时间段不相交
			if ((begin2.getTime().getTime() > end1.getTime().getTime() && end2.getTime().getTime() > end1.getTime().getTime())
					|| (begin2.getTime().getTime() < begin1.getTime().getTime() && end2.getTime().getTime() < begin1.getTime().getTime())) {
				// System.out.println("b"+ret);
				return ret;
			}

			if (begin2.getTime().getTime() >= begin1.getTime().getTime()) {
				begindate = sdf.format(begin2.getTime());
			} else {
				begindate = sdf.format(begin1.getTime());
			}
			if (end2.getTime().getTime() >= end1.getTime().getTime()) {
				enddate = sdf.format(end1.getTime());
			} else {
				enddate = sdf.format(end2.getTime());
			}

			if (!begindate.equals("") && !enddate.equals("")) {
				ret = getDisDays(begindate, enddate);
			}
		} catch (Exception e) {

		}
		// System.out.println("e"+ret);
		return ret;
	}

	/**
	 * 比较两个日期d1<d2。
	 * 
	 * @param d1
	 *            开始日期(yyyy-MM-dd)
	 * @param d2
	 *            结束日期
	 * @return d1是否小于d2
	 */
	public static boolean after(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = sdf.parse(d1);
			Date dt2 = sdf.parse(d2);
			return dt1.getTime() < dt2.getTime();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 移动日期。
	 * 
	 * @param date
	 *            原日期(yyyy-MM-dd)
	 * @param len
	 *            移动天数
	 * @return 移动后日期
	 */
	public static String dayMove(String date, int len) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.DATE, len);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 移动日期。
	 * 
	 * @param date
	 *            原日期(yyyy-MM-dd)
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param honr
	 *            小时
	 * @param mintues
	 *            分钟
	 * @param second
	 *            秒
	 * @return 移动后日期
	 */
	public static String dayMoveDateTime(String date, int year, int month, int day, int honr, int mintues, int second) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.YEAR, year);
			cal.add(Calendar.MONTH, month);
			cal.add(Calendar.DATE, day);
			cal.add(Calendar.HOUR, honr);
			cal.add(Calendar.MINUTE, mintues);
			cal.add(Calendar.SECOND, second);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 移动日期。
	 * 
	 * @param date
	 *            原日期(yyyy-MM-dd)
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param honr
	 *            小时
	 * @param mintues
	 *            分钟
	 * @param second
	 *            秒
	 * @return 移动后日期
	 */
	public static String dayMoveDateTime(String date, String dateformat, int year, int month, int day, int honr, int mintues, int second) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.YEAR, year);
			cal.add(Calendar.MONTH, month);
			cal.add(Calendar.DATE, day);
			cal.add(Calendar.HOUR, honr);
			cal.add(Calendar.MINUTE, mintues);
			cal.add(Calendar.SECOND, second);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 获取日期控件中WdatePicker里配置的dateFmt中的值，即日期格式
	 * 
	 * @param fieldcheck
	 * @return
	 */
	public static String getWdatePickerDateFmt(String fieldcheck) {
		String dateformat = "";
		if (StringHelper.isEmpty(fieldcheck)) {
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		// {dateFmt:'yyyy年MM月dd日 HH时mm分ss秒',alwaysUseStartDate:true}
		int dateFmtIndex = fieldcheck.indexOf("dateFmt");
		if (dateFmtIndex > -1) {
			int datefmtStart = fieldcheck.indexOf("'", dateFmtIndex);
			int datefmtEnd = fieldcheck.indexOf("'", datefmtStart + 1);
			dateformat = fieldcheck.substring(datefmtStart + 1, datefmtEnd);
		}
		return dateformat;
	}

	public static Date ConvertToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}

	 public static void main(String args[]){
		 System.out.println(getCurrentTimeMillis());
	 }
	 
	public static void main1(String[] args) {
		// System.out.println(StrToDateTime("2015年7月1日","yyyy-MM-dd HH:mm:ss"));
		/*
		 * //获取最后一天 try {
		 * System.out.println(getFirstDayOfMonthWeek(ConvertToDate
		 * ("2016-05-06")));
		 * System.out.println(getLastDayOfMonthWeek(ConvertToDate
		 * ("2016-05-06"))); } catch (ParseException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		System.out.println(monthMove("2016-06",-12));
		System.out.println(getDateWeekCN("2017-10-31"));
		System.out.println(getDateWeekCN("2017-11-01"));
		System.out.println(getDateWeekCN("2017-11-02"));
		System.out.println(getDateWeekCN("2017-11-03"));
		System.out.println(getDateWeekCN("2017-11-04"));
		System.out.println(getDateWeekCN("2017-11-05"));
		System.out.println(getDateWeekCN("2017-11-06"));
		System.out.println(getDateWeekCN("2017-11-07"));
		System.out.println(getDateWeekCN("2017-11-08"));
		System.out.println(getDateWeekCN("2017-11-09"));
		System.out.println(getDateWeekCN("2017-11-10"));
		System.out.println(getDateWeekCN("2017-11-11"));
		System.out.println(getDateWeekCN("2017-11-12"));
		System.out.println(getDateWeekCN("2017-11-13"));
		System.out.println(getDateWeekCN("2017-11-14"));
		System.out.println(getDateWeekCN("2017-11-15"));
		System.out.println(getDateWeekCN("2017-11-16"));
		System.out.println(getDateWeekCN("2017-11-17"));
		System.out.println(getDateWeekCN("2017-11-18"));
		System.out.println(getDateWeekCN("2017-11-19"));
		System.out.println(getDateWeekCN("2017-11-20"));
		System.out.println(getDateWeekCN("2017-11-21"));
		System.out.println(getDateWeekCN("2017-11-22"));
		System.out.println(getDateWeekCN("2017-11-23"));
		System.out.println(getDateWeekCN("2017-11-24"));
		System.out.println(getDateWeekCN("2017-11-25"));
		System.out.println(getDateWeekCN("2017-11-26"));
		System.out.println(getDateWeekCN("2017-11-27"));
		System.out.println(getDateWeekCN("2017-11-28"));
		System.out.println(getDateWeekCN("2017-11-29"));
		System.out.println(getDateWeekCN("2017-11-30"));
		System.out.println(getDateWeekCN("2017-12-01"));
 int i=0;
		System.out.print("i++==i++="+(i++==i++));
		System.out.print("i++==i++="+(i));
	}

	/**
	 * 返回当前月份(yyyy-MM)。
	 * 
	 * @return 当前月份
	 */
	public static String getCurrentMonth() {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String curmonth = sdf.format(today.getTime());
		return curmonth;
	}

	/**
	 * 移动月份。
	 * 
	 * @param date
	 *            原日期
	 * @param len
	 *            移动月份
	 * @return 移动后日期
	 */
	public static String monthMove(String date, int len) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.MONTH, len);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 截取2个时间段相交的时间段。
	 * 
	 * @param b1
	 *            开始时间段开始时间
	 * @param e1
	 *            开始时间段结束时间
	 * @param b2
	 *            结束时间段开始时间
	 * @param e2
	 *            结束时间段结束时间
	 * @return 相交时间段 String[] = {array[0]=begindate
	 *         ,array[1]=enddate}，不相交array[0]=""
	 */
	public static String[] getBetweenDate(String b1, String e1, String b2, String e2) {
		String[] date = new String[2];
		String begindate = "";
		String enddate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar begin1 = Calendar.getInstance();
			Calendar end1 = Calendar.getInstance();
			Calendar begin2 = Calendar.getInstance();
			Calendar end2 = Calendar.getInstance();
			begin1.setTime(sdf.parse(b1));
			end1.setTime(sdf.parse(e1));
			begin2.setTime(sdf.parse(b2));
			end2.setTime(sdf.parse(e2));
			if ((begin2.getTime().getTime() >= end1.getTime().getTime() && end2.getTime().getTime() >= end1.getTime().getTime())
					|| (begin2.getTime().getTime() <= begin1.getTime().getTime() && end2.getTime().getTime() <= begin1.getTime().getTime())) {
				date[0] = "";
				return date;
			}

			if (begin2.getTime().getTime() >= begin1.getTime().getTime()) {
				begindate = sdf.format(begin2.getTime());
			} else {
				begindate = sdf.format(begin1.getTime());
			}
			if (end2.getTime().getTime() >= end1.getTime().getTime()) {
				enddate = sdf.format(end1.getTime());
			} else {
				enddate = sdf.format(end2.getTime());
			}

			if (!begindate.equals("") && !enddate.equals("")) {
				date[0] = begindate;
				date[1] = enddate;
			}
		} catch (Exception e) {

		}
		return date;
	}

	/**
	 * 根据日期返回该月的第一天。
	 * 
	 * @param date
	 *            日期
	 * @return 该月第一天
	 */
	public static String getFirstDayOfMonthWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 1st
		// int day = calendar.get(Calendar.DAY_OF_WEEK);
		// calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}

	/**
	 * 根据日期返回该月的最后一天。
	 * 
	 * @param date
	 *            日期
	 * @return 该月最后一天
	 */
	public static String getLastDayOfMonthWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.add(calendar.MONTH, 1);
		calendar.set(calendar.DATE, 1);
		calendar.add(calendar.DATE, -1); // last day
		// int day = calendar.get(Calendar.DAY_OF_WEEK);
		// calendar.add(Calendar.DATE, 7 - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}

	/**
	 * 格式化Date类型日期为yyyy-MM-dd格式，如果date1为null,返回当天日期。
	 * 
	 * @param date1
	 *            Date类型日期
	 * @return String String类型日期(yyyy-MM-dd)
	 */
	public static String getShortDate(Date date1) {
		String strDate = null;
		if (date1 == null)
			date1 = Calendar.getInstance().getTime();
		// try{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		strDate = format.format(date1);
		// }catch(ParseException
		// pe){System.out.println("DateHelper.getShortDate解析异常:"+pe.toString());}
		return strDate;
	}

	/**
	 * 日期格式化方法。
	 * 
	 * @param date
	 *            Date类型日期
	 * @return String类型日期(yyyy-MM-dd HH:MM:SS)
	 */
	public static String convertDateIntoYYYYMMDD_HHCMMCSSStr(Date date) {
		return convertDateIntoDisplayStr(date, DATE_YYYYMMMMDD + " " + TIME_HHCMMCSS);
	}

	/**
	 * 日期格式化方法。
	 * 
	 * @param date
	 *            Date类型日期
	 * @return String类型日期
	 */
	public static String convertDateIntoYYYYMMDDStr(Date date) {
		return convertDateIntoDisplayStr(date, null);
	}

	/**
	 * 日期格式化方法。
	 * 
	 * @param date
	 *            Date类型日期
	 * @return String类型日期(yyyy-MM-dd HH:MM)
	 */
	public static String convertDateIntoYYYYMMDD_HHMMStr(Date date) {
		return convertDateIntoDisplayStr(date, DATE_YYYYMMMMDD + " " + TIME_HHCMM);
	}

	/**
	 * 按指定格式格式化日期，如format为null默认按yyyy-MM-dd格式转换。
	 * 
	 * @param date
	 *            Date类型日期
	 * @param format
	 *            格式
	 * @return String类型日期
	 */
	public static String convertDateIntoDisplayStr(Date date, String format) {
		String dateStr = null;
		if (format == null)
			format = DATE_YYYYMMMMDD;
		if (m_dtFormater == null) {
			m_dtFormater = new SimpleDateFormat();
		}
		m_dtFormater.applyPattern(format);
		if (date != null) {
			dateStr = m_dtFormater.format(date);
		}
		return dateStr;
	}

	/**
	 * String格式日期转换Date类型。
	 * 
	 * @param source
	 *            String类型日期(yyyy-MM-dd)
	 * @return Date类型日期
	 */
	public static Date parseDate(String source) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_YYYYMMMMDD);
		try {
			return format.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得当前星期的第一天
	 * 
	 * @return
	 */
	public static Date getCurrentWeekFirstDay() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
		Date first = calendar.getTime();// getDay(calendar.getTime(), 1);
		if (getDayOfWeek(date) == 1) {// 如果今天是星期日
			first = getDay(calendar.getTime(), -6);
		} else {
			first = getDay(calendar.getTime(), 1);
		}
		return first;
	}

	/**
	 * 取得所给日期的星期的第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfWeek(Date date) {
		// Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
		Date first = calendar.getTime();// getDay(calendar.getTime(), 1);
		if (getDayOfWeek(date) == 1) {// 如果今天是星期日
			first = getDay(calendar.getTime(), -6);
		} else {
			first = getDay(calendar.getTime(), 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(first);
	}

	/**
	 * 取得所给日期的星期的最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
		Date last = calendar.getTime();// getDay(calendar.getTime(), 1);
		if (getDayOfWeek(date) == 1) {// 如果今天是星期日
			last = getDay(calendar.getTime(), -6);
		} else {
			last = getDay(calendar.getTime(), 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(last);
	}

	/**
	 * 取得当前日期为本周的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前日期为本周的第几天
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static int getDayOfWeek(String dateStr) {
		Date date = parseDate(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回指定日期的前后的i天
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getDay(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return calendar.getTime();
	}

	/**
	 * String 转为date型
	 */

	public static Date stringtoDate(String stringDate) {
		if (StringHelper.isEmpty(stringDate)) {
			return new Date();
		}
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format1.parse(stringDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 获得第几季度
	public static int getThisSeasonTime(int month) {
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		return season;
	}

	public static String getSeasonStart(int season) {
		String array[][] = { { "01", "02", "03" }, { "04", "05", "06" }, { "07", "08", "09" }, { "10", "11", "12" } };

		String start_month = array[season - 1][0];
		String year = getCurrentYear();
		String date = year + "-" + start_month + "-01";
		return date;

	}

	public static String getSeasonend(int season) {
		String array[][] = { { "01", "02", "03" }, { "04", "05", "06" }, { "07", "08", "09" }, { "10", "11", "12" } };
		String end_month = array[season - 1][2];
		String year = getCurrentYear();
		String month = year + "-" + end_month;
		String lastmonth = monthMove(month, 1) + "-01";
		String enddate = dayMove(lastmonth, -1);
		return enddate;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDateTime(String str) {
		str = str.trim();
		if (str.split(":").length == 2) {
			str += ":00";
		}
		str = str.replace(" 0:", " 00:")//
				.replace(" 1:", " 01:")//
				.replace(" 2:", " 02:")//
				.replace(" 3:", " 03:")//
				.replace(" 4:", " 04:")//
				.replace(" 5:", " 05:")//
				.replace(" 6:", " 06:")//
				.replace(" 7:", " 07:")//
				.replace(" 8:", " 08:")//
				.replace(" 9:", " 09:");
		
		str = str.replace("/", "-").replace("年", "-").replace("月", "-").replace("日", "");
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		if (str.length() <= 10) {
			formatStr = "yyyy-MM-dd";
		} else if (str.length() < 18) {
			formatStr = "yyyy/MM/dd HH:mm:ss";
		}

		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将日期型字符串统一成为一种格式 2016年6月14日12:51:13 李大春 新增
	 * 
	 * @param str
	 *            字符型的日期格式，如：yyyy/M/d hh:mm:ss
	 * @param format
	 *            统一后的格式
	 * @return 统一后的格式:yyyy-MM-dd HH:mm:ss
	 */
	public static String StrToDateTime(String str, String format) {
		Date date = StrToDateTime(str);
		return convertDateIntoDisplayStr(date, format);
	}

	/**
	 * 返回当指定月份的天数。
	 * 
	 * @param year
	 *            int 年份 （yyyy）
	 * @param month
	 *            int 月份
	 * @return 天数
	 */
	public static int getMonthDays(int year, int month) {
		int days = new Date(year, month, 0).getDate();
		System.out.println("天数为=" + days);
		return days;
	}

	/**
	 * 返回当指定月份的天数。
	 * 
	 * @param yearMonth
	 *            （yyyy-MM）
	 * @return 天数
	 */
	public static int getMonthDays(String yearMonth) {
		int days = 0;
		// String strDate = "2012-02";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = new GregorianCalendar();
		try {
			Date date1 = sdf.parse(yearMonth);
			calendar.setTime(date1); // 放入你的日期
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println("天数为=" + days);
		return days;
	}

	public static boolean isValidDate(String str) {
		if (StringHelper.isEmpty(str) || str.length() < 8) {
			return false;
		}
		if (str.length() <= 10) {
			str += " 00:00:00";
		}

		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static boolean isValidDate2(String str) {
		if (StringHelper.isEmpty(str) || str.length() < 8) {
			return false;
		}
		if (str.length() <= 10) {
			str += " 00:00:00";
		}
		if (str.split(":").length < 3) {
			str += ":00";
		}
		str = str.replace("/", "-");
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 日期比较
	 * @param startDateStr 开始时间
	 * @param endDateStr 结束时间
	 * @return 如果 开始时间 小于 结束时间，则返回true，否则返回false
	 */
	public static boolean afterAll(String startDateStr, String endDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(startDateStr).getTime() < sdf.parse(endDateStr).getTime();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取两个时间范围内的所有时间，如date1=2018-07-03，date2=2017-07-08，返回值list内容为：2018-07-04，2018-07-05，2018-07-06，2018-07-07
	 * @param date1  yyyy-MM-dd
	 * @param date2  yyyy-MM-dd
	 * @return
	 */
	public static List<String> getMinusDates(String date1, String date2){ 
    	List<String> list = new ArrayList<String>();
    	String dateFormat = "yyyy-MM-dd"; 
        SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
        if(date1.equals(date2)){ 
            System.out.println("两个日期相等!");   
            return list; 
        }
        String tmp; 
        if(date1.compareTo(date2) > 0){  //确保 date1的日期不晚于date2 
            tmp = date1; date1 = date2; date2 = tmp; 
        } 
        tmp = format.format(str2Date(date1).getTime() + 3600*24*1000); 
        int num = 0;  
        while(tmp.compareTo(date2) < 0){                    
            System.out.println(tmp);
            list.add(tmp);
            num++; 
            tmp = format.format(str2Date(tmp).getTime() + 3600*24*1000); 
        }
        if(num == 0){
           System.out.println("两个日期相邻!"); 
        }
        return list;
    } 

	public static Date str2Date(String str) {
		String dateFormat = "yyyy-MM-dd"; 
	    SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
        if (str == null) return null; 
        try { 
            return format.parse(str); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 
	
	/* 获取指定年月有多少个工作日） 
     * 
     * @param year 
     * @param month 
     * @return 
     */ 

    public static int countWorkDay(int year, int month) { 
        Calendar c = Calendar.getInstance(); 
        c.set(Calendar.YEAR, year); 
        // 月份是从0开始计算，所以需要减1 
        c.set(Calendar.MONTH, month - 1); 
        // 当月最后一天的日期 
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
        // 开始日期为1号 
        int start = 1; 
        // 计数 
        int count = 0; 
        while (start <= max) { 
            c.set(Calendar.DAY_OF_MONTH, start); 
            if (isWorkDay(c)) { 
                count++; 
            } 
            start++; 
        } 
        return count; 
    } 

    // 判断是否工作日（未排除法定节假日，由于涉及到农历节日，处理很麻烦） 
    public static boolean isWorkDay(Calendar c){ 
        // 获取星期,1~7,其中1代表星期日，2代表星期一 ... 7代表星期六 
        int week = c.get(Calendar.DAY_OF_WEEK); 
        // 不是周六和周日的都认为是工作日 
        return week != Calendar.SUNDAY && week != Calendar.SATURDAY; 

    }
}
