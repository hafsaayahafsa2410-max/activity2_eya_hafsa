package org.example.data;
/*
 * CrudRepository.java
 * This is the generic CRUD interface that defines the basic operations:
 * save, update, delete, findById, and count.
 * we use this so  business layer doesn’t care HOW data is stored it just follows the contract.
 */
public interface CrudRepository<E> {
    E save(E e);
    E update(E e);
    boolean delete(int id);
    E findById(int id);
    long count();
}
