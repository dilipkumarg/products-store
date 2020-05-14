package com.dilipkumarg.gmp.productsstore.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoIncrementIdentifierGenerator {

    private final AtomicInteger integer;

    public AutoIncrementIdentifierGenerator(int initialValue) {
        this.integer = new AtomicInteger(initialValue);
    }

    public Integer next() {
        return integer.incrementAndGet();
    }
}
