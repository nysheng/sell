package com.nysheng.sell.enums;


/**
 * 异常消息
 *
 * @author nysheng
 * @date 2020/4/2 17:54
 */
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存异常");
    private Integer code;
    private String msg;
    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public Integer getCode(){
        return this.code;
    }
}
