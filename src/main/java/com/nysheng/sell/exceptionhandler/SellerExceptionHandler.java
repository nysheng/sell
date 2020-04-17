package com.nysheng.sell.exceptionhandler;

import com.nysheng.sell.config.ProjectUrlConfig;
import com.nysheng.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 卖家
 *
 * @author nysheng
 * 2020/4/16 16:46
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWxOpenAuthorizeUrl())
                .concat("/sell/wechat/authorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }
}
