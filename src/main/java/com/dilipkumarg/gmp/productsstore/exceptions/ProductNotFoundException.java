package com.dilipkumarg.gmp.productsstore.exceptions;

public class ProductNotFoundException extends AppException {

    private final Integer productId;

    public ProductNotFoundException(final Integer productId) {
        super("Product not found:" + productId);
        this.productId = productId;
    }
}
