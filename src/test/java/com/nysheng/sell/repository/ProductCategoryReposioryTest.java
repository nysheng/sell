package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * 类目表测试
 * @author nysheng
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryReposioryTest {
    @Autowired
    private ProductCategoryReposiory repository;

    /**
     * 查找
     */
    @Test
    public void findOneTest(){
        ProductCategory productCategory=repository.findById(1).orElse(null);
        Assert.assertNotEquals(null,productCategory);
    }
    /**
     * 新增
     */
    @Test
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory("蔬菜类",2);
        final ProductCategory result = repository.save(productCategory);
        Assert.assertNotEquals(null,result);
    }
    /**
     * 修改
     */
    @Test
    public void setTest(){
        ProductCategory productCategory=repository.findById(4).orElse(null);
        productCategory.setCategoryName("肉类");
        final ProductCategory result = repository.save(productCategory);
        Assert.assertNotEquals(null,result);
    }
    /**
     * 通过类目编号查找
     */
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(1,2,3);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}