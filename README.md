# Data_Persistence_and_Access
## Assignment 2 java

## Appendix A: SQL Scripts for Superhero Database

## Overview:
This appendix provides instructions for creating a Superhero database in PgAdmin, setting up tables, creating relationships between them, and populating them with data using SQL scripts.

## Steps:

1. Create a database called SuperheroesDb in PgAdmin.
2. Create tables for Superhero, Assistant, and Power using a script called 01_tableCreate.sql.
3. Add relationships between tables using ALTER SQL keyword and a script called 02_relationshipSuperheroAssistant.sql and 03_relationshipSuperheroPower.sql.
4. Insert data into tables using scripts called 04_insertSuperheroes.sql, 05_insertAssistants.sql, and 06_powers.sql.
5. Update data for a superhero using a script called 07_updateSuperhero.sql.
6. Delete an assistant using a script called 08_deleteAssistant.sql.

That's it! These scripts can be run from the PgAdmin query tool.

## Appendix B: Reading data with JDBC

## Overview:
In this assignment, you will be creating a Spring Boot application that interacts with a PostgreSQL database called Chinook. The application should include the correct driver and implement the repository pattern to manipulate the data.

## Customer Requirements:

1. Display all customers in the database, including their id, first name, last name, country, postal code, phone number, and email.
2. Display a specific customer by their id, showing the same information as in requirement 1.
3. Display a specific customer by name using the LIKE keyword for partial matches.
4. Return a page of customers based on a limit and offset parameter, using the SQL limit and offset keywords.
5. Add a new customer to the database with only the fields listed in requirement 1.
6. Update an existing customer.
7. Return the country with the most customers.
8. Find the customer with the highest total spent (based on the invoice table).
9. Determine the most popular genre for a given customer, based on the genre that corresponds to the most tracks from invoices associated with that customer.

## Notes:

Configuration should be externalized in application.properties and accessed via @Value.
Create a model/record class for each data structure used, including Customer, CustomerCountry, CustomerSpender, and CustomerGenre.
