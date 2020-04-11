package com.nysheng.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.enums.OrderStatusEnum;
import com.nysheng.sell.enums.PayStatusEnum;
import com.nysheng.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  订单数据传输对象
 *
 * @author nysheng
 * 2020/4/2 16:34
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;//订单表id
    private String buyerName;//买家名字
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//买家微信ID
    private BigDecimal orderAmount;//订单总金额
    private Integer orderStatus= OrderStatusEnum.NEW.getStatus();//订单状态，默认0新订单
    private Integer payStatus= PayStatusEnum.WAIT.getStatus();//支付状态，默认0未支付
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private List<OrderDetail> orderDetailList;//订单详情列表
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByStatus(this.orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByStatus(this.payStatus,PayStatusEnum.class);
    }
}
