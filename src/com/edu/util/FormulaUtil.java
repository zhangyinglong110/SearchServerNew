package com.edu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Administrator
 *
 */
public class FormulaUtil {

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @param d6
	 * @param d7
	 * @param d8
	 * @param d9
	 * @param d10
	 * @return
	 */
	public static double getTotalScore(double d1, double d2, double d3, double d4, double d5, double d6, double d7,
			double d8, double d9, double d10) {
		double sumValue = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10;
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		System.out.println(d4);
		System.out.println(d5);
		System.out.println(d6);
		System.out.println(d7);
		System.out.println(d8);
		System.out.println(d9);
		System.out.println(d10);
		return sumValue;
	}

	public static boolean isSameDate(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setFirstDayOfWeek(Calendar.MONDAY);// 西方周日为一周的第一天，咱得将周一设为一周第一天
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (subYear == 0)// subYear==0,说明是同一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) // subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)// subYear==-1,说明cal比cal2小一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

}
