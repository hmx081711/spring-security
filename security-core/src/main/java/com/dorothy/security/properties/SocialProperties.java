package com.dorothy.security.properties;

public class SocialProperties {

    private String qqProcessUrl;

    private QQproperties qq = new QQproperties();


    public QQproperties getqq() {
        return qq;
    }

    public void setqq(QQproperties qQproperties) {
        this.qq = qQproperties;
    }

    public String getQqProcessUrl() {
        return qqProcessUrl;
    }

    public void setQqProcessUrl(String qqProcessUrl) {
        this.qqProcessUrl = qqProcessUrl;
    }
}
