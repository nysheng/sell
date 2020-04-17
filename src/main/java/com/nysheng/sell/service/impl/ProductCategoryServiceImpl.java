package com.nysheng.sell.service.impl;

import com.nysheng.sell.dataobject.ProductCategory;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.repository.ProductCategoryRepository;
import com.nysheng.sell.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目Service层实现类
 *
 * @author nysheng
 * 2020/3/30 17:03
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository reposiory;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        ProductCategory productCategory= reposiory.findById(categoryId).orElse(null);
        if(productCategory==null){
            log.error("【查询类目信息】类目不存在，categoryId:{}",categoryId);
            throw new SellException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        return productCategory;
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
