package com.dorothy.web.filter.config;

import com.dorothy.web.filter.TimeFilter;
import com.dorothy.web.filter.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private TimeInterceptor timeInterceptor;
    /**
     * 注册三方filter
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
       FilterRegistrationBean register = new FilterRegistrationBean();
       TimeFilter timeFilter = new TimeFilter();
       register.setFilter(timeFilter);

       List<String> urls = new ArrayList<>();
       urls.add("/*");
       register.setUrlPatterns(urls);
       return register;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
