package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.Order;
import com.dilipkumarg.gmp.productsstore.models.UserCart;
import com.dilipkumarg.gmp.productsstore.services.CartService;
import com.dilipkumarg.gmp.productsstore.services.OrderService;

public class CheckoutCommand implements ShellCommand {

    private final CartService cartService;
    private final OrderService orderService;
    private final Availability availability;

    public CheckoutCommand(
            final CartService cartService, final OrderService orderService,
            final Availability availability) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Checkouts the user cart and create the order.";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final UserCart cart = cartService.getCart();
        ps.println("Total items in the cart:" + cart.getProductEntries().size() + ", Price:" + cart.getTotalCost());
        final Order order = orderService.createOrder(cart);
        ps.println("Success!! Order created with id:" + order.getId());
        cartService.clearCart();
    }
}
