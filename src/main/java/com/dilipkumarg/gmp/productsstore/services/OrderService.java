package com.dilipkumarg.gmp.productsstore.services;

import java.util.List;

import com.dilipkumarg.gmp.productsstore.models.Order;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.models.UserCart;

public interface OrderService {

    Order createOrder(UserCart cart);

    List<Order> findAllByUser(User user);


}
