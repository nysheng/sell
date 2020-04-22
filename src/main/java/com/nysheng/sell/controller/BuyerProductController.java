package com.nysheng.sell.controller;

import com.nysheng.sell.dataobject.ProductCategory;
import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.service.impl.ProductCategoryServiceImpl;
import com.nysheng.sell.service.impl.ProductInfoServiceImpl;
import com.nysheng.sell.vo.ProductInfoVO;
import com.nysheng.sell.vo.ProductVO;
import com.nysheng.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author nysheng
 * 2020/3/31 11:14
 */
@RestController
@RequestMapping("/buyer/product")
@EnableCaching
public class BuyerProductController {
    @Autowired
    private ProductInfoServiceImpl infoService;
    @Autowired
    private ProductCategoryServiceImpl categoryService;
    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO<List<ProductVO>> list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList= infoService.findUpAll();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList= productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        ResultVO<List<ProductVO>> resultVO=new ResultVO<>();
        List<ProductVO> datas=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> list=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    list.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(list);
            datas.add(productVO);
        }
        resultVO.setData(datas);
        return resultVO.success();
    }
}
