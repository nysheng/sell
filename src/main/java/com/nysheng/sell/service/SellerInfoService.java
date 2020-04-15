package com.nysheng.sell.service;

import com.nysheng.sell.dataobject.SellerInfo;

/**
 * 卖家信息Service层接口
 *
 * @author nysheng
 * 2020/4/14 14:54
 */
public interface SellerInfoService {
    //根据微信openid查询卖家信息
    SellerInfo findByOpenid(String openid);
}
