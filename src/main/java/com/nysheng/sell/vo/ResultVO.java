package com.nysheng.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回商品的最外层信息
 *
 * @author nysheng
 * 2020/3/31 11:28
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -447475848862926953L;
    private Integer code;//错误码
    private String msg;//提示信息
    private T data;//返回的具体内容
    public ResultVO<T> success(){
        this.code=0;
        this.msg="成功";
        return this;
    }
    public ResultVO<T> fail(Integer code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
        return this;
    }
}
