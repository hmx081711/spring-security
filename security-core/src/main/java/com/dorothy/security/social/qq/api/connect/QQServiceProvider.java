package com.dorothy.security.social.qq.api.connect;

import com.dorothy.security.social.qq.api.QQ;
import com.dorothy.security.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appid;


    private static final String GET_AUTHORIZATION_CODE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String GET_ACCESS_TOKEN  = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appid,String clientSecret) {
        super(new OAuth2Template(appid,clientSecret,GET_AUTHORIZATION_CODE,GET_ACCESS_TOKEN));
        this.appid = appid;
    }

    @Override
    public QQ getApi(String accessToken) {

        return new QQImpl(accessToken,appid);
    }
}
