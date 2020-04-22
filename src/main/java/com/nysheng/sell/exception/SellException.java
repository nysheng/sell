package com.nysheng.sell.exception;

import com.nysheng.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * 异常处理
 *
 * @author nysheng
 * 2020/4/2 17:52
 */
@Getter
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
