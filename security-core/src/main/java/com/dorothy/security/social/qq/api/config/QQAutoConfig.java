package com.dorothy.security.social.qq.api.config;

import com.dorothy.security.properties.SecurityProperties;
import com.dorothy.security.social.qq.api.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "com.dorothy.social.qq",name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(securityProperties.getSocial().getqq().getProviderId(),securityProperties.getSocial().getqq().getAppId()
                ,securityProperties.getSocial().getqq().getAppSecret());
    }
}
