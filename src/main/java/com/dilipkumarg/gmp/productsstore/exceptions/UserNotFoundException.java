package com.dilipkumarg.gmp.productsstore.exceptions;

public class UserNotFoundException extends AppException {
    private final String userId;

    public UserNotFoundException(final String userId) {
        super("User not found:" + userId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
