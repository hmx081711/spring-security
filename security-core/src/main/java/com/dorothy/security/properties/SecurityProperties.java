package com.dorothy.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.dorothy")
public class SecurityProperties {

    private BrowserSecurityProperties browser = new BrowserSecurityProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserSecurityProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserSecurityProperties browser) {
        this.browser = browser;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties socialProperties) {
        this.social = socialProperties;
    }
}
