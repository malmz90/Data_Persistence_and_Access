package com.example.appendix_a;

import com.example.appendix_a.Models.CustomerSpender;
import java.util.List;

public interface CRUDRepository<T, U> {
    List<T> findAll();
    T findById(U id);

    T findByName(String name);
    CustomerSpender getCustomerWithHighestInvoice();
    int addCustomer(T object);
    int update(T object);
    int delete(T object);
    int deleteById(U id);
}
