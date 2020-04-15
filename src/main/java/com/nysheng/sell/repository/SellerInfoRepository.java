package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息DAO层
 *
 * @author nysheng
 * 2020/4/14 14:50
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);//根据卖家微信openid查找信息
}
