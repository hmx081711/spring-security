package com.dorothy.security.properties;


public class BrowserSecurityProperties {

    private String loginPage = "/dorothy-login.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String browser) {
        this.loginPage = browser;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
