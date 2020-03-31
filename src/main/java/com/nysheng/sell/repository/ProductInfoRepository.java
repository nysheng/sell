package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品信息 DAO层接口
 *
 * @author nysheng
 * @date 2020/3/30 20:34
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
