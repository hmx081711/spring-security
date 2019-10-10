package com.dorothy.security.properties.ValidateCode;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.dorothy.security.properties.ValidateCode.ValidateController.SESSION_KEY;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> intercaptorUrl = new HashSet<>();


    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] config = StringUtils.splitByWholeSeparator(validateCodeProperties.getImageCode().getUrls(),",");

        for (String url : config) {
            intercaptorUrl.add(url);
        }
        intercaptorUrl.add("/authentication/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        boolean falg = false;

        for (String url : intercaptorUrl) {
            if (antPathMatcher.match(url,request.getRequestURI())) {
                falg = true;
            }
        }
        if (falg) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException ex) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, ex);
                return;
            }
        }

        filterChain.doFilter(request, response);

    }

    private void validate(ServletWebRequest servletWebRequest) {
        ImageCode validCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, SESSION_KEY);
        String requestCode = (String) servletWebRequest.getParameter("imageCode");
        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码不能为空");
        } else if (validCode == null) {
            throw new ValidateCodeException("验证码不存在");
        } else if (validCode.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        } else if (!StringUtils.equalsIgnoreCase(validCode.getCode(), requestCode)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
    }
}
