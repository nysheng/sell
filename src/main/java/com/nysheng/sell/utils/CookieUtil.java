package com.nysheng.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public static Cookie get(HttpServletRequest request,String name){
        Map<String,Cookie> map=cookies2Map(request);
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            return null;
        }
    }
    private static Map<String,Cookie> cookies2Map(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        Map<String,Cookie> map=new HashMap<>();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                map.put(cookie.getName(),cookie);
            }
        }
        return map;
    }
}
