package com.nysheng.sell.dataobject;

import com.nysheng.sell.enums.OrderStatusEnum;
import com.nysheng.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表 order_master
 *
 * @author nysheng
 * 2020/3/31 20:08
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
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

    @Override
    public String toString() {
        return "OrderMaster{" +
                "orderId='" + orderId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", buyerOpenid='" + buyerOpenid + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
