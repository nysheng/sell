package com.nysheng.sell.service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 微信支付
 *
 * @author nysheng
 * 2020/4/7 16:56
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderMasterService orderMasterService;

    private final String ORDER_NAME="微信点餐订单";

    /**
     * 微信支付 发起支付
     * @param orderDTO
     * @return
     */
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest=new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);
        log.info("【微信支付】发起支付，request:{}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response:{}",payResponse);
        return payResponse;
    }

    /**
     * 微信支付异步通知处理-修改订单状态
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {
        //验证签名
        //支付状态
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，response:{}",payResponse);
        //查询订单
        OrderDTO orderDTO = orderMasterService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if(orderDTO==null){
            log.error("【微信支付】异步通知，订单不存在。orderId:{}", payResponse.getOrderId());
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，订单金额不一致。orderId:{}，微信通知金额={}，系统金额={}",payResponse.getOrderId(),orderDTO.getOrderAmount(),payResponse.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改支付状态
        orderMasterService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 微信支付 退款
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        log.info("【微信退款】request:{}",refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response:{}",refundResponse);

        return refundResponse;
    }
}
