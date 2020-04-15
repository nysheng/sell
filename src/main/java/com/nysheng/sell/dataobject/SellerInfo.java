package com.nysheng.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息表_seller_info
 *
 * @author nysheng
 * 2020/4/14 14:46
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;//用户id
    private String username;
    private String password;
    private String openid;//卖家微信openid

    @Override
    public String toString() {
        return "SellerInfo{" +
                "sellerId='" + sellerId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
