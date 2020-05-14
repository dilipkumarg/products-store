package com.dilipkumarg.gmp.productsstore.repo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.dilipkumarg.gmp.productsstore.models.Identifiable;

public class InMemoryCrudRepository<T extends Identifiable<ID>, ID> implements CrudRepository<T, ID> {

    private Map<ID, T> data = new LinkedHashMap<>();

    @Override
    public T save(final T entity) {
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T update(final ID id, final T entity) {
        data.compute(id, (i, e) -> entity);
        return entity;
    }

    @Override
    public Optional<T> findById(final ID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Collection<T> findAll() {
        return data.values();
    }

    @Override
    public boolean remove(final ID id) {
        data.remove(id);
        return true;
    }
}
