package com.example.appendix_a;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChinookDAO
   {

        private String url = "jdbc:postgresql://localhost:5432/Chinook";
        private String username = "postgres";
        private String password = "postgres";

        public ChinookDAO() {
        }

        public ChinookDAO(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

       public record Customer(int id,
                               String firstName,
                               String lastName,
                               String country,
                               String postalCode,
                               String phoneNumber,
                               String email )
       {
       }


       public void getAllCustomers() {
           String sql = "SELECT * FROM customer";
           try(Connection conn = DriverManager.getConnection(url, username,password)) {

               PreparedStatement statement = conn.prepareStatement(sql);

               ResultSet result = statement.executeQuery();
               List<Customer> customers = new ArrayList<>();
               while(result.next()){
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_Code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
               }

               System.out.println(customers);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
}
