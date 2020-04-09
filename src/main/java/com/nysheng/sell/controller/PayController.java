package com.nysheng.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.service.OrderMasterService;
import com.nysheng.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 微信支付
 *
 * @author nysheng
 * 2020/4/7 16:41
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId, @RequestParam("returnUrl")String returnUrl, Map<String,Object> map){
        //1.查询订单
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if(orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }
    //异步通知支付结果
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        PayResponse payResponse = payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}
