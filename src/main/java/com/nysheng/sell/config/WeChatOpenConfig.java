package com.nysheng.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信开放平台配置
 *
 * @author nysheng
 * 2020/4/14 16:26
 */
@Component
public class WeChatOpenConfig {
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;
    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxMpService;
    }
    private WxMpConfigStorage wxOpenConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage=new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getOpenAppId());
        return wxMpConfigStorage;
    }
}
