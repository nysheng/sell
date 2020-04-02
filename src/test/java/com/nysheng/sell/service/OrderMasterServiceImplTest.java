package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 订单 service层测试
 *
 * @author nysheng
 * @date 2020/4/2 19:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class OrderMasterServiceImplTest {
    @Autowired
    private OrderMasterServiceImpl orderMasterService;
    @Test
    void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerAddress("幸福路10号");
        orderDTO.setBuyerOpenid("zhang3");
        orderDTO.setBuyerPhone("12345678910");
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("aaa");
        orderDetail.setProductQuantity(20);
        OrderDetail orderDetail2=new OrderDetail();
        orderDetail2.setProductId("aaa");
        orderDetail2.setProductQuantity(30);
        orderDTO.setOrderDetailList(Arrays.asList(orderDetail,orderDetail2));
        OrderDTO result = orderMasterService.create(orderDTO);
        log.info("【创建订单】result={}",result);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findOne() {
    }

    @Test
    void findList() {
    }

    @Test
    void cancel() {
    }

    @Test
    void finish() {
    }

    @Test
    void paid() {
    }
}