package com.pluralsight;

import java.sql.*;

public class DisplayData { String username = "root";
    String password = "yearup";
    String database = "sakila";
    String databaseurl = "jdbc:mysql://localhost:3306/" + database;

    public void showProducts() {

        String username = "root";
        String password = "yearup";
        String database = "sakila";
        String databaseurl = "jdbc:mysql://localhost:3306/" + database;

        try (Connection connection = DriverManager.getConnection(databaseurl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT productid, productname, unitprice FROM products");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.printf(
                        "Product ID: %d\nName: %s\nPrice: $%.2f\n\n",
                        resultSet.getInt("productid"),
                        resultSet.getString("productname"),
                        resultSet.getDouble("unitprice")
                );
            }

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }


    public void showCustomers() {

        String username = "root";
        String password = "yearup";
        String database = "sakila";
        String databaseurl = "jdbc:mysql://localhost:3306/" + database;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(databaseurl, username, password);

            preparedStatement = connection.prepareStatement(
                    "SELECT contactname, companyname, city, country, phone FROM customers"
            );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.printf(
                        "Contact Name: %s\nCompany Name: %s\nCity: %s\nCountry: %s\nPhone: %s\n\n",
                        resultSet.getString("contactname"),
                        resultSet.getString("companyname"),
                        resultSet.getString("city"),
                        resultSet.getString("country"),
                        resultSet.getString("phone")
                );
            }

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showCategories() {

        String username = "root";
        String password = "yearup";
        String database = "sakila";
        String databaseurl = "jdbc:mysql://localhost:3306/" + database;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(databaseurl, username, password);

            preparedStatement = connection.prepareStatement(
                    "SELECT categoryid, categoryname FROM categories"
            );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.printf(
                        "Category ID: %s\nCategory Name: %s\n\n",
                        resultSet.getString("categoryid"),
                        resultSet.getString("categoryname")
                );
            }
            int categoryId = ConsoleHelper.promptForInt("Enter a category ID to see its products");
            showProductsByCategory(categoryId);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showProductsByCategory(int categoryId) {
        try (Connection connection = DriverManager.getConnection(databaseurl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT productid, productname, unitprice, unitsinstock " +
                             "FROM products WHERE categoryid = ?"
             )) {

            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.printf(
                        "Product ID: %d\nProduct Name: %s\nUnit Price: $%.2f\nUnits in Stock: %d\n\n",
                        resultSet.getInt("productid"),
                        resultSet.getString("productname"),
                        resultSet.getDouble("unitprice"),
                        resultSet.getInt("unitsinstock")
                );
            }

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
