package com.zlw.lifequan.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 单位转换，工具类
 *
 * @author zlw QQ:739043667
 */
public class UnitsHelper {

    /**
     * 转换存储空间大小。例：1024 -> 1K
     */
    public static String toSize(long size) {
        if (size < 1024) {
            // B
            return size + "B";
        } else if (size < 1024 * 1024) {
            // K
            return decFormat(size / 1024f, 2) + "K";
        } else {
            // M
            return decFormat(size / (1024f * 1024f), 2) + "M";
        }
        // TODO ：G
    }

    /**
     * 去除多余的小数点尾数， 例： 12.34567 -> 12.35 ；支持四舍五入 num:要转换的数； decimal：要保留的小数点的位数
     */
    public static double decFormat(double num, int decimal) {
        BigDecimal b = new BigDecimal(num);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }


    /**
     * 千位分隔符
     *
     * @return
     */
    public static String moneyFormat(long num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }
}
