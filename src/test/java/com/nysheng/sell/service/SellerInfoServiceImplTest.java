package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 卖家信息Service层测试类
 *
 * @author nysheng
 * 2020/4/14 16:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Test
    void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoService.findByOpenid("1CDgco3n5FBdXUJOd9rZ46I");
        Assert.assertTrue("卖家信息不存在当前openid",sellerInfo!=null);
    }
}