package com.nysheng.sell.enums;

/**
 * 支付状态
 *
 * @author nysheng
 * @date 2020/3/31 20:31
 */
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");
    private Integer status;
    private String msg;
    PayStatusEnum(Integer status,String msg){
        this.status=status;
        this.msg=msg;
    }
    public Integer getStatus(){
        return this.status;
    }
}
