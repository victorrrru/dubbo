package com.fww;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created By victorrrr on 2017/8/31
 */
public class DateUtil {

    @Test
    public void test1() {
        System.out.println(LocalTime.now());
        System.out.println(LocalTime.now().plusHours(1));
        System.out.println(LocalDate.parse("2017-08-09").getDayOfMonth());
        System.out.println(LocalDate.parse("2017-08-23").with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(LocalDate.parse("2017-08-23").with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(LocalDateTime.parse("2017-08-09 12:01:09", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd")));
        System.out.println(LocalDate.parse("2017-08-09"));
        System.out.println(LocalDate.parse("2017-08-09").getDayOfMonth());
        System.out.println(LocalDate.parse("2017-08-09").getDayOfMonth());
        System.out.println(LocalDate.parse("2017-08-09").getDayOfMonth());
        System.out.println(LocalDate.parse("2017-08-09").getDayOfMonth());
    }

    @Test
    public void test2() {
        Calendar instance = Calendar.getInstance();
        System.out.println(TimeZone.getDefault().getID());
        System.out.println(System.getProperty("user.timezone"));
        System.out.println(Locale.getDefault().toString());
        System.out.println(new SimpleDateFormat("yyyy年MM月dd日 hh时 a E").format(new Date()));

    }
}
