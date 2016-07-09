package org.demo.filters;


import org.demo.service.security.MyAuthenticationService;
import org.demo.service.security.MyAuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private MyAuthenticationService authenticationService;

    public void setAuthenticationService(MyAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("************ doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        boolean authenticated = false;
        String token = httpRequest.getHeader("X-Auth-MyToken");
        if (token == null) {

        } else if (authenticationService.checkToken(token)) {
            authenticated = true;
        }
        if (!authenticated)
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }


    }
}