package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 卖家信息DAO测试类
 *
 * @author nysheng
 * 2020/4/14 15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Test
    void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setSellerId("2");
        sellerInfo.setOpenid("2CDgco3n5FBdXUJOd9rZ46I");
        sellerInfo.setUsername("张三");
        sellerInfo.setPassword("dd123");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertTrue("新增卖家信息失败",result!=null);
    }
    @Test
    void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("1CDgco3n5FBdXUJOd9rZ46I");
        Assert.assertTrue("卖家openid不存在",sellerInfo!=null);
    }
}