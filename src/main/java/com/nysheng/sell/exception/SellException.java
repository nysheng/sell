package com.nysheng.sell.exception;

import com.nysheng.sell.enums.ResultEnum;

/**
 * 异常处理
 *
 * @author nysheng
 * @date 2020/4/2 17:52
 */
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String msg){
        super(msg);
        this.code=code;
    }
}
