package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息 service层接口
 *
 * @author nysheng
 * 2020/3/30 20:37
 */
public interface ProductInfoService {
    //查找单个商品
    ProductInfo findOne(String productId);
    //分页查找所有商品
    Page<ProductInfo> findAll(Pageable pageable);
    //查找所有上架商品
    List<ProductInfo> findUpAll();
    //查找所有下架商品
    List<ProductInfo> findDownAll();
    //添加商品
    ProductInfo save(ProductInfo productInfo);
    //扣库存
    void increaseStock(List<CartDTO> cartDTOList);
    //添加库存
    void decreaseStock(List<CartDTO> cartDTOList);
    //上架
    ProductInfo onSale(ProductInfo productInfo);
    //下架
    ProductInfo offSale(ProductInfo productInfo);
}
