package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类目Service测试类
 *
 * @author nysheng
 * @date 2020/3/30 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl categoryService;
    @Test
    void findOneTest() {
        ProductCategory result= categoryService.findOne(1);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findAllTest() {
        List<ProductCategory> results= categoryService.findAll();
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    void findByCategoryTypeInTest() {
        List<ProductCategory> results= categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    @Transactional
    void saveTest() {
        ProductCategory result= categoryService.save(new ProductCategory("男生最爱",3));
        Assert.assertNotEquals(null,result);
    }
}