package com.dilipkumarg.gmp.productsstore.exceptions;

public class SecurityException extends AppException {
    public SecurityException(final String message) {
        super(message);
    }

    public SecurityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
