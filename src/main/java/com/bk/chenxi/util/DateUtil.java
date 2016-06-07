package com.bk.chenxi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类DateUtil.java的实现描述：日期转换
 * 
 * @copier wb-chenchuanke 2014年8月12日 上午9:17:11
 */

public class DateUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 时间是否在配置时间之前
     * 
     * @param configDate
     * @param entryDate
     * @return
     */
    public static boolean isBefore(Date configDate, Date entryDate) {
        boolean bool = entryDate.before(configDate);
        return bool;
    }

    /**
     * 时间是否在配置时间之后
     * 
     * @param configDate
     * @param entryDate
     * @return
     */
    public static boolean isAfter(Date configDate, Date entryDate) {
        boolean bool = entryDate.after(configDate);// 入职时间是否在指定时间之后
        return bool;
    }

    /**
     * 将字符串时间转换成Date类型
     * 
     * @param str
     * @return
     */
    public static Date convertStr2Date(String str) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(str);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 将字符串时间转换成Date类型
     * 
     * @param str
     * @return
     */
    public static Date convertStr3Date(String str) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(str);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 字符串转日期
     * 
     * @param str
     * @return
     */
    public static Date StringToDate(String str) {
        Date date = null;
        try {
            date = new Date(str);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 加减天数
     * 
     * @param date
     * @return Date
     */
    public static Date increaseDate(Date aDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 加减小时
     * 
     * @param date
     * @return Date
     */
    public static Date increaseHour(Date aDate, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 获取当月最后一天
     * 
     * @param aDate
     * @return
     */
    public static Date lastDayByMonth(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取当前月份
     * 
     * @param aDate
     * @return
     */
    public static Integer getMonth(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * yyyy-MM-dd
     * 
     * @param aDate
     * @return
     */
    public static String dateTo8String(Date aDate) {
        if (aDate == null) {
            return null;
        }
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     * 
     * @param aDate
     * @return
     */
    public static String dateTo14StringCH(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyy年MM月dd日 HH:mm:ss
     * 
     * @param aDate
     * @return
     */
    public static String dateTo8StringCH(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * 
     * @param aDate
     * @return
     */
    public static String dateTo14String(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyy-MM-dd HH:mm
     * 
     * @param aDate
     * @return
     */
    public static String dateTo12String(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyyMMdd
     * 
     * @param aDate
     * @return
     */
    public static String dateTo8String1(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dates = sdf.format(aDate);
        return dates;
    }

    /**
     * yyyyMMdd
     * 
     * @param aDate
     * @return
     */
    public static Integer dateTo8Integer(Date aDate) {
        String dates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dates = sdf.format(aDate);
        return Integer.valueOf(dates);
    }

    /**
     * yyyyMMdd
     * 
     * @param str
     * @return
     */
    public static Date string8ToDate(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /** 判断主方法 */
    public static boolean validate(String dateString) {
        // 使用正则表达式 测试 字符 符合 dddd-dd-dd 的格式(d表示数字)
        Pattern p = Pattern.compile("\\d{4}+\\d{1,2}+\\d{1,2}+");
        Matcher m = p.matcher(dateString);
        if (!m.matches()) {
            return false;
        }

        // 得到年月日
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4, 6));
        int day = Integer.valueOf(dateString.substring(6, 8));

        if (month < 1 || month > 12) {
            return false;
        }
        int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (isLeapYear(year)) {
            monthLengths[2] = 29;
        } else {
            monthLengths[2] = 28;
        }
        int monthLength = monthLengths[month];
        if (day < 1 || day > monthLength) {
            return false;
        }
        return true;
    }

    /** 是否是闰年 */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    public static Date now() {
        return new Date();
    }

    /**
     * 根据年月日拼装日期
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDate(String year, String month, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = year + "-" + month + "-" + day;
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 比较两个日期相差多少天
     * 
     * @param fDate
     * @param oDate
     * @return
     */
    public static int countDiffDateDays(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return (day2 - day1);
    }

    public static long countDiffDateDays2(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTimeInMillis(0);
        aCalendar.setTime(fDate);
        fDate = getDate(aCalendar.get(Calendar.YEAR), aCalendar.get(Calendar.MONTH),
                        aCalendar.get(Calendar.DAY_OF_YEAR));
        aCalendar.setTime(oDate);
        oDate = getDate(aCalendar.get(Calendar.YEAR), aCalendar.get(Calendar.MONTH),
                        aCalendar.get(Calendar.DAY_OF_YEAR));
        long quot = (oDate.getTime() - fDate.getTime()) / 1000 / 60 / 60 / 24;
        if (quot < 0) {
            quot = -quot;
        }
        return quot;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_YEAR, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 过滤掉日期中的时分秒，保留年月日
     * 
     * @param date
     * @return
     */
    public static Date convertDate2Date(Date date) {
        String aDateStr = dateTo8String(date);
        Date adate = convertStr2Date(aDateStr);
        return adate;
    }

    /**
     * 获取日期年
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        return aCalendar.get(Calendar.YEAR);
    }

    /**
     * 对两个日期进行比较(只比较日期，不比较时间)
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int compareOnlyDate(Date date1, Date date2) {
        String date1str = dateTo8String(date1);
        Date newDate1 = convertStr2Date(date1str);
        String date2str = dateTo8String(date2);
        Date newDate2 = convertStr2Date(date2str);
        return newDate1.compareTo(newDate2);
    }

    public static long getBetweenDays(Date date1, Date date2) {
        if (date2.before(date1)) {// 保证date1在date2之前
            Date date3 = date1;
            date1 = date2;
            date2 = date3;
        }
        long to = date1.getTime();
        long from = date2.getTime();
        return (from - to) / (1000 * 60 * 60 * 24);
    }

}
