package com.nysheng.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 买家订单 表单数据
 *
 * @author nysheng
 * 2020/4/3 19:48
 */
@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;//买家姓名
    @NotEmpty(message = "电话必填")
    private String phone;//买家电话
    @NotEmpty(message = "地址必填")
    private String address;//买家地址
    @NotEmpty(message = "openId必填")
    private String openId;//买家openid
    @NotEmpty(message = "购物车不能为空")
    private String items;//购物车
}
