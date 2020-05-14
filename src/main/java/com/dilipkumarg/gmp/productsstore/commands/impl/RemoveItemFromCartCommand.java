package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.services.CartService;

public class RemoveItemFromCartCommand implements ShellCommand {

    private final CartService cartService;
    private final Availability availability;

    public RemoveItemFromCartCommand(
            final CartService cartService, final Availability availability) {
        this.cartService = cartService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Removes the product from cart, if multiple quantites exists, it will decrements the quantity. Format:" +
                " productId";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public int getMinRequiredArgs() {
        return 1;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final int productId = Integer.parseInt(args.get(0));
        cartService.removeProduct(productId);
        ps.println("Cart updated.");
    }
}
