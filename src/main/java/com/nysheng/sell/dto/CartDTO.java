package com.nysheng.sell.dto;

import lombok.Data;

/**
 * 库存数据传输对象
 *
 * @author nysheng
 * 2020/4/2 19:19
 */
@Data
public class CartDTO {
    private String productId;//商品id
    private Integer productStock;//库存
    public CartDTO(){}
    public CartDTO(String productId,Integer productStock){
        this.productId=productId;
        this.productStock=productStock;
    }
}
