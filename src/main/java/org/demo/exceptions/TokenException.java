package org.demo.exceptions;

public class TokenException extends Throwable {
    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
