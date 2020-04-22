package com.nysheng.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 *
 * @author nysheng
 * 2020/3/31 11:38
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 9099276393461174421L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
