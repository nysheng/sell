package com.nysheng.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.nysheng.sell.dto.OrderDTO;

/**
 * 微信支付
 *
 * @author nysheng
 * 2020/4/7 16:55
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);
    RefundResponse refund(OrderDTO orderDTO);
}
