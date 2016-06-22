package org.demo.exception;

public class UserNotFoundException extends RuntimeException {

    private final String username;
    public UserNotFoundException(String username) {
      this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getMessage() {
        return String.format("User with name %s not found", username);
    }
}
