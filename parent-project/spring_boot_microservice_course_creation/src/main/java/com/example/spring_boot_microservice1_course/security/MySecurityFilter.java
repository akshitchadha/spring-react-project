package com.example.spring_boot_microservice1_course.security;


import javax.servlet.*;
import java.io.IOException;


public class MySecurityFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
