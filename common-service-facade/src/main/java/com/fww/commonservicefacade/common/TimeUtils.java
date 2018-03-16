package com.fww.commonservicefacade.common;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/03/15 16:11
 */
public class TimeUtils {

    public static final long MILLIS_PER_SECOND = 1000L;
    public static final long MILLIS_PER_MINUTE = 60000L;
    public static final long MILLIS_PER_HOUR = 3600000L;
    public static final long MILLIS_PER_DAY = 86400000L;

    public static Date addYear(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.YEAR, amount);
        return instance.getTime();
    }
    public static Date addMonth(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.MONTH, amount);
        return instance.getTime();
    }
    public static Date addDay(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.DAY_OF_MONTH, amount);
        return instance.getTime();
    }
    public static Date addHour(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.HOUR_OF_DAY, amount);
        return instance.getTime();
    }
    public static Date addMinute(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.MINUTE, amount);
        return instance.getTime();
    }
    public static Date addSecond(Date date, Integer amount) {
        Calendar instance = add(date, Calendar.SECOND, amount);
        return instance.getTime();
    }

    private static Calendar add(Date date, Integer dateType, Integer amount) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(dateType, amount);
        return instance;
    }
}
