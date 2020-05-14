package com.dilipkumarg.gmp.productsstore.models;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class UserCart {

    private User user;
    private Map<Integer, OrderProductEntry> productEntries = new LinkedHashMap<>();
    private double totalCost;

    public UserCart(final User user) {
        this.user = user;
    }
}
