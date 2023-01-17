package pl.dolega.hplussports.dao;

import java.sql.*;

import pl.dolega.hplussports.beans.Product;
import pl.dolega.hplussports.beans.User;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public List<Product> searchProducts(String searchString, Connection connection) throws SQLException {

        Product product;
        List<Product> products = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();

            String sql = "select * from products where product_name like '%" + searchString + "%'";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductImgPath(resultSet.getString("image_path"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {

            Connection connection = DBConnection.getConnectionToDatabase();
            String insertQuery = "insert into users values(?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());

            rowsAffected = statement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public boolean validateUser (String username, String password) {

        boolean isValidUser = false;

        try {

            Connection connection = DBConnection.getConnectionToDatabase();

            // write the insert query
            String sql = "select * from users where username=? and password=?";

            // set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // execute the statement and check whether user exists
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                isValidUser = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValidUser;

    }

    public User getProfileDetails(String username) {

        User user = null;

        try {
            // get connection to database
            Connection connection = DBConnection.getConnectionToDatabase();

            // write select query to get profile details
            String sql = "select * from users where username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            // execute query, get resultSet and return User info
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getInt("age"));
                user.setActivity(resultSet.getString("activity"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }
}
