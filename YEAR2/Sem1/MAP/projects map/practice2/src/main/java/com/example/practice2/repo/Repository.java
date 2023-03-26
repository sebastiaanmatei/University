package com.example.practice2.repo;



import com.example.practice2.domain.Entity;
import com.example.practice2.exceptions.ValidationException;


public interface Repository<ID, E extends Entity<ID>> {

    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     *
     * @param entity
     *         entity must be not null
     * @return null- if the given entity is saved
     *         otherwise returns the entity (id already exists)
     * @throws ValidationException
     *            if the entity is not valid
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     */
    E save(E entity);


}



