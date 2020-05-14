package com.dilipkumarg.gmp.productsstore.services;

import java.util.List;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.exceptions.ProductNotFoundException;
import com.dilipkumarg.gmp.productsstore.models.Product;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Integer productId, Product product);

    void deleteProduct(Integer productId);

    List<Product> findAllProducts();

    Optional<Product> findById(Integer productId);

    default Product getById(Integer productId) {
        return findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
