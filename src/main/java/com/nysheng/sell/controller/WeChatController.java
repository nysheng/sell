package com.nysheng.sell.controller;

import com.nysheng.sell.config.ProjectUrlConfig;
import com.nysheng.sell.enums.ResultEnum;
import com.nysheng.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * 微信接口
 *
 * @author nysheng
 * 2020/4/6 16:47
 */
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        String url=projectUrlConfig.getWxMpAuthorizeUrl()+"/sell/wechat/userInfo";
        //拼接授权地址
        String resultUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        //重定向到授权地址，授权后微信端会继而重定向到url并带上参数code与state
        return "redirect:"+resultUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code")String code,@RequestParam("state")String returnUrl ){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】获得的code:{}，state:{}",code,returnUrl);
            throw new SellException(ResultEnum.WECHAT_CP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openid=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openid;
    }
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl")String returnUrl){
        String url=projectUrlConfig.getWxOpenAuthorizeUrl()+"/sell/wechat/qrUserInfo";
        //拼接授权地址
        String resultUrl =wxOpenService.buildQrConnectUrl(url,WxConsts.QrConnectScope.SNSAPI_LOGIN,URLEncoder.encode(returnUrl));
        return "redirect:"+resultUrl;
    }
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code")String code,@RequestParam("state")String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】获得的code:{}，state:{}",code,returnUrl);
            throw new SellException(ResultEnum.WECHAT_OPEN_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openid=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openid;
    }
}
