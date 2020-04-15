package com.nysheng.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目中用到的配置（硬编码）
 *
 * @author nysheng
 * 2020/4/14 19:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectUrlConfig {
    //微信公众平台授权路径
    private String wxMpAuthorizeUrl;
    //微信开放平台授权路径
    private String wxOpenAuthorizeUrl;
    //项目路径
    private String sell;
}
