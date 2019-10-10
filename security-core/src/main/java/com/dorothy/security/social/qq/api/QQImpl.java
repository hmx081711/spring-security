package com.dorothy.security.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private static final String GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private String appid;

    private String openid;

    private ObjectMapper objectMapper = new ObjectMapper();

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public QQImpl(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid = appid;
        String url = String.format(GET_OPEN_ID, accessToken);
        String result = this.getRestTemplate().getForObject(url, String.class);
        System.err.println(result);
        String openid = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
        this.openid = openid;
    }

    @Override
    public UserInfo getUserInfo(){

        String url = String.format(GET_USER_INFO, appid, openid);
        String result = this.getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, UserInfo.class);
            userInfo.setOpenId(openid);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
        return userInfo;
    }
}
