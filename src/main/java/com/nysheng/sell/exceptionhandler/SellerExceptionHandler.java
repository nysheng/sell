package com.nysheng.sell.exceptionhandler;

import com.nysheng.sell.config.ProjectUrlConfig;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.exception.SellerAuthorizeException;
import com.nysheng.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 *
 * @author nysheng
 * 2020/4/16 16:46
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWxOpenAuthorizeUrl())
                .concat("/sell/wechat/authorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }
    //拦截返回SellException
    //@ResponseStatus(HttpStatus.FORBIDDEN)用于返回http请求状态403
    @ExceptionHandler(value=SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResultVO<String> handlerSellException(SellException e){
        ResultVO resultVO=new ResultVO();
        return resultVO.fail(e.getCode(),e.getMessage(),null);
    }
}
