package com.nysheng.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信授权配置
 *
 * @author nysheng
 * 2020/4/6 18:01
 */
@Component
public class WeChatMpConfig {
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage=new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getWeAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getWeAppSecret());
        return wxMpConfigStorage;
    }
}
