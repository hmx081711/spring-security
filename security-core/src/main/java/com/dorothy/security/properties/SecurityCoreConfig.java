package com.dorothy.security.properties;

import com.dorothy.security.properties.ValidateCode.ValidateCodeProperties;
import com.dorothy.security.properties.ValidateCode.generator.DefaultImageValidCodeGenerator;
import com.dorothy.security.properties.ValidateCode.generator.ValidateCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class, ValidateCodeProperties.class})
public class SecurityCoreConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        return new DefaultImageValidCodeGenerator();
    }
}
