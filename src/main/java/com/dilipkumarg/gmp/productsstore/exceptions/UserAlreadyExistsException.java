package com.dilipkumarg.gmp.productsstore.exceptions;

import java.text.MessageFormat;

public class UserAlreadyExistsException extends AppException {
    public UserAlreadyExistsException(final String userId) {
        super(MessageFormat.format("{0} already exists", userId));
    }
}
