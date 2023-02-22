package com.example.appendix_a;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<ChinookDAO.Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<ChinookDAO.Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                ChinookDAO.Customer customer = new ChinookDAO.Customer(
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    };

    @Override
    public ChinookDAO.Customer findById(Integer id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        ChinookDAO.Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                customer = new ChinookDAO.Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ChinookDAO.Customer findByName(String name) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ? ";
        ChinookDAO.Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2,name);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                customer = new ChinookDAO.Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ChinookDAO.Customer addCustomer(ChinookDAO.Customer object) {
        String sql = "INSERT INTO customer ('first_name','last_name','country','postal_code','phone','email') VALUES(?,?,?,?,?,?)";
        ChinookDAO.Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,object.firstName());
            statement.setString(2,object.lastName());
            statement.setString(3,object.country());
            statement.setString(4,object.postalCode());
            statement.setString(5,object.phoneNumber());
            statement.setString(6,object.email());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                customer = new ChinookDAO.Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public int update(ChinookDAO.Customer object) {
        return 0;
    }

    @Override
    public int delete(ChinookDAO.Customer object) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}