package com.nysheng.sell.controller;

import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单控制器
 *
 * @author nysheng
 * 2020/4/10 12:07
 */
@RestController
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "size",defaultValue = "3")Integer size, Map<String,Object>  map){
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(PageRequest.of(page-1, size));
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("order/list",map);
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常,{}",e);
            map.put("msg", e.getMessage());
            map.put("redirectUrl", "/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("redirectUrl","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            map.put("orderDTO",orderDTO);
        }catch (SellException e){
            log.info("【卖家订单详情】发生异常，{}",e);
            map.put("msg",e.getMessage());
            map.put("redirectUrl", "/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        return  new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.finish(orderDTO);
        }catch (SellException e){
            log.info("【卖家完结订单】发生异常，{}",e);
            map.put("msg",e.getMessage());
            map.put("redirectUrl", "/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("redirectUrl","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
