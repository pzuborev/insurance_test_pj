package org.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Resource not found";
    }
}
