package org.demo.filters;


import org.demo.exception.ApiException;
import org.demo.service.security.MyAuthenticationService;
import org.demo.service.security.TokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestTokenAuthenticationFilter extends GenericFilterBean {

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
        String path = httpRequest.getServletPath() + httpRequest.getPathInfo();
        System.out.println("path = " + path);

        try {
            if (token == null) {
                authenticated = checkPassword(httpRequest, httpResponse);
                if (authenticated && path.contains("/logout")) authenticationService.logout(token);
            } else {
                authenticated = checkToken(token);
            }

            if (authenticated) {
                filterChain.doFilter(httpRequest, httpResponse);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }

        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }




    }

    private boolean checkToken(String token) {
        return authenticationService.checkToken(token);
    }

    private boolean checkPassword(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        TokenDetails tokenInfo = null;
        String username = httpRequest.getHeader("username");
        String password = httpRequest.getHeader("password");
        String authorization = httpRequest.getHeader("Authorization");

        if (authorization != null) {
            throw new ApiException("basic authorization is not supported");
        }

        if (username != null && password != null) {
            System.out.println("username = " + username + " password = " + password);
            tokenInfo = authenticationService.authenticate(username, password);
        }

        System.out.println("tokenInfo = " + tokenInfo);
        if (tokenInfo != null) {
            httpResponse.setHeader("X-Auth-Token", tokenInfo.getToken());
            return true;
        }
        return false;
    }
}