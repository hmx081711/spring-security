package com.dorothy.security.properties.ValidateCode;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security")
public class ValidateCodeProperties {

    private ImageCodeProperties imageCode = new ImageCodeProperties();


    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCodeProperties) {
        this.imageCode = imageCodeProperties;
    }
}
