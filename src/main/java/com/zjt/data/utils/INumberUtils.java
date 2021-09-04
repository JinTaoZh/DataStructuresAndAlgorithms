package com.zjt.data.utils;

/**
 * 常用数字判断
 *
 * @author zjt
 * @date 2021-09-04
 */
public class INumberUtils {

    /**
     * 正整数的反转
     */
    public static Integer reverse(Integer num) {
        int reverse = 0;
        while (num > 0) {
            reverse *= 10;
            reverse += num % 10;

            num = num / 10;
        }
        return reverse;
    }

    /**
     * 是正数：不为空 && 大于0
     */
    public static boolean isPositive(Number num) {
        return num != null && num.doubleValue() > 0;
    }

    /**
     * 不是正数：为空 || 小于等于0
     */
    public static boolean isNotPositive(Number num) {
        return !isPositive(num);
    }


}
