package com.nysheng.sell.service.impl;

import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.service.OrderMasterService;
import com.nysheng.sell.service.PushMessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 推送消息Service测试类
 *
 * @author nysheng
 * 2020/4/17 20:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PushMessageServiceImplTest {
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private OrderMasterService orderMasterService;
    @Test
    void orderStatus() {
        OrderDTO orderDTO=orderMasterService.findOne("1585832497684503474");
        pushMessageService.orderStatus(orderDTO);
    }
}