package dao;

import daoservices.DatabaseConnection;
import models.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements DaoInterface<Category> {
    private Connection connection;

    public CategoryDao() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Category category) throws SQLException {
        String sql = "INSERT INTO categories (category_id, name, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.executeUpdate();
        }
    }

    @Override
    public Category read(String categoryId) throws SQLException {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                return new Category(categoryId, name, description);
            }
        }
        return null;
    }

    @Override
    public void delete(Category category) throws SQLException {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryId());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getCategoryId());
            statement.executeUpdate();
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM categories";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                String categoryId = resultSet.getString("category_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                categories.add(new Category(categoryId, name, description));
            }
            return categories;
        }
    }
}