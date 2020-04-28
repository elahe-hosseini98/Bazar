package ElaheHosseini_HW12_Maktab33.Customer.dao;

import ElaheHosseini_HW12_Maktab33.Customer.dto.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {
    private final String url = "jdbc:postgresql://localhost/Online_Bazar";
    private final String user = "postgres";
    private final String password = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean insert(Customer customer) throws InvalidEmailException {
        try {
            Connection connection = getConnection();
            if (!isEmailUnique(customer.getEmail())) {
                throw new InvalidEmailException("__EMAIL ALREADY EXIST!");
            }
            String insertQuery = "insert into customer (age, first_name, last_name, phone_number, email" +
                    ", address) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, customer.getAge());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean isEmailUnique(String email) {
        try {
            Connection connection = getConnection();
            String selectQuery = "select email from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(email)) return false;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Customer customerInfo(String email) {
        Customer customer = new Customer();
        try {
            Connection connection = getConnection();
            String query = "select * from customer where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setPhoneNumber(resultSet.getString(4));
                customer.setAddress(resultSet.getString(6));
                customer.setAge(resultSet.getInt(7));
                customer.setEmail(email);
                preparedStatement.close();
                connection.close();
                return customer;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            String query = "Select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setPhoneNumber(resultSet.getString(4));
                customer.setAddress(resultSet.getString(6));
                customer.setAge(resultSet.getInt(7));
                customer.setEmail(resultSet.getString(5));
                allCustomers.add(customer);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return allCustomers;
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}
