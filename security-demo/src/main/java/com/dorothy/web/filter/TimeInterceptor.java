package com.dorothy.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("time interceptor start");
        request.setAttribute("start", new Date().getTime());
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long start = (Long) request.getAttribute("start");
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
        System.out.println("time interceptor finished");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long start = (Long) request.getAttribute("start");
        //System.out.println(((Exception)ex).getClass().getSimpleName());
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
        System.out.println("time interceptor finished");
    }
}
