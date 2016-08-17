package org.demo.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.demo.controller.ErrorResource;
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

    final static Logger logger = Logger.getLogger(RestTokenAuthenticationFilter.class);
    @Autowired
    private MyAuthenticationService authenticationService;

    public void setAuthenticationService(MyAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        logger.debug(" *** doFilter ");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (logger.isDebugEnabled()) {
            logger.debug("httpRequest Method = " + httpRequest.getMethod());
            logger.debug("httpRequest  Origin = " + httpRequest.getHeader("Origin"));
            logger.debug("httpRequest Access-Control-Request-Method = " + httpRequest.getHeader("Access-Control-Request-Method"));
            logger.debug("httpRequest Access-Control-Request-Headers = " + httpRequest.getHeader("Access-Control-Request-Headers"));
            logger.debug("httpRequest Content-Type = " + httpRequest.getHeader("Content-Type"));
        }
        if (httpRequest.getHeader("Origin") != null) {
            String origin = httpRequest.getHeader("Origin");
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
            httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            httpResponse.addHeader("Access-Control-Allow-Method", "POST");
            httpResponse.addHeader("Access-Control-Max-Age", "86400");
            httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.addHeader("Access-Control-Allow-Headers", "token, username, password, content-type");
            httpResponse.addHeader("Access-Control-Expose-Headers", "token, username, password, content-type");
        }
        if (httpRequest.getMethod().equals("OPTIONS")) {
            if (logger.isDebugEnabled()) {
                logger.debug("  ");
                logger.debug("httpResponse Access-Control-Allow-Headers: " + httpResponse.getHeader("Access-Control-Allow-Headers"));
                logger.debug("httpResponse Access-Control-Allow-Methods: " + httpResponse.getHeader("Access-Control-Allow-Methods"));
                logger.debug("httpResponse Access-Control-Allow-Method: " + httpResponse.getHeader("Access-Control-Allow-Method"));
                logger.debug("httpResponse Access-Control-Allow-Origin: " + httpResponse.getHeader("Access-Control-Allow-Origin"));
                logger.debug("return " + httpResponse.getStatus());
            }
            httpResponse.setStatus(HttpServletResponse.SC_OK);

            return;
        }

        boolean authenticated = false;
        String token = httpRequest.getHeader("token");
        if (token == null) token = httpRequest.getParameter("token");
        String path = httpRequest.getServletPath() + httpRequest.getPathInfo();

        if (logger.isDebugEnabled()) {
            logger.debug("***** PATH :: " + path);
            logger.debug("*** header username :: " + httpRequest.getHeader("username"));
            logger.debug("*** header token    :: " + httpRequest.getHeader("token"));
            logger.debug("*** param  username :: " + httpRequest.getParameter("username"));
            logger.debug("*** param  token    :: " + httpRequest.getParameter("token"));
        }
        try {
            if (token == null) {

                authenticated = checkPassword(httpRequest, httpResponse);
            } else {
                authenticated = checkToken(token);
            }

            if (authenticated) {
                logger.debug("*** perform next filter");
                filterChain.doFilter(httpRequest, httpResponse);
            } else {
                logger.debug("*** auth failed");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();

//          httpResponse.sendError(, new ErrorResource(e.getMessage()));
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write(convertObjectToJson(new ErrorResource(e.getMessage())));
        }

    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private boolean checkToken(String token) {
        return authenticationService.checkToken(token);
    }

    private boolean checkPassword(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        logger.debug("*** do checkPassword ***");

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
            logger.debug("*** username = " + username + " password = " + password);
            tokenInfo = authenticationService.authenticate(username, password);

            logger.debug("*** tokenInfo = " + tokenInfo);
            if (tokenInfo != null) {
                httpResponse.setHeader("token", tokenInfo.getToken());
                return true;
            }
        } else {
            logger.debug("***** username or password is null");
        }

        return false;
    }
}