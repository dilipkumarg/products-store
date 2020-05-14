package com.dilipkumarg.gmp.productsstore.repo;

import java.util.Collection;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.models.Identifiable;

public interface CrudRepository<T extends Identifiable<ID>, ID> {

    T save(T entity);

    T update(ID id, T entity);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean remove(ID id);
}
