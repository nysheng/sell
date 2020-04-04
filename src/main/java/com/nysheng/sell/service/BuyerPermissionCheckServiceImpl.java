package com.nysheng.sell.service;

import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户权限校验实现类
 *
 * @author nysheng
 * 2020/4/4 10:42
 */
@Service
@Slf4j
public class BuyerPermissionCheckServiceImpl implements BuyerPermissionCheckService {
    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderDTO buyerOrderCheckAndDetail(String openId, String orderId) {
        OrderDTO orderDTO=this.buyerOrderCheck(openId, orderId);
        if(orderDTO==null){
            log.error("【查询订单详情】订单不存在，orderId:{}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }
    @Override
    public OrderDTO buyerOrderCheckAndCancel(String openId, String orderId) {
        OrderDTO orderDTO = this.buyerOrderCheck(openId, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】订单不存在，orderId:{}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO result = orderMasterService.cancel(orderDTO);
        return result;
    }

    //检查当前订单是否属于当前用户
    private OrderDTO buyerOrderCheck(String openId, String orderId) {
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(!openId.equalsIgnoreCase(orderDTO.getBuyerOpenid())){
            log.error("【权限校验】当前订单不属于当前用户，openId:{}，orderId:{}",openId,orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
