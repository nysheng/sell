package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.OrderStatusEnum;
import com.nysheng.sell.enums.PayStatusEnum;
import com.nysheng.sell.service.impl.OrderMasterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 * 订单 service层测试
 *
 * @author nysheng
 * 2020/4/2 19:43
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
        OrderDTO result = orderMasterService.findOne("1585832497684503474");
        System.out.println(result);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findList() {
        Page<OrderDTO> orderDTOPage = orderMasterService.findList("zhang3", PageRequest.of(0, 2));
        Assert.assertNotEquals(null,orderDTOPage);
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderMasterService.findOne("1585832497684503474");
        OrderDTO result = orderMasterService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getStatus(),result.getOrderStatus());
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderMasterService.findOne("1585832497684503474");
        OrderDTO result = orderMasterService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getStatus(),result.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderMasterService.findOne("1585832497684503474");
        OrderDTO result = orderMasterService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getStatus(),result.getPayStatus());
    }
    @Test
    void list(){
        Page<OrderDTO> list = orderMasterService.findList(PageRequest.of(0, 10));
        Assert.assertNotEquals(0,list.getTotalElements());
    }
}