package com.nysheng.sell.enums;


/**
 * 异常消息
 *
 * @author nysheng
 * 2020/4/2 17:54
 */
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    ORDER_OWNER_ERROR(2,"当前订单不属于当前用户"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存异常"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),
    CAR_EMPTY(18,"购物车为空"),
    WECHAT_CP_ERROR(19,"微信公众号账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(20,"微信支付异步通知金额校验不通过"),
    ;
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
