package com.nysheng.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情表
 *
 * @author nysheng
 * 2020/3/31 20:39
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    @Id
    private String detailId;//详情表id
    private String orderId;//订单id
    private String productId;//商品id
    private String productName;//商品名字
    private BigDecimal productPrice;//商品价格
    private Integer productQuantity;//商品数量
    private String productIcon;//商品图片

    @Override
    public String toString() {
        return "OrderDetail{" +
                "detailId='" + detailId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productIcon='" + productIcon + '\'' +
                '}';
    }
}
