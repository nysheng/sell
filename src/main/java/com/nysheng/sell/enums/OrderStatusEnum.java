package com.nysheng.sell.enums;

/**
 * 订单状态
 *
 * @author nysheng
 * @date 2020/3/31 20:23
 */
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消");
    private Integer status;
    private String msg;
    OrderStatusEnum(Integer status,String msg){
        this.status=status;
        this.msg=msg;
    }
    public Integer getStatus(){
        return this.status;
    }
}
