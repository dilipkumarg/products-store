package com.dilipkumarg.gmp.productsstore.commands;

import com.dilipkumarg.gmp.productsstore.services.CartService;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class CartAvailablity implements Availability {

    private final SecurityService securityService;
    private final CartService cartService;

    public CartAvailablity(
            final SecurityService securityService,
            final CartService cartService) {
        this.securityService = securityService;
        this.cartService = cartService;
    }

    @Override
    public boolean isAvailable() {
        return securityService.isUserLoggedIn() && !cartService.getCart().getProductEntries().isEmpty();
    }

    @Override
    public String getHelpText() {
        return "Active user should be logged in and have atlease one item in the cart.";
    }
}
