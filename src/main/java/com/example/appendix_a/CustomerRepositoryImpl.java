package com.example.appendix_a;

import com.example.appendix_a.Models.Customer;
import com.example.appendix_a.Models.CustomerCountry;
import com.example.appendix_a.Models.CustomerGenre;
import com.example.appendix_a.Models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    };

    @Override
    public Customer findById(Integer id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_Code"),
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
    public Customer findByName(String name) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ? ";
        Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2,name);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_Code"),
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
    public CustomerCountry mostPopularCountry() {
        String sql = "SELECT country FROM customer GROUP BY country ORDER BY country DESC LIMIT 1";
        CustomerCountry country = null;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                country = new CustomerCountry(
                        result.getString("country")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }


    @Override
    public List<Customer> getCustomerPage(int lim, int offs) {
        String sql = "SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,lim);
            statement.setInt(2,offs);
            ResultSet result = statement.executeQuery();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer addCustomer(Customer object) {
        String sql = "INSERT INTO customer (\"first_name\",\"last_name\",\"country\",\"postal_code\",\"phone\",\"email\") VALUES(?,?,?,?,?,?)";
        Customer insertedCustomer = null;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.firstName());
            statement.setString(2, object.lastName());
            statement.setString(3, object.country());
            statement.setString(4, object.postalCode());
            statement.setString(5, object.phoneNumber());
            statement.setString(6, object.email());

            int numRowsAffected = statement.executeUpdate();
            if (numRowsAffected > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int insertedId = rs.getInt(1);
                    insertedCustomer = new Customer(insertedId, object.firstName(), object.lastName(), object.country(), object.postalCode(), object.phoneNumber(), object.email());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedCustomer;
    }

    @Override
    public Customer updateCustomer(Customer object) {
        String sql = "UPDATE customer SET first_name=?, last_name=?, country=?, postal_code=?, phone=?, email=? " +
                "WHERE customer_id=?";
        int numRowsAffected = 0;
        Customer updatedCustomer = null;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, object.firstName());
            statement.setString(2, object.lastName());
            statement.setString(3, object.country());
            statement.setString(4, object.postalCode());
            statement.setString(5, object.phoneNumber());
            statement.setString(6, object.email());
            statement.setInt(7, object.id());
            numRowsAffected = statement.executeUpdate();

            if (numRowsAffected > 0) {
                updatedCustomer = object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedCustomer;
    }

    @Override
    public CustomerSpender customerWithHighestInvoice() {
        String sql = "SELECT customer_id FROM invoice GROUP BY customer_id  ORDER BY SUM(total) DESC LIMIT 1; ";
        CustomerSpender customerSpender= null;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            if(result.next()){
                int id = result.getInt("customer_id");
                customerSpender = new CustomerSpender(findById(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSpender;
    }

    @Override
    public List <CustomerGenre> customerPopularGenre(Customer object) {
        String sql = "SELECT invoice.customer_id, genre.name, count(genre.name) \n" +
                "FROM invoice INNER JOIN invoice_line ON invoice_line.invoice_id = invoice.invoice_id\n" +
                "INNER JOIN track ON invoice_line.track_id = track.track_id\n" +
                "INNER JOIN genre ON track.genre_id = genre.genre_id\n" +
                "WHERE invoice.customer_id = ? GROUP BY genre.name, invoice.customer_id\n" +
                "ORDER BY count(genre.name) DESC FETCH FIRST 1 ROWS WITH TIES";
       List<CustomerGenre>customerGenres= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, object.id());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CustomerGenre customerGenre = new CustomerGenre(
                        result.getString("name")
                );
                customerGenres.add(customerGenre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerGenres;
    }

    @Override
    public int delete(Customer object) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}