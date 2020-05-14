package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.services.CartService;
import com.dilipkumarg.gmp.productsstore.services.ProductService;

public class ProductUpdateCommand implements ShellCommand {

    private final ProductService productService;
    private final CartService cartService;
    private final Availability availability;

    public ProductUpdateCommand(
            final ProductService productService,
            final CartService cartService, final Availability availability) {
        this.productService = productService;
        this.cartService = cartService;
        this.availability = availability;
    }


    @Override
    public String getHelpText() {
        return "Updates the product with new information. Format: id, name, description, price";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public int getMinRequiredArgs() {
        return 4;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final Product product = productService.getById(Integer.parseInt(args.get(1)));

        product.setName(args.get(1));
        product.setDescription(args.get(2));
        product.setPrice(Double.parseDouble(args.get(3)));

        final Product updateProduct = productService.updateProduct(product.getId(), product);
        ps.println(MessageFormat.format("Product:{0} info updated", product.getId()));

        cartService.onProductUpdated(updateProduct);
        ps.println(MessageFormat.format("Product:{0} info updated in all carts", product.getId()));
    }
}
