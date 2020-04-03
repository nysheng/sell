package com.nysheng.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 *
 * @author nysheng
 * @date 2020/3/30 22:42
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code,String message){
        this.code=code;this.message=message;
    }
}