package com.dilipkumarg.gmp.productsstore;

import com.dilipkumarg.gmp.productsstore.repo.OrderRepository;
import com.dilipkumarg.gmp.productsstore.repo.OrderRepositoryImpl;
import com.dilipkumarg.gmp.productsstore.repo.ProductRepository;
import com.dilipkumarg.gmp.productsstore.repo.ProductRepositoryImpl;
import com.dilipkumarg.gmp.productsstore.repo.UserRepository;
import com.dilipkumarg.gmp.productsstore.repo.UserRepositoryImpl;
import com.dilipkumarg.gmp.productsstore.services.CartService;
import com.dilipkumarg.gmp.productsstore.services.OrderService;
import com.dilipkumarg.gmp.productsstore.services.OrderServiceImpl;
import com.dilipkumarg.gmp.productsstore.services.ProductService;
import com.dilipkumarg.gmp.productsstore.services.ProductServiceImpl;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;
import com.dilipkumarg.gmp.productsstore.services.UserCartService;
import com.dilipkumarg.gmp.productsstore.services.UserService;
import com.dilipkumarg.gmp.productsstore.services.UserServiceImpl;
import lombok.Data;

@Data
// using this in place of dependency injection.
public class ObjectFactory {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    private final UserService userService = new UserServiceImpl(userRepository);
    private final SecurityService securityService = new SecurityService(userService);
    private final ProductService productService = new ProductServiceImpl(productRepository, securityService);
    private final OrderService orderService = new OrderServiceImpl(orderRepository);
    private final CartService cartService = new UserCartService(securityService, userService, productService);

}
