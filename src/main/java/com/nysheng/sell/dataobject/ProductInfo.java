package com.nysheng.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品信息表 product_info
 *
 * @author nysheng
 * 2020/3/30 20:09
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    private String productId;
    private String productName;//商品名
    private BigDecimal productPrice;//单价
    private Integer productStock;//库存
    private String productDescription;//描述
    private String productIcon;//图标
    private Integer productStatus;//商品状态，0正常1下架
    private Integer categoryType;//类目编号

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", productStatus=" + productStatus +
                ", categoryType=" + categoryType +
                '}';
    }
}
