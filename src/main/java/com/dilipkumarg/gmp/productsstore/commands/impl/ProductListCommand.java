package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.services.ProductService;

public class ProductListCommand implements ShellCommand {

    private final ProductService productService;
    private final Availability availability;

    public ProductListCommand(
            final ProductService productService,
            final Availability availability) {
        this.productService = productService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Lists all the products in the system.";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        ps.println("----Products----");
        productService.findAllProducts().forEach(product -> {
            ps.println(MessageFormat.format("Id:{0}, name:{1}, price:{2}\ndescription:{3}", product.getId(),
                    product.getName(), product.getPrice(), product.getDescription()));
        });
    }


}
