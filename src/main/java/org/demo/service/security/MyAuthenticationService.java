package org.demo.service.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyAuthenticationService {
    TokenDetails authenticate(String login, String password);

    boolean checkToken(String token);

    void logout(String token);

    UserDetails currentUser();
}