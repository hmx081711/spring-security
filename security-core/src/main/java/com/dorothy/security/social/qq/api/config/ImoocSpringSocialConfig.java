package com.dorothy.security.social.qq.api.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class ImoocSpringSocialConfig extends SpringSocialConfigurer {

    private String qqProcessUrl;

    public ImoocSpringSocialConfig(String qqProcessUrl) {
        this.qqProcessUrl = qqProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(qqProcessUrl);
        return super.postProcess(object);
    }
}
