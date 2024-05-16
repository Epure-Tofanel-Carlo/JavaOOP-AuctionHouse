package daoservices;

import dao.CategoryDao;
import models.Category;
import java.sql.SQLException;
import java.util.List;

public class CategoryRepositoryService {

    private CategoryDao categoryDao;

    public CategoryRepositoryService() throws SQLException {
        this.categoryDao = new CategoryDao();
    }

    public void createCategory(Category category) throws SQLException {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (categoryExists(category.getCategoryId())) {
            throw new IllegalArgumentException("Category with this ID already exists");
        }
        categoryDao.add(category);
    }

    private boolean categoryExists(String categoryId) throws SQLException {
        return categoryDao.read(categoryId) != null;
    }

    public Category readCategoryById(String categoryId) throws SQLException {
        if (categoryId == null || categoryId.isEmpty()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        return categoryDao.read(categoryId);
    }

    public void deleteCategory(String categoryId) throws SQLException {
        Category category = readCategoryById(categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
        categoryDao.delete(category);
    }

    public List<Category> getAllCategories() throws SQLException
    {
        return categoryDao.getAllCategories();
    }
}