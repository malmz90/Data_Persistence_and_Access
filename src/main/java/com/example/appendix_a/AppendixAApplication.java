package com.example.appendix_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppendixAApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppendixAApplication.class, args);
        ChinookDAO chinookDAO = new ChinookDAO();
        chinookDAO.getAllCustomers();
        }

}
