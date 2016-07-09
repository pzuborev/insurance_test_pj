package org.demo.service.security;

import org.demo.dao.security.TokenDao;
import org.demo.exception.ApiException;
import org.demo.exception.TokenNotFoundException;
import org.demo.service.MyUserDetailsService;
import org.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
@Qualifier("authenticationService")
public class MyAuthenticationServiceImpl implements MyAuthenticationService {

    @Autowired
    TokenDao tokenDao;
    @Autowired
    MyUserService myUserService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    final static Logger logger = Logger.getLogger(MyAuthenticationServiceImpl.class);


    private String newTokenValue () {
        return UUID.randomUUID().toString();
    }

    @Override
    public TokenDetails authenticate(String login, String password) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(login, password);
        /*try {*/
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetails userContext = (UserDetails) authentication.getPrincipal();
                TokenDetails newToken = new TokenDetails(newTokenValue(), userContext); //todo save token to DB

                // todo why can be null?
                if (newToken == null) {
                    return null;
                }
                System.out.println("******** authentication successful has finished ");
                return newToken;
            }
       /* } catch (AuthenticationException e) {
            e.printStackTrace();
        }*/
        return null;

    }

    @Override
    public boolean checkToken(String token) {
        System.out.println("******** checkToken " + token);

        Assert.notNull(tokenDao, "tokenDao is null");

        System.out.println(myUserService);
        UserDetails userDetails = myUserService.loadUserByToken(token); //TODO: find user by token in DB;
        if (userDetails == null) {
            throw new TokenNotFoundException(token);
        }

        Authentication securityToken =
                new PreAuthenticatedAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);
        return true;
    }

    @Override
    public void logout(String token) {
        System.out.println("******* logout");
        //todo delete token from DB
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserDetails currentUser() {
        return null;
    }
}
