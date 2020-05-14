package com.dilipkumarg.gmp.productsstore.services;

import java.util.List;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.exceptions.UserNotFoundException;
import com.dilipkumarg.gmp.productsstore.models.User;

public interface UserService {

    User register(User user);

    List<User> findAllUsers();

    Optional<User> findById(String userId);

    default User getById(String userId) {
        return findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
