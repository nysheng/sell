package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息 DAO层测试
 *
 * @author nysheng
 * @date 2020/3/30 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    void findOneTest(){
        ProductInfo result = repository.findById("aaa").orElse(null);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findAllTest(){
        List<ProductInfo> results = repository.findAll();
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    void findUpAllTest() {
        List<ProductInfo> results = repository.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    //@Transactional
    void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("aaa");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.00));
        productInfo.setProductDescription("很好喝的皮蛋粥");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setProductStock(100);
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(1);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotEquals(null,result);
    }
}