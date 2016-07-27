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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("*** doFilter ***");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        System.out.println("Origin = " + httpRequest.getHeader("Origin"));

//        httpResponse.addHeader("Access-Control-Allow-Headers",  "X-Requested-With");
//        httpResponse.addHeader("Access-Control-Allow-Headers",  "Destination");
//        httpResponse.addHeader("Access-Control-Allow-Methods",  "GET");
//        httpResponse.addHeader("Access-Control-Allow-Methods",  "POST");
//        httpResponse.addHeader("Access-Control-Allow-Methods",  "OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Origin",  "*");
        httpResponse.addHeader("Access-Control-Allow-Credentials",  "true");
        httpResponse.addHeader("Access-Control-Expose-Headers",  "token");


        boolean authenticated = false;
        String token = httpRequest.getHeader("token");
        if (token == null) token = httpRequest.getParameter("token");
        String path = httpRequest.getServletPath() + httpRequest.getPathInfo();

        System.out.println("** PATH :: " + path);

        System.out.println("*** header username :: " + httpRequest.getHeader("username"));
        System.out.println("*** header token    :: " + httpRequest.getHeader("token"));
        System.out.println("*** param  username :: " + httpRequest.getParameter("username"));
        System.out.println("*** param  token    :: " + httpRequest.getParameter("token"));

        try {
            if (token == null) {
                authenticated = checkPassword(httpRequest, httpResponse);
                if (authenticated && path.contains("/logout")) authenticationService.logout(token);
//                if (authenticated && path.contains("/login")) httpResponse.sendRedirect("/admin");

            } else {
                authenticated = checkToken(token);
            }

            if (authenticated) {
                filterChain.doFilter(httpRequest, httpResponse);
            } else {
                System.out.println("*** auth failed");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    }

    private boolean checkToken(String token) {
        return authenticationService.checkToken(token);
    }

    private boolean checkPassword(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        System.out.println("*** do checkPassword ***");

        TokenDetails tokenInfo = null;
        String username = httpRequest.getHeader("username");
        String password = httpRequest.getHeader("password");
        if (username == null) {
            username = httpRequest.getParameter("username");
            password = httpRequest.getParameter("password");
        }

        String authorization = httpRequest.getHeader("Authorization");

        if (authorization != null) {
            throw new ApiException("basic authorization is not supported");
        }

        if (username != null && password != null) {
            System.out.println("*** username = " + username + " password = " + password);
            tokenInfo = authenticationService.authenticate(username, password);

            System.out.println("*** tokenInfo = " + tokenInfo);
            if (tokenInfo != null) {
                httpResponse.setHeader("token", tokenInfo.getToken());
                return true;
            }
        } else {
            System.out.println("***** username or password is null");
        }

        return false;
    }
}