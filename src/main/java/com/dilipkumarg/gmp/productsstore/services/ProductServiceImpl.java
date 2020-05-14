package com.dilipkumarg.gmp.productsstore.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.repo.ProductRepository;
import com.dilipkumarg.gmp.productsstore.utils.AutoIncrementIdentifierGenerator;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final SecurityService securityService;
    private final AutoIncrementIdentifierGenerator identifierGenerator;

    public ProductServiceImpl(
            final ProductRepository repository,
            final SecurityService securityService) {
        this.repository = repository;
        this.securityService = securityService;
        this.identifierGenerator = new AutoIncrementIdentifierGenerator(0);
    }

    @Override
    public Product createProduct(final Product product) {
        product.setCreatedBy(securityService.getLoggedInUser());
        product.setCreatedAt(Instant.now());
        product.setId(identifierGenerator.next());

        return repository.save(product);
    }

    @Override
    public Product updateProduct(final Integer productId, final Product product) {
        return repository.update(productId, product);
    }

    @Override
    public void deleteProduct(final Integer productId) {
        repository.remove(productId);
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<Product> findById(final Integer productId) {
        return repository.findById(productId);
    }
}
