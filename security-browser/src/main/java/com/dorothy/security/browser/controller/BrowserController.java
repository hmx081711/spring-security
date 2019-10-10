package com.dorothy.security.browser.controller;

import com.dorothy.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.dorothy.security.browser.support.SimpleResponse;

@RestController
@RequestMapping("/browser")
public class BrowserController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 需要验证时跳转这里
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest cacheRequest = requestCache.getRequest(request, response);
        if (cacheRequest !=null) {
            String targetUrl = cacheRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")) {
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("需要身份验证,请引导用户去登陆页面");
    }


    @GetMapping("/me")
    public Object getAuthenticationInfo(@AuthenticationPrincipal User user) {
        //return SecurityContextHolder.getContext().getAuthentication();
        return user;
    }
}
