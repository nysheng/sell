package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.enums.ProductStatusEnum;
import com.nysheng.sell.service.impl.ProductInfoServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息 service实现类测试
 *
 * @author nysheng
 * 2020/3/30 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    void findOneTest() {
        ProductInfo result = productInfoService.findOne("aaa");
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findAllTest() {
        PageRequest pageRequest=PageRequest.of(0,2);
        Page<ProductInfo> results = productInfoService.findAll(pageRequest);
        Assert.assertNotEquals(null,results);
    }

    @Test
    void findUpAllTest() {
        List<ProductInfo> results = productInfoService.findUpAll();
        Assert.assertNotEquals(0,results.size());
    }
    @Test
    void findDownAllTest() {
        List<ProductInfo> results = productInfoService.findDownAll();
        Assert.assertNotEquals(0,results.size());
    }

    @Test
    @Transactional
    void saveTest() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("bbb");
        productInfo.setProductName("蔬菜粥");
        productInfo.setProductPrice(new BigDecimal(4.00));
        productInfo.setProductDescription("很好喝的蔬菜粥");
        productInfo.setProductStatus(ProductStatusEnum.UP.getStatus());
        productInfo.setProductStock(100);
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(1);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotEquals(null,result);
    }
}