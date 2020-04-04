package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

/**
 * 订单 DAO层测试
 *
 * @author nysheng
 * 2020/3/31 21:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    @Test
    void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("nys");
        orderMaster.setBuyerAddress("幸福路97号");
        orderMaster.setBuyerPhone("12345678910");
        orderMaster.setBuyerOpenid("a123");
        orderMaster.setOrderAmount(new BigDecimal(100.9));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findByBuyerOpenidTest() {
        Page<OrderMaster> page = repository.findByBuyerOpenid("a123", PageRequest.of(0, 2));
        Assert.assertNotEquals(null,page);
    }
}