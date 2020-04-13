package com.nysheng.sell.controller;

import com.nysheng.sell.dataobject.ProductCategory;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.form.CategoryForm;
import com.nysheng.sell.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目信息
 *
 * @author nysheng
 * 2020/4/13 17:34
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value="categoryId",required = false)Integer categoryId,Map<String,Object> map){
        if(!StringUtils.isEmpty(categoryId)){
            //修改
            try {
                ProductCategory productCategory = productCategoryService.findOne(categoryId);
                map.put("productCategory",productCategory);
            }catch (SellException e){
                map.put("msg",e.getMessage());
                map.put("redirectUrl","/sell/seller/category/list");
                return new ModelAndView("common/error",map);
            }
        }
        return new ModelAndView("category/index",map);
    }
    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm categoryForm,Map<String,Object> map){
        ProductCategory productCategory=new ProductCategory();
        if(!StringUtils.isEmpty(categoryForm.getCategoryId())){
            //修改
            try {
                productCategory=productCategoryService.findOne(categoryForm.getCategoryId());
            }catch (SellException e){
                map.put("msg",e.getMessage());
                map.put("redirectUrl","/sell/seller/category/list");
                return new ModelAndView("common/error",map);
            }
        }
        BeanUtils.copyProperties(categoryForm,productCategory);
        productCategoryService.save(productCategory);
        map.put("redirectUrl","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
