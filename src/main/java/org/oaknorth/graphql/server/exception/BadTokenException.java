package org.oaknorth.graphql.server.exception;


import org.springframework.security.core.AuthenticationException;

public class BadTokenException extends AuthenticationException {

    public BadTokenException() {
        super("Token is invalid or expired");
    }
}
