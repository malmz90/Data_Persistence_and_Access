package com.example.appendix_a;

import com.example.appendix_a.Models.CustomerCountry;
import com.example.appendix_a.Models.CustomerGenre;
import com.example.appendix_a.Models.CustomerSpender;

import java.util.List;

public interface CRUDRepository<T, U> {
    List<T> findAll();
    T findById(U id);
    T findByName(String name);
    T addCustomer(T object);
    T updateCustomer(T object);
    int delete(T object);
    int deleteById(U id);
    List <T> getCustomerPage (int lim, int offs);
    CustomerCountry mostPopularCountry();
    CustomerSpender customerWithHighestInvoice();
    List <CustomerGenre> customerPopularGenre(T object);

}
