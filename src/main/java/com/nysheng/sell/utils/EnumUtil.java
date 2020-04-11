package com.nysheng.sell.utils;

import com.nysheng.sell.enums.StatusEnum;

/**
 * 枚举工具类
 *
 * @author nysheng
 * 2020/4/10 19:22
 */
public class EnumUtil {
    public static <T extends StatusEnum> T getByStatus(Integer status, Class<T> enumClass){
        for (T e:enumClass.getEnumConstants()){
            if(status.equals(e.getStatus())){
                return e;
            }
        }
        return null;
    }
}
