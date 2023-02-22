package com.example.appendix_a;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChinookDAO
   {
       public record Customer(int id,
                               String firstName,
                               String lastName,
                               String country,
                               String postalCode,
                               String phoneNumber,
                               String email )
       {
       }

}
