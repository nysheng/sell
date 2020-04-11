package com.nysheng.sell.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author nysheng
 * 2020/3/31 20:23
 */
@Getter
public enum OrderStatusEnum implements StatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消");
    private Integer status;
    private String msg;
    OrderStatusEnum(Integer status,String msg){
        this.status=status;
        this.msg=msg;
    }
}
