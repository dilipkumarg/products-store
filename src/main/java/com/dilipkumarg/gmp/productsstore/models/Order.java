package com.dilipkumarg.gmp.productsstore.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order implements Identifiable<Integer> {

    private Integer id;
    private List<OrderProductEntry> entries;
    private double totalPrice;
    private User user;
}
