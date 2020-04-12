package com.nysheng.sell.controller;

import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家商品信息
 *
 * @author nysheng
 * 2020/4/12 13:40
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "3") Integer size, Map<String,Object> map) {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(page-1, size));
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("product/list",map);
    }
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId")String productId,Map<String,Object> map){
        try {
            ProductInfo productInfo=productInfoService.findOne(productId);
            productInfoService.offSale(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("redirectUrl","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("redirectUrl","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId")String productId,Map<String,Object> map){
        try {
            ProductInfo productInfo=productInfoService.findOne(productId);
            productInfoService.onSale(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("redirectUrl","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("redirectUrl","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
