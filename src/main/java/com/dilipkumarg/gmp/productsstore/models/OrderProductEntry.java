package com.dilipkumarg.gmp.productsstore.models;

import lombok.Data;

@Data
public class OrderProductEntry {
    private Product product;
    private int quantity;
    private double price;

    public OrderProductEntry(final Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }
}
