package com.nysheng.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 商品信息表单数据
 *
 * @author nysheng
 * 2020/4/13 15:40
 */
@Data
public class ProductForm {
    private String productId;//商品id
    @NotEmpty(message = "商品名不能空")
    private String productName;//商品名
    private BigDecimal productPrice;//单价
    private Integer productStock;//库存
    private String productDescription;//描述
    @NotEmpty(message = "图片不能为空")
    private String productIcon;//图标
    private Integer categoryType;//类目编号
}
