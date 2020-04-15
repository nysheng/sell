package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.SellerInfo;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import com.nysheng.sell.repository.SellerInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家信息Service层实现类
 *
 * @author nysheng
 * 2020/4/14 16:01
 */
@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService{
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findByOpenid(String openid) {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        if(sellerInfo==null){
            log.error("【卖家信息查询】指定微信openid不存在，openid:{}",openid);
            throw new SellException(ResultEnum.SELLER_OPENID_NOT_EXIST);
        }
        return sellerInfo;
    }
}
