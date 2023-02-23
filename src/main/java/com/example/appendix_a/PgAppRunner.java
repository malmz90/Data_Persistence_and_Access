package com.example.appendix_a;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    public PgAppRunner(CustomerRepository customerRepository ){
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //System.out.println(customerRepository.findAll());
        //System.out.println("test " + customerRepository.findById(2));
       //System.out.println(customerRepository.findByName("Leonie"));
        //System.out.println(customerRepository.mostPopularCountry());
       // System.out.println(customerRepository.getCustomerPage(3,3));
        System.out.println(customerRepository.customerWithHighestInvoice());

    }
}

