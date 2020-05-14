package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.services.CartService;
import com.dilipkumarg.gmp.productsstore.services.ProductService;

public class ProductDeleteCommand implements ShellCommand {

    private final ProductService productService;
    private final CartService cartService;
    private final Availability availability;

    public ProductDeleteCommand(
            final ProductService productService,
            final CartService cartService, final Availability availability) {
        this.productService = productService;
        this.cartService = cartService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Deletes the given product from the system. format: productId";
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
        final Product product = productService.getById(Integer.parseInt(args.get(0)));
        cartService.onBeforeProductDeleted(product);
        productService.deleteProduct(product.getId());
        ps.println(MessageFormat.format("Product:{} deleted successfully and updated in the carts.", product.getId()));
    }
}
