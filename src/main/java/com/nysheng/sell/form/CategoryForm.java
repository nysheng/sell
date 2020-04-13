package com.nysheng.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 商品类目信息表单数据
 *
 * @author nysheng
 * 2020/4/13 19:19
 */
@Data
public class CategoryForm {
    //类目id
    private Integer categoryId;
    //类目名字
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;
    //类目编号
    private Integer categoryType;
}
