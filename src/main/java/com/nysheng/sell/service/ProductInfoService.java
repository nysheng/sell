package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息 service层接口
 *
 * @author nysheng
 * @date 2020/3/30 20:37
 */
public interface ProductInfoService {
    ProductInfo findOne(String productId);
    Page<ProductInfo> findAll(Pageable pageable);
    List<ProductInfo> findUpAll();
    List<ProductInfo> findDownAll();
    ProductInfo save(ProductInfo productInfo);
}
