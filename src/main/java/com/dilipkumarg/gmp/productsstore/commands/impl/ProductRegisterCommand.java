package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.services.ProductService;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class ProductRegisterCommand implements ShellCommand {

    private final ProductService productService;

    private final Availability adminAvailability;

    public ProductRegisterCommand(
            final ProductService productService,
            final Availability adminAvailability) {
        this.productService = productService;
        this.adminAvailability = adminAvailability;
    }

    @Override
    public String getHelpText() {
        return "Registers the given product into the system. Format: name, description, price";
    }

    @Override
    public Availability getAvailability() {
        return adminAvailability;
    }

    @Override
    public int getMinRequiredArgs() {
        return 3;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final Product product = Product.builder()
                .name(args.get(0))
                .description(args.get(1))
                .price(Double.parseDouble(args.get(2)))
                .build();
        final Product created = productService.createProduct(product);
        ps.println(MessageFormat
                .format("Product added successfully. Id:{0}, name:{1}", created.getId(), created.getName()));
    }
}
