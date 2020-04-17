package com.nysheng.sell.service.impl;

import com.nysheng.sell.dataobject.ProductInfo;
import com.nysheng.sell.dto.CartDTO;
import com.nysheng.sell.enums.ProductStatusEnum;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.repository.ProductInfoRepository;
import com.nysheng.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息 Service实现类
 *
 * @author nysheng
 * 2020/3/30 20:56
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo=repository.findById(productId).orElse(null);
        if(productInfo==null){
            log.error("【查找商品信息】商品不存在，productId:{}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getStatus());
    }
    @Override
    public List<ProductInfo> findDownAll() {
        return repository.findByProductStatus(ProductStatusEnum.DOWN.getStatus());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if(productInfo==null){
                log.error("【增加商品库存】商品不存在，productId:{}",cartDTO.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer intResult=productInfo.getProductStock()+cartDTO.getProductStock();
            productInfo.setProductStock(intResult);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer intResult=productInfo.getProductStock()-cartDTO.getProductStock();
            if(intResult<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(intResult);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(ProductInfo productInfo) {
        //检查商品状态
        if(productInfo.getProductStatus()!=ProductStatusEnum.DOWN.getStatus()){
            log.error("【设置商品在架】商品状态错误，productInfo:{}",productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //修改商品状态
        productInfo.setProductStatus(0);
        //更新
        ProductInfo result = repository.save(productInfo);
        return result;
    }

    @Override
    public ProductInfo offSale(ProductInfo productInfo) {
        //检查商品状态
        if(productInfo.getProductStatus()!=ProductStatusEnum.UP.getStatus()){
            log.error("【设置商品下架】商品状态错误，productInfo:{}",productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //修改商品状态
        productInfo.setProductStatus(1);
        //更新
        ProductInfo result = repository.save(productInfo);
        return result;
    }


}
