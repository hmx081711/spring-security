package com.dorothy.security.social;

import com.dorothy.security.properties.SecurityProperties;
import com.dorothy.security.properties.SocialProperties;
import com.dorothy.security.social.qq.api.config.ImoocSpringSocialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository connectionRepository =  new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        connectionRepository.setTablePrefix("dorothy_");
        return connectionRepository;
    }


    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        String processUrl = securityProperties.getSocial().getQqProcessUrl();
        return new ImoocSpringSocialConfig(processUrl);
    }
}
