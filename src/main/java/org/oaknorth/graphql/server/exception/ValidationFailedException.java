package org.oaknorth.graphql.server.exception;

import java.text.MessageFormat;

public class ValidationFailedException  extends RuntimeException {
    public ValidationFailedException() {}

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(String message, Object... props) {
        super(MessageFormat.format(message,props));
    }

    public ValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailedException(Throwable cause) {
        super(cause);
    }

    public ValidationFailedException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
