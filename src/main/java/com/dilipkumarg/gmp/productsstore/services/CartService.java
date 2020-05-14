package com.dilipkumarg.gmp.productsstore.services;

import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.models.UserCart;

public interface CartService {

    // adding multiple times updates the quantity
    UserCart addProduct(Integer productId);

    UserCart removeProduct(Integer productId);

    UserCart clearCart();

    UserCart getCart();

    void onProductUpdated(Product product);

    void onBeforeProductDeleted(Product product);

}
