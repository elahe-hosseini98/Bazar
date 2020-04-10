package ElaheHosseini_HW10_Maktab33.ProductStore.dao;

import ElaheHosseini_HW10_Maktab33.ProductStore.dto.Product;
import ElaheHosseini_HW10_Maktab33.ProductStore.dto.ProductType;

import java.sql.*;
import java.util.ArrayList;

public class ProductDao {
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

    public boolean insert(Product product, int count) {
        try {
            Connection connection = getConnection();
            String insertQuery = "insert into product_store (name, brand, count, product_type, Specification, price) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, product.getProductName().toLowerCase());
            preparedStatement.setString(2, product.getBrand().toLowerCase());
            preparedStatement.setInt(3, count);
            preparedStatement.setString(4, product.getProductType().toString());
            preparedStatement.setString(5, product.getTechnicalSpecification());
            preparedStatement.setFloat(6, product.getPrice());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public int productCount(Product product) {
        try {
            Connection connection = getConnection();
            String query = "select count from product_store where name = ? and brand = ? and product_type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName().toLowerCase());
            preparedStatement.setString(2, product.getBrand().toLowerCase());
            preparedStatement.setString(3, product.getProductType().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                preparedStatement.close();
                connection.close();
                return count;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void productCountIncrementer(Product product, int prevCount, int count) {
        try {
            Connection connection = getConnection();
            String updateQuery = "update product_store set count = ? where name = ? and brand = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, (prevCount + count));
            preparedStatement.setString(2, product.getProductName().toLowerCase());
            preparedStatement.setString(3, product.getBrand().toLowerCase());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> availableProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try {
            Connection connection = getConnection();
            String query = "select * from product_store";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductName(resultSet.getString(1));
                product.setBrand(resultSet.getString(2));
                product.setNumOfAvailableProducts(resultSet.getInt(3));
                product.setProductType(stringToProductType(resultSet.getString(4)));
                product.setTechnicalSpecification(resultSet.getString(5));
                product.setPrice((float) resultSet.getDouble(6));
                allProducts.add(product);
            }
            return allProducts;
        } catch (SQLException e) {
            System.out.println("__CAN'T REACH TO THE STORE DATABASE!");
            return null;
        }
    }

    public ProductType stringToProductType(String productType) {
        switch (productType) {
            case "Electrical":
                return ProductType.Electrical;
            case "Shoes":
                return ProductType.Shoes;
            case "Stationery":
                return ProductType.Stationery;
        }
        return null;
    }

    public void updateStoreAfterShopping(ArrayList<Product> basket) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        for (Product basketProduct : basket
        ) {

            int count = productCount(basketProduct);
            String updateQuery = "update product_store set count = ? where name = ? and brand = ? and product_type = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, count - basketProduct.getNumOfAvailableProducts());
            preparedStatement.setString(2, basketProduct.getProductName());
            preparedStatement.setString(3, basketProduct.getBrand());
            preparedStatement.setString(4, basketProduct.getProductType().toString());
            preparedStatement.execute();
        }
        String updateQuery = "delete from product_store where count = 0";
        preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();

    }
}
