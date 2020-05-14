package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.UserCart;
import com.dilipkumarg.gmp.productsstore.services.CartService;

public class ShowCartCommand implements ShellCommand {

    private final CartService cartService;
    private final Availability availability;

    public ShowCartCommand(
            final CartService cartService, final Availability availability) {
        this.cartService = cartService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Lists the products the logged in user's cart";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final UserCart cart = cartService.getCart();
        ps.println("----Cart----");
        ps.println(
                MessageFormat.format("Items: {0}, Price: {1}", cart.getProductEntries().size(), cart.getTotalCost()));

        cart.getProductEntries().values()
                .forEach(entry -> {
                    ps.println(MessageFormat.format("Product Id:{0}, Name:{1}, Quantity:{2}, Cost:{3}",
                            entry.getProduct().getId(), entry.getProduct().getName(), entry.getQuantity(),
                            entry.getQuantity() * entry.getPrice()));
                });
    }
}
