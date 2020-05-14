package com.dilipkumarg.gmp.productsstore.models;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private String description;
    private double price;

    private User createdBy;
    private Instant createdAt;
}
