package com.nysheng.sell.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置
 *
 * @author nysheng
 * 2020/4/7 17:43
 */
@Component
public class WeChatPayConfig {
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;
    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig());
        return bestPayService;
    }
    public WxPayConfig wxPayConfig(){
        WxPayConfig wxPayConfig=new WxPayConfig();
        wxPayConfig.setAppId(weChatAccountConfig.getWeAppId());
        wxPayConfig.setAppSecret(weChatAccountConfig.getWeAppSecret());
        wxPayConfig.setMchId(weChatAccountConfig.getMchId());
        wxPayConfig.setMchKey(weChatAccountConfig.getMchKey());
        wxPayConfig.setKeyPath(weChatAccountConfig.getKeyPath());
        wxPayConfig.setNotifyUrl(weChatAccountConfig.getNotifyUrl());
        return wxPayConfig;
    }
}
