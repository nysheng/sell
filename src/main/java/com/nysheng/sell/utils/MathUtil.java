package com.nysheng.sell.utils;

/**
 * 数据计算工具类
 *
 * @author nysheng
 * 2020/4/9 14:32
 */
public class MathUtil {
    private static final double MATH_RANGE=0.01;
    public static boolean equals(double d1,double d2){
        if(Math.abs(d1-d2)<MATH_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
