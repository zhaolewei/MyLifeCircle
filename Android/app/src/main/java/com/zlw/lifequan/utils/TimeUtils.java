package com.zlw.lifequan.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_CHINESE = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_mm_ss = new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_HH_mm_ss = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_RECORDER = new SimpleDateFormat("yy/MM/dd");//16/6/18 00:07:05

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    public static String getTime_mm_ss(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_mm_ss);
    }

    public static String getTimeRecorder(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_RECORDER);
    }

    public static String getTime_HH_mm_ss(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_HH_mm_ss);
    }


    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }
}
