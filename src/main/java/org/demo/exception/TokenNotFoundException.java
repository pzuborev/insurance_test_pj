package org.demo.exception;

public class TokenNotFoundException extends RuntimeException {

    private final String token;
    public TokenNotFoundException(String token) {
        this.token = token;
    }

    public String getUsername() {
        return token;
    }

    @Override
    public String getMessage() {
        return String.format("Token '%s' not found", token);
    }
}