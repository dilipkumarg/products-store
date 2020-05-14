package com.dilipkumarg.gmp.productsstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dilipkumarg.gmp.productsstore.models.Order;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.models.UserCart;
import com.dilipkumarg.gmp.productsstore.repo.OrderRepository;
import com.dilipkumarg.gmp.productsstore.utils.AutoIncrementIdentifierGenerator;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final AutoIncrementIdentifierGenerator identifierGenerator;

    public OrderServiceImpl(
            final OrderRepository repository) {
        this.repository = repository;
        this.identifierGenerator = new AutoIncrementIdentifierGenerator(0);
    }

    @Override
    public Order createOrder(final UserCart cart) {
        final Order order = Order.builder()
                .id(identifierGenerator.next())
                .entries(new ArrayList<>(cart.getProductEntries().values()))
                .totalPrice(cart.getTotalCost())
                .user(cart.getUser())
                .build();
        return this.repository.save(order);
    }

    @Override
    public List<Order> findAllByUser(final User user) {
        return repository.findAll().stream()
                .filter(order -> Objects.equals(order.getUser(), user))
                .collect(Collectors.toList());
    }
}
