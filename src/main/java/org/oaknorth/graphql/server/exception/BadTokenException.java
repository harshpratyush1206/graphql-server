package org.oaknorth.graphql.server.exception;

public class BadTokenException extends RuntimeException {

    public BadTokenException() {}

    public BadTokenException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Token is invalid or expired";
    }
}
