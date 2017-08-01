package com.edu.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

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
		cal1.setFirstDayOfWeek(Calendar.MONDAY);// ��������Ϊһ�ܵĵ�һ�죬�۵ý���һ��Ϊһ�ܵ�һ��
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (subYear == 0)// subYear==0,˵����ͬһ��
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) // subYear==1,˵��cal��cal2��һ��;java��һ����"0"��ʶ����ô12����"11"
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)// subYear==-1,˵��cal��cal2Сһ��
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		boolean a = isSameDate("2017-7-25", "2017-7-31");
		if (a) {
			System.out.println("��ͬһ�ܣ�");
		} else {
			System.out.println("����ͬһ�ܣ�");
		}
	}

}
