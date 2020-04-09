package com.nysheng.sell.service;

import com.nysheng.sell.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 微信支付 测试
 *
 * @author nysheng
 * 2020/4/7 17:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderMasterService orderMasterService;
    @Test
    void create() {
        OrderDTO orderDTO=orderMasterService.findOne("1585920839412840644");
        payService.create(orderDTO);
    }
    @Test
    void refund(){
        OrderDTO orderDTO=orderMasterService.findOne("1585920839412840644");
        payService.refund(orderDTO);
    }
}