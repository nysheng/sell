package com.nysheng.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 *
 * @author nysheng
 * 2020/4/15 20:58
 */
public class CookieUtil {
    public static void set(HttpServletResponse response,String name,String value,Integer maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static void get(){

    }
}
