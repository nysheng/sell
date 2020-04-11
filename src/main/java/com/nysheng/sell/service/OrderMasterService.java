package com.nysheng.sell.service;

import com.nysheng.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单 Service层接口
 *
 * @author nysheng
 * 2020/3/31 20:56
 */
public interface OrderMasterService {
    //创建订单
    OrderDTO create(OrderDTO orderDTO);
    //查询单个订单
    OrderDTO findOne(String orderId);
    //查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    //查询订单列表
    Page<OrderDTO> findList(Pageable pageable);
    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);
    //完结订单
    OrderDTO finish(OrderDTO orderDTO);
    //支付订单
    OrderDTO paid(OrderDTO orderDTO);
}
