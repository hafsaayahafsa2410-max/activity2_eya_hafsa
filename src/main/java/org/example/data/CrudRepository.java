package org.example.data;
/*
 * CrudRepository.java
 * This is the generic CRUD interface that defines the basic operations:
 * save, update, delete, findById, and count.
 */
public interface CrudRepository<E> {
    E save(E e);
    E update(E e);
    boolean delete(int id);
    E findById(int id);
    long count();
}
