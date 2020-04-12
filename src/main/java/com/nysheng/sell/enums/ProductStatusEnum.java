package com.nysheng.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 *
 * @author nysheng
 * 2020/3/30 22:42
 */
@Getter
public enum ProductStatusEnum implements StatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer status;
    private String message;
    ProductStatusEnum(Integer status,String message){
        this.status=status;this.message=message;
    }
}
