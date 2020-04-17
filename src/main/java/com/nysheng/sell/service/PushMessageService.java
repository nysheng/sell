package com.nysheng.sell.service;

import com.nysheng.sell.dto.OrderDTO;

/**
 * 消息推送Service接口
 *
 * @author nysheng
 * 2020/4/17 19:47
 */
public interface PushMessageService {
    void orderStatus(OrderDTO orderDTO);
}
