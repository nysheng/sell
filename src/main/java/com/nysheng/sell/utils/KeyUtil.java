package com.nysheng.sell.utils;

import java.util.Random;

/**
 * 主键id生成工具
 *
 * @author nysheng
 * 2020/4/2 18:16
 */
public class KeyUtil {
    public static synchronized String getUniqueKey(){
        Random random=new Random();
        Integer randomInt=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(randomInt);
    }
}
