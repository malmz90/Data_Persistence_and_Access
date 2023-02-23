package com.example.appendix_a.Models;

import org.springframework.stereotype.Component;

       public record Customer(int id,
                               String firstName,
                               String lastName,
                               String country,
                               String postalCode,
                               String phoneNumber,
                               String email )
       {
       }

