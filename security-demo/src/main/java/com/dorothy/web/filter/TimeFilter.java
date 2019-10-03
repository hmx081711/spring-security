package com.dorothy.web.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Time Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Time Filter start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("Time Filter 耗时:" + (new Date().getTime() - start));
        System.out.println("Time Filter stop");
    }

    @Override
    public void destroy() {
        System.out.println("Time Filter destroy");
    }
}
