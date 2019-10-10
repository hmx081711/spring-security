package com.dorothy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("当前登录的用户为:"+username);
        return loadUserById(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        logger.info("当前登录的用户为:"+s);
        return (SocialUserDetails) loadUserById(s);
    }


    private UserDetails loadUserById(String str) {
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码为:"+password);
        //校验用户信息的操作
        return new User(str,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
