package com.nysheng.sell.aspect;

import com.nysheng.sell.constant.CookieConstant;
import com.nysheng.sell.constant.RedisConstant;
import com.nysheng.sell.exception.SellerAuthorizeException;
import com.nysheng.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家授权登录
 *
 * @author nysheng
 * 2020/4/16 16:21
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Pointcut("execution(public * com.nysheng.sell.controller.Seller*.*(..))"+"&& !execution(public * com.nysheng.sell.controller.SellerInfoController.*(..))")
    public void verify(){}
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.info("【登录校验】Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }
        //去redis获取token
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.info("【登录校验】Redis中查询不到token");
            throw new SellerAuthorizeException();
        }
    }
}
