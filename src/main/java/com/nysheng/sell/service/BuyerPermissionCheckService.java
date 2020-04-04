package com.nysheng.sell.service;

import com.nysheng.sell.dto.OrderDTO;

/**
 * 用户权限校验
 *
 * @author nysheng
 * 2020/4/4 10:40
 */
public interface BuyerPermissionCheckService {
    //权限校验并取消订单
    OrderDTO buyerOrderCheckAndCancel(String openId, String orderId);
    //权限校验并查询订单详情
    OrderDTO buyerOrderCheckAndDetail(String openId, String orderId);
}
