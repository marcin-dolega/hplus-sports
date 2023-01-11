package pl.dolega.hplussports.dao;

import java.sql.*;

import pl.dolega.hplussports.beans.Product;
import pl.dolega.hplussports.beans.User;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public List<Product> searchProducts(String searchString) throws SQLException {

        Product product;
        List<Product> products = new ArrayList<>();
        String sql = "select * from products where product_name like '%" + searchString + "%'";

        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductImagePath(resultSet.getString("image_path"));
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
}
