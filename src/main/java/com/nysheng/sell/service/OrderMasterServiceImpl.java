package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.OrderDetail;
import com.nysheng.sell.dataobject.OrderMaster;
import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.dto.CartDTO;
import com.nysheng.sell.dto.OrderDTO;
import com.nysheng.sell.enums.OrderStatusEnum;
import com.nysheng.sell.enums.PayStatusEnum;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.repository.OrderDetailRepository;
import com.nysheng.sell.repository.OrderMasterRepository;
import com.nysheng.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单 service层实现类
 *
 * @author nysheng
 * 2020/4/2 16:46
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.getUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        //1查询商品（数量，单价）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2计算总价
            orderAmount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.
                    getProductQuantity())).add(orderAmount);
            //3订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //4订单入库
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);
        //5扣库存
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO findOne(String orderId) {
        //1查询订单
        OrderMaster result = orderMasterRepository.findById(orderId).orElse(null);
        if(result==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //3封装数据
        OrderDTO orderDTO =new OrderDTO();
        BeanUtils.copyProperties(result,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    @Transactional
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        //1查找订单表数据
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //2遍历订单数据
        List<OrderDTO> orderDTOList=new ArrayList<OrderDTO>();
        for(OrderMaster orderMaster:orderMasterPage.getContent()){
            OrderDTO orderDTO=new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            orderDTOList.add(orderDTO);
        }
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        //查找订单表数据
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> list = new ArrayList<>();
        //遍历订单数据
        for(OrderMaster orderMaster: orderMasterPage.getContent()){
            OrderDTO orderDTO=new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            list.add(orderDTO);
        }
        return new PageImpl<OrderDTO>(list,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //1判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("【取消订单】订单状态错误，orderId:{}，orderStatus:{}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【取消订单】订单更新失败，orderMaster:{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情，orderDTO:{}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //4如果已支付，退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getStatus())){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //1判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("【完结订单】订单状态错误，orderId:{}，orderStatus:{}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【完结订单】订单更新失败，orderMaster:{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //1判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("【支付订单】订单状态错误，orderId:{}，orderStatus:{}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getStatus())){
            log.error("【支付订单】订单支付状态不正确，orderId:{}，orderPayStatus:{}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getStatus());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【支付订单】订单更新失败，orderMaster:{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
