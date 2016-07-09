package org.demo.service.security;

import org.springframework.security.core.userdetails.UserDetails;

public class TokenDetails {
    String token;
    UserDetails userDetails;

    public TokenDetails(String token, UserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }
    public long getLastUsed() {
        return System.currentTimeMillis();
    }


}
