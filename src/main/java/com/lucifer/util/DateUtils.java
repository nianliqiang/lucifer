package com.lucifer.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jodd.util.StringUtil;

/**
 * @Description: 时间日期操作类
 * @author:
 * @date:
 */
public class DateUtils {

    /**
     * 要用到的DATE Format的定义
     */
    public static final String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日
    public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日 时:分:秒

    public static final String DATE_FORMAT_STARTTIME = "yyyy-MM-dd 00:00:00";
    public static final String DATE_FORMAT_ENDTIME = "yyyy-MM-dd 23:59:59";
    public static final SimpleDateFormat sdfStartTime = new SimpleDateFormat(DateUtils.DATE_FORMAT_STARTTIME);
    public static final SimpleDateFormat sdfEndTime = new SimpleDateFormat(DateUtils.DATE_FORMAT_ENDTIME);

    public static final SimpleDateFormat sdfDateOnly = new SimpleDateFormat(DateUtils.DATE_FORMAT_DATEONLY);
    public static final SimpleDateFormat sdfDateTime = new SimpleDateFormat(DateUtils.DATE_FORMAT_DATETIME);

    /**
     * 静态常量
     */
    public static final int C_ONE_SECOND = 1000;
    public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
    public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
    public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

    /**
     * 获取指定时间之前(之后)的几个月的那一天的日期
     *
     * @param date 这个日期之前或之后
     * @param formatCode 按照这个模板格式化
     * @param month 正数:之后,负数:之前
     */
    public static String getBeforeMonthFormat(Date date, String formatCode, int month) {
        if (StringUtil.isEmpty(formatCode)) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        Date m = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(formatCode);
        return sdf.format(m);
    }

    /**
     * 获取指定时间之前(之后)的几年的那一天的日期
     *
     * @param date 这个日期之前或之后
     * @param formatCode 按照这个模板格式化
     * @param year 正数:之后,负数:之前
     */
    public static String getBeforeYearFormat(Date date, String formatCode, int year) {
        if (StringUtil.isEmpty(formatCode)) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        Date m = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(formatCode);
        return sdf.format(m);
    }

    /**
     * 获取昨天的日期 ,传入格式 formatCode
     */
    public static String getYesterdayYmd(Date date, String formatCode) {
        if (date == null || formatCode == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        SimpleDateFormat sdf = new SimpleDateFormat(formatCode);
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取昨天的日期
     */
    public static Date getYesterday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的后num天时间
     */
    public static Date getNextDate(Date time, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_YEAR, num);
        return getNowDate(calendar.getTime());
    }

    public static Date getNowDate(Date time) {
        return formatStr2Date(formatDate2Str(time, DATE_FORMAT_DATEONLY) + " 00:00:00", DATE_FORMAT_DATETIME);
    }

    /**
     * 根据样式格式化当前日期
     */
    public static Date getNowDateByFormat(Date time, String format) {
        return formatStr2Date(formatDate2Str(time, format), format);
    }

    /**
     * 转换指定时间为指定格式
     */
    public static String formatDate2Str(Date date, String format) {
        if (date == null) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }
        SimpleDateFormat sdf = getSimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 拿到指定输出格式的SimpleDateFormat
     */
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat sdf;
        if (format == null) {
            sdf = new SimpleDateFormat(DATE_FORMAT_DATETIME);
        } else {
            sdf = new SimpleDateFormat(format);
        }

        return sdf;
    }

    /**
     * 转换指定格式的时间为Date
     */
    public static Date formatStr2Date(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }
        SimpleDateFormat sdf = getSimpleDateFormat(format);
        return sdf.parse(dateStr, new ParsePosition(0));
    }


    /**
     * 计算当前月份的最大天数
     */
    public static int findMaxDayInMonth() {
        return findMaxDayInMonth(0, 0);
    }

    /**
     * 计算指定日期月份的最大天数
     */
    public static int findMaxDayInMonth(Date date) {
        if (date == null) {
            return 0;
        }
        return findMaxDayInMonth(date2Calendar(date));
    }

    /**
     * 计算指定日历月份的最大天数
     */
    public static int findMaxDayInMonth(Calendar calendar) {
        if (calendar == null) {
            return 0;
        }

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算当前年某月份的最大天数
     */
    public static int findMaxDayInMonth(int month) {
        return findMaxDayInMonth(0, month);
    }

    /**
     * 计算某年某月份的最大天数
     */
    public static int findMaxDayInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        if (year > 0) {
            calendar.set(Calendar.YEAR, year);
        }

        if (month > 0) {
            calendar.set(Calendar.MONTH, month - 1);
        }

        return findMaxDayInMonth(calendar);
    }

    /**
     * Calendar 转换为 Date
     */
    public static Date calendar2Date(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.getTime();
    }

    /**
     * Date 转换为 Calendar
     */
    public static Calendar date2Calendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 拿到默认格式的SimpleDateFormat
     */
    public static SimpleDateFormat getSimpleDateFormat() {
        return getSimpleDateFormat(null);
    }

    /**
     * 转换当前时间为默认格式
     */
    public static String formatDate2Str() {
        return formatDate2Str(new Date());
    }

    /**
     * 转换当前时间为默认格式
     */
    public static String formatShortDate2Str() {
        return formatDate2Str(new Date(), DATE_FORMAT_DATEONLY);
    }

    /**
     * 转换指定时间为默认格式
     */
    public static String formatDate2Str(Date date) {
        return formatDate2Str(date, DATE_FORMAT_DATETIME);
    }

    /**
     * 转换指定时间为默认格式
     */
    public static String formatDate2StrDefault(Date date) {
        String result = "";
        try {
            result = formatDate2Str(date, DATE_FORMAT_DATETIME);
        } catch (Exception e) {
            try {
                result = formatDate2Str(date, DATE_FORMAT_DATEONLY);
            } catch (Exception e1) {
                return null;
            }
        }
        return result;
    }

    /**
     * 转换默认格式的时间为Date
     */
    public static Date formatStr2Date(String dateStr) {
        return formatStr2Date(dateStr, null);
    }

    /**
     * 转换默认格式的时间为Date
     */
    public static Date formatStr2DateDefault(String dateStr) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATETIME);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            try {
                sdf = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
                date = sdf.parse(dateStr);
            } catch (Exception e1) {
                return null;
            }
        }
        return date;
    }

    /**
     * 转换默认格式的时间为指定格式时间
     */
    public static String formatDefault2Define(String dateStr,
        String defineFormat) {
        return formatSource2Target(dateStr, DATE_FORMAT_DATETIME, defineFormat);
    }

    /**
     * 转换源格式的时间为目标格式时间
     */
    public static String formatSource2Target(String dateStr,
        String sourceFormat, String targetFormat) {
        Date date = formatStr2Date(dateStr, sourceFormat);
        return formatDate2Str(date, targetFormat);
    }

    /**
     * 计算指定日期是该年中的第几周
     */
    public static int findWeeksNoInYear(Date date) {
        if (date == null) {
            return 0;
        }
        return findWeeksNoInYear(date2Calendar(date));
    }

    /**
     * 计算指定日历是该年中的第几周
     */
    public static int findWeeksNoInYear(Calendar calendar) {
        if (calendar == null) {
            return 0;
        }
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 计算一年中的第几星期是几号
     */
    public static Date findDateInWeekOnYear(int year, int weekInYear,
        int dayInWeek) {
        Calendar calendar = Calendar.getInstance();
        if (year > 0) {
            calendar.set(Calendar.YEAR, year);
        }

        calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
        calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);

        return calendar.getTime();
    }

    /**
     * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
     */
    public static Date dayBefore2Date(int days) {
        Date date = new Date();
        return dayBefore2Object(days, null, date);
    }

    /**
     * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
     */
    public static String dayBefore2Str(int days) {
        String string = formatDate2Str();
        return dayBefore2Object(days, null, string);
    }

    /**
     * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T dayBefore2Object(int days,
        String format, T instance) {
        Calendar calendar = Calendar.getInstance();
        if (days == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }

        calendar.add(Calendar.DATE, days);
        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }
        return null;
    }

    /**
     * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
     */
    public static Date defineDayBefore2Date(Date date, int days) {
        Date dateInstance = new Date();
        return defineDayBefore2Object(date, days, null, dateInstance);
    }

    /**
     * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
     */
    public static String defineDayBefore2Str(Date date, int days) {
        String stringInstance = formatDate2Str();
        return defineDayBefore2Object(date, days, null, stringInstance);
    }

    /**
     * 相对于指定日期的前几天(days < 0)或者后几天(days > 0)
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T defineDayBefore2Object(Date date,
        int days, String format, T instance) {
        if (date == null || days == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(Calendar.DATE, days);
        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> T defineHourBefore2Object(Date date,
        int hours, String format, T instance) {
        if (date == null || hours == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(Calendar.MINUTE, hours);
        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }
        return null;
    }

    /**
     * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    public static Date monthBefore2Date(int months) {
        Date date = new Date();
        return monthBefore2Object(months, null, date);
    }

    /**
     * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    public static String monthBefore2Str(int months) {
        String string = formatDate2Str();
        return monthBefore2Object(months, null, string);
    }

    /**
     * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T monthBefore2Object(int months,
        String format, T instance) {
        if (months == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, months);

        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }

        return null;
    }

    /**
     * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    public static Date defineMonthBefore2Date(Date date, int months) {
        Date dateInstance = new Date();
        return defineMonthBefore2Object(date, months, null, dateInstance);
    }

    /**
     * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    public static String defineMonthBefore2Str(Date date, int months) {
        String stringInstance = formatDate2Str();
        return defineMonthBefore2Object(date, months, null, stringInstance);
    }

    /**
     * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T defineMonthBefore2Object(Date date,
        int months, String format, T instance) {
        if (months == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = DATE_FORMAT_DATETIME;
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(Calendar.MONTH, months);

        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }

        return null;
    }

    /**
     * 计算两个日期之间差的天数,支持跨年操作,但是不支持(只支持日期的相减.不支持时间相减) Date a1 = sdfDateTime.parse("2017-12-31 23:00:00"); Date a2 = sdfDateTime.parse("2018-01-01
     * 00:00:01"); 这两个时间相减=0
     */
    @Deprecated
    public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {
        if (firstCalendar.after(secondCalendar)) {
            Calendar calendar = firstCalendar;
            firstCalendar = secondCalendar;
            secondCalendar = calendar;
        }

        long calendarNum1 = firstCalendar.getTimeInMillis();
        long calendarNum2 = secondCalendar.getTimeInMillis();

        return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
    }

    public static String getDateOfNextWeekDay(Integer weekDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int currWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (currWeek == 0) {
            currWeek = 7;
        }
        if (weekDay > currWeek) {
            c.add(Calendar.DATE, weekDay - currWeek);
        } else if (weekDay < currWeek) {
            c.add(Calendar.DATE, 7 - currWeek + weekDay);
        }
        String dateStr = formatDate2Str(c.getTime(), "yyyyMMdd");
        return dateStr;
    }

    /**
     * lfx 下面函数使用
     */
    private static Map<String, Integer> weekMap;

    static {
        weekMap = new HashMap<String, Integer>();
        weekMap.put("周六", 7);
        weekMap.put("周日", 1);
        weekMap.put("周一", 2);
        weekMap.put("周二", 3);
        weekMap.put("周三", 4);
        weekMap.put("周四", 5);
        weekMap.put("周五", 6);
    }

    public static String getWeekStr(String dateStr, String dateFmt) {
        Calendar c = Calendar.getInstance();
        c.setTime(formatStr2Date(dateStr, dateFmt));
        int num = c.get(Calendar.DAY_OF_WEEK);
        for (String key : weekMap.keySet()) {
            if (weekMap.get(key) == num) {
                return key;
            }
        }
        return "";
    }

    /**
     * 获取当前日期是星期几，礼拜天为7，礼拜六为6，依次类推
     */
    public static int getWeekDay() {
        Calendar c = Calendar.getInstance();
        int num = c.get(Calendar.DAY_OF_WEEK);
        if (num == 1) {
            return 7;
        }
        return num - 1;
    }

    /**
     * 获取当前时间, timestamp 格式
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取指定日期的前(后)num天时间
     */
    public static Date getPreDate(Date time, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_YEAR, -num);
        return getNowDate(calendar.getTime());
    }

    /**
     * 计算两个时间之间的天数差(支持跨年以及 date/time 的计算)
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 天数差
     */
    public static Integer calRelativeDate(Timestamp startDate, Timestamp endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = sdf.parse(sdf.format(startDate));
            d2 = sdf.parse(sdf.format(endDate));
        } catch (Exception e) {
        }
        if (d1 != null && d2 != null) {
            Date[] d = new Date[]{d1, d2};
            Calendar[] cal = new Calendar[2];

            for (int m = 0; m < cal.length; ++m) {
                cal[m] = Calendar.getInstance();
                cal[m].setTime(d[m]);
                cal[m].set(11, 0);
                cal[m].set(12, 0);
                cal[m].set(13, 0);
            }

            long var12 = cal[0].getTime().getTime();
            long n = cal[1].getTime().getTime();
            int ret = (int) Math.abs((var12 - n) / 1000L / 3600L / 24L);
            return ret;
        } else {
            return 0;
        }
    }


    //获取开始时间   如2018-08-01 00:00:00
    public static Date getStartTime(Date date) {
        try {
            String format = sdfStartTime.format(date);
            Date startTime = sdfDateTime.parse(format);
            return  startTime ;
        } catch (ParseException e) {
            e.printStackTrace();
            return   null;
        }

    }

    //获取结束时间   如2018-08-01 23:59:59
    public static Date getEndTime(Date date) {
        try {
            String format = sdfEndTime.format(date);
            Date endTime = sdfDateTime.parse(format);
            return  endTime ;
        } catch (ParseException e) {
            e.printStackTrace();
            return   null;
        }

    }


    public static Date getFormatTime(String timeInStr, String formatStr, String formatTime) {
        try {
            SimpleDateFormat ex = new SimpleDateFormat(formatStr);
            Date parse = ex.parse(timeInStr);
            SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
            String format = sdf.format(parse);
            SimpleDateFormat sdft = new SimpleDateFormat(DATE_FORMAT_DATETIME);
            Date date = sdft.parse(format);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String getFormatTimeInStr(String timeInStr, String formatStr, String formatTime) {
        try {
            SimpleDateFormat ex = new SimpleDateFormat(formatStr);
            Date parse = ex.parse(timeInStr);
            SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
            String format = sdf.format(parse);
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
