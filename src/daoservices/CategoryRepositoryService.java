package daoservices;

import dao.CategoryDao;
import models.Category;
import java.util.List;

public class CategoryRepositoryService {

    private CategoryDao categoryDao;

    public CategoryRepositoryService() {
        this.categoryDao = new CategoryDao();
    }

    public void createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (categoryExists(category.getCategoryId())) {
            throw new IllegalArgumentException("Category with this ID already exists");
        }
        categoryDao.createCategory(category);
    }

    private boolean categoryExists(String categoryId) {
        return categoryDao.getAllCategories().stream()
                .anyMatch(cat -> cat.getCategoryId().equals(categoryId));
    }

    public Category readCategoryById(String categoryId) {
        if (categoryId == null || categoryId.isEmpty()) {
            throw new IllegalArgumentException("Category ID cannot be null or empty");
        }
        return categoryDao.readCategoryById(categoryId);
    }

    public void deleteCategory(String categoryId) {
        Category category = readCategoryById(categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
        categoryDao.removeCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }


}
