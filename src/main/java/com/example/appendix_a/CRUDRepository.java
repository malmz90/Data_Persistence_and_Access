package com.example.appendix_a;

import java.util.List;

public interface CRUDRepository<T, U> {
    List<T> findAll();
    T findById(U id);

    T findByName(String name);

    List <T> getCustomerPage (int lim, int offs);
    int addCustomer(T object);
    int updateCustomer(T object);
    int delete(T object);
    int deleteById(U id);
}
