package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 订单详情 DAO层测试
 *
 * @author nysheng
 * @date 2020/4/1 17:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("xq0001");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("aaa");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(10));
        orderDetail.setProductQuantity(10);
        orderDetail.setProductIcon("http://xxxx.jpg");
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotEquals(null,result);
    }
    @Test
    void findByOrOrderIdTest() {
        List<OrderDetail> results = repository.findByOrOrderId("123456");
        Assert.assertNotEquals(0,results.size());
    }
}