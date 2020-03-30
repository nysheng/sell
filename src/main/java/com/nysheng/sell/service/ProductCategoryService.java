package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目类service层接口
 *
 * @author nysheng
 * @date 2020/3/30 16:51
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
