package com.nysheng.sell.controller;

import com.nysheng.sell.config.ProjectUrlConfig;
import com.nysheng.sell.constant.CookieConstant;
import com.nysheng.sell.constant.RedisConstant;
import com.nysheng.sell.dataobject.SellerInfo;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.service.SellerInfoService;
import com.nysheng.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家信息验证Controller层
 *
 * @author nysheng
 * 2020/4/15 19:12
 */
@Controller
@RequestMapping("/seller")
public class SellerInfoController {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid")String openid, HttpServletResponse response, Map<String, Object> map){
        //openid去和数据库中的数据匹配
        try {
            SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("redirectUrl","/sell/seller/order/list");
        }
        //设置token到redis
        String token= UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOOKEN_PREFIX,token),openid,RedisConstant.EXPIRE, TimeUnit.SECONDS);
        //设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.MAXAGE_7200);
        return new ModelAndView("redirect:"+ projectUrlConfig.getSell()+"/sell/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        //获得包含token的cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie!=null){
            //清除redis中token
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOOKEN_PREFIX,cookie.getValue()));
            //清除cookie中token
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("redirectUrl","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
