package com.nysheng.sell.controller;

import com.nysheng.sell.dataobject.ProductCategory;
import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.form.ProductForm;
import com.nysheng.sell.service.ProductCategoryService;
import com.nysheng.sell.service.ProductInfoService;
import com.nysheng.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
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
    @Autowired
    private ProductCategoryService productCategoryService;

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

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value="productId",required = false)String productId,Map<String,Object> map){
        //查找类目信息
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        if(!StringUtils.isEmpty(productId)){
            //修改
            try {
                ProductInfo productInfo=productInfoService.findOne(productId);
                map.put("productInfo",productInfo);
            }catch (SellException e){
                map.put("msg",e.getMessage());
                map.put("redirectUrl","/sell/seller/product/list");
                return new ModelAndView("common/error",map);
            }
        }
        return new ModelAndView("product/index",map);
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError());
            map.put("redirectUrl","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo=new ProductInfo();
        if(!StringUtils.isEmpty(productForm.getProductId())){
            try{
                productInfo=productInfoService.findOne(productForm.getProductId());
            }catch (SellException e){
                map.put("msg",e.getMessage());
                map.put("redirectUrl","/sell/seller/product/list");
                return new ModelAndView("common/error",map);
            }
        }else{
            productForm.setProductId(KeyUtil.getUniqueKey());
        }
        BeanUtils.copyProperties(productForm,productInfo);
        productInfoService.save(productInfo);
        map.put("redirectUrl","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}