package com.dilipkumarg.gmp.productsstore.services;

import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.exceptions.SecurityException;
import com.dilipkumarg.gmp.productsstore.models.User;

public class SecurityService {

    private final ThreadLocal<User> userTL = new ThreadLocal<>();

    private final UserService userService;

    public SecurityService(final UserService userService) {
        this.userService = userService;
    }

    public User login(String userId) {
        final User user = userService.getById(userId);
        userTL.set(user);
        return user;
    }

    public void logout() {
        userTL.remove();
    }

    public boolean isUserLoggedIn() {
        return getLoggedUser().isPresent();
    }

    public User getLoggedInUser() {
        return getLoggedUser().orElseThrow(() -> new SecurityException("User not logged in"));
    }

    private Optional<User> getLoggedUser() {
        return Optional.ofNullable(userTL.get());
    }
}
