package com.nysheng.sell.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.form.OrderForm;
import com.nysheng.sell.service.impl.BuyerPermissionCheckServiceImpl;
import com.nysheng.sell.service.OrderMasterService;
import com.nysheng.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 买家订单 controller层实现
 *
 * @author nysheng
 * 2020/4/3 18:52
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerPermissionCheckServiceImpl buyerPermissionCheckService;

    //1创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm:{}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        Gson gson=new Gson();
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try{
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【创建订单】对象转换错误，string:{}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if(CollectionUtils.isEmpty(orderDetailList)){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CAR_EMPTY);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO  result = orderMasterService.create(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",orderDTO.getOrderId());
        ResultVO<Map<String,String>> resultVO=new ResultVO<>();
        resultVO.setData(map);
        return resultVO.success();
    }
    //2订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>>  list(@RequestParam(value = "openId",required = true) String openId,@RequestParam(value = "page",defaultValue ="0") Integer page,@RequestParam(value = "size",defaultValue = "10") Integer size){
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(openId, PageRequest.of(page, size));
        List<OrderDTO> orderDTOList = orderDTOPage.getContent();
        ResultVO<List<OrderDTO>> resultVO=new ResultVO<>();
        resultVO.setData(orderDTOList);
        return resultVO.success();
    }
    //3订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId,@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerPermissionCheckService.buyerOrderCheckAndDetail(openId, orderId);
        //装填数据
        ResultVO<OrderDTO> resultVO=new ResultVO<>();
        resultVO.setData(orderDTO);
        return resultVO.success();
    }
    //4取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerPermissionCheckService.buyerOrderCheckAndCancel(openId, orderId);
        return new ResultVO().success();
    }
}
