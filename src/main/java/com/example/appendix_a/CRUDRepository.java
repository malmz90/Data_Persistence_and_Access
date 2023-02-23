package com.example.appendix_a;

import java.util.List;

public interface CRUDRepository<T, U> {
    List<T> findAll();
    T findById(U id);

    T findByName(String name);
    T addCustomer(T object);
    T updateCustomer(T object);
    int delete(T object);
    int deleteById(U id);
}
