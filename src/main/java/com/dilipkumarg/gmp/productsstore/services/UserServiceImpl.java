package com.dilipkumarg.gmp.productsstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.exceptions.UserAlreadyExistsException;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.models.UserCart;
import com.dilipkumarg.gmp.productsstore.repo.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(final User user) {
        if (findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistsException(user.getId());
        }
        user.setCart(new UserCart(user));
        return repository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<User> findById(final String userId) {
        return repository.findById(userId);
    }
}
