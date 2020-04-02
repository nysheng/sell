package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductCategory;
import com.nysheng.sell.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目Service层实现类
 *
 * @author nysheng
 * @date 2020/3/30 17:03
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository reposiory;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return reposiory.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return reposiory.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return reposiory.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return reposiory.save(productCategory);
    }
}
