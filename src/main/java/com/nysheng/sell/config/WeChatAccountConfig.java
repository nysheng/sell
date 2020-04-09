package com.nysheng.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信用户配置
 *
 * @author nysheng
 * 2020/4/6 18:17
 */
@ConfigurationProperties(prefix = "wechat")
@Data
@Component
public class WeChatAccountConfig {
    private String weAppId;
    private String weAppSecret;
    private String mchId;//商户号
    private String mchKey;//商户密匙
    private String keyPath;//商户证书路径
    private String notifyUrl;//微信支付异步通知地址
}
