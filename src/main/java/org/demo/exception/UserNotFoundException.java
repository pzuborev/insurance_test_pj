package org.demo.exception;

public class UserNotFoundException extends RuntimeException {
   // public static final long serialVersionUID = -1;

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
