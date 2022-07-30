package com.api.lebooo.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author 沈锋 email: shenfeng@yutian.com.cn
 * @Description: 日期工具类
 * @date 2013-7-5
 */
public class DateUtil {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String PATNER_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String PATNER_DEFAULT_O = "yyyy/MM/dd HH:mm:ss";
    /**
     * HH:mm:ss
     */
    public static final String PATNER_ACTIVITY = "HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String PATNER_NO_MILLSECOND = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String PATNER_FULL = "yyyyMMddHHmmssSSS";
    /**
     * yyyy-MM-dd
     */
    public static final String PATNER_ISO9985 = "yyyy-MM-dd";
    /**
     * yyyyMMdd
     */
    public static final String PATNER_ISO9985_2 = "yyyyMMdd";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String PATNER_DEFAULT_NOMIN = "yyyy-MM-dd HH:mm";

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取系统的当前时间
     *
     * @return Date
     */
    public static Date currentDate() {
        return new Date();
    }

    /**
     * @param dates
     * @return
     */
    public static int getFiledValue(int field, Date... dates) {
        Calendar calendar = Calendar.getInstance();
        if (dates != null && dates.length > 0) {
            calendar.setTime(dates[0]);
        } else {
            calendar.setTime(new Date());
        }
        return calendar.get(field);
    }

    // 获得格式化后的字符串
    public static Date parse(String datestring) {
        return parse(datestring, PATNER_ISO9985);
    }

    public static Date stringToDate(String datestring) {
        return parse(datestring, PATNER_DEFAULT);
    }

    public static Date parse(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        DateFormat parser = new SimpleDateFormat(format);
        try {
            return parser.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("格式化日期格式的时候出错:");
        }
    }

    public static String toString(Date now) {
        SimpleDateFormat formatTime = new SimpleDateFormat(PATNER_DEFAULT);
        if (now == null)
            return "";
        return formatTime.format(now);
    }
    public static String toStringO(Date now) {
        SimpleDateFormat formatTime = new SimpleDateFormat(PATNER_DEFAULT_O);
        if (now == null) {
            return "";
        }
        return formatTime.format(now);
    }

    public static String toStringNo(Date now) {
        SimpleDateFormat formatTime = new SimpleDateFormat(PATNER_DEFAULT_NOMIN);
        if (now == null) {
            return "";
        }
        return formatTime.format(now);
    }

    public static String toStringYmd(Date now) {
        SimpleDateFormat formatTime = new SimpleDateFormat(PATNER_ISO9985);
        if (now == null)
            return "";
        return formatTime.format(now);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param formatter
     * @return
     */
    public static String format(Date date, String formatter) {
        SimpleDateFormat formatTime = new SimpleDateFormat(formatter);
        return formatTime.format(date);
    }

    /**
     * 清除小时和分钟
     *
     * @param date
     * @return
     */
    public static Date clearHourMinute(Date date) {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String printNow() {
        return toString(currentDate());
    }

    /**
     * 减去一分钟
     *
     * @param date
     * @return
     */
    public static Date cutOneMin(Date date) {
        if (date == null)
            return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 1);
        return calendar.getTime();
    }

    /**
     * 加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        if (date == null)
            return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        if (date == null)
            return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 控制台简单输出日志
     *
     * @param msg
     */
    public static void spLog(String msg) {
        System.err.println(msg);
    }

    public static String timestampe() {
        return format(DateUtil.currentDate(), PATNER_FULL);
    }

    /**
     * 比较两个时间
     *
     * @return
     */
    public static long compareTwoDates(Date compareDate, Date baseDate) {

        if (null == compareDate || null == baseDate) {
            return 0L;
        }

        Calendar compareCal = Calendar.getInstance();
        compareCal.setTime(DateUtil.parse(DateUtil.format(compareDate, PATNER_ISO9985), PATNER_ISO9985));
        Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(DateUtil.parse(DateUtil.format(baseDate, PATNER_ISO9985), PATNER_ISO9985));

        return (compareCal.getTimeInMillis() - baseCal.getTimeInMillis()) / (1000 * 60 * 60 * 24);
    }

    public static long compareTwoMss(Date compareDate, Date baseDate) {
        if (null == compareDate || null == baseDate) {
            return 0L;
        }

        Calendar compareCal = Calendar.getInstance();
        compareCal.setTime(DateUtil.parse(DateUtil.format(compareDate, PATNER_DEFAULT), PATNER_DEFAULT));
        Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(DateUtil.parse(DateUtil.format(baseDate, PATNER_DEFAULT), PATNER_DEFAULT));

        return (compareCal.getTimeInMillis() - baseCal.getTimeInMillis());
    }

    /**
     * 转时分秒格式如：12:59:59
     */
    public static String secToTime(int time) {
        int miao = time % 60;
        int fen = time / 60;
        int hour = 0;
        if (fen >= 60) {
            hour = fen / 60;
            fen = fen % 60;
        }
        String timeString = "";
        String miaoString = "";
        String fenString = "";
        String hourString = "";
        if (miao < 10) {
            miaoString = "0" + miao;
        } else {
            miaoString = miao + "";
        }
        if (fen < 10) {
            fenString = "0" + fen;
        } else {
            fenString = fen + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + fenString + ":" + miaoString;
        } else {
            timeString = fenString + ":" + miaoString;
        }
        return timeString;
    }

    public static String formatTime(Date now) {
        SimpleDateFormat formatTime = new SimpleDateFormat(PATNER_NO_MILLSECOND);
        if (now == null){
            return "";
        }
        return formatTime.format(now);
    }


    public static String dateToStr(Date date, String pattern) {
        if ((date == null) || (date.equals("")))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**定义时间格式*/
    public static DateFormat dateToFor(Integer formatNum) {
        String pattern;
        if(formatNum==1){
            pattern="yyyy.MM.dd HH:mm:ss";
        }else if(formatNum==2){
            pattern="yyyy.MM.dd";
        }else{
            pattern="yyyy.MM";
        }
        return new SimpleDateFormat(pattern);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }

    public static String dateToStr(Date date, int type) {
        switch (type) {
            case 0:
                return dateToStr(date);
            case 1:
                return dateToStr(date, "yyyy/MM");
            case 2:
                return dateToStr(date, "yyyyMMdd");
            case 3:
                return dateToStr(date, "yyyyMM");
            case 4:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case 5:
                return dateToStr(date, "yyyyMMddHHmmss");
            case 6:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case 7:
                return dateToStr(date, "HH:mm:ss");
            case 8:
                return dateToStr(date, "HH:mm");
            case 9:
                return dateToStr(date, "HHmmss");
            case 10:
                return dateToStr(date, "HHmm");
            case 11:
                return dateToStr(date, "yyyy-MM-dd");
            case 12:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
            case 13:
                return dateToStr(date, "yyyy.MM.dd HH:mm:ss");
            case 14:
                return dateToStr(date, "yyyy.MM.dd");
            case 15:
                return dateToStr(date, "yyyy.MM.dd HH");
            case 16:
                return dateToStr(date, "yyyy.MM");
            case 17:
                return dateToStr(date, "yyyy-MM");
        }
        throw new IllegalArgumentException("Type undefined : " + type);
    }


    public static void main(String[] args) {
//         System.err.println( DateUtil.toStringO(new Date()));
        // DateUtil.PATNER_ISO9985 ));


    }
}
