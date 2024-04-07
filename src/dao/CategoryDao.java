package dao;

import models.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();

    public void createCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        categoryList.add(category);
    }

    public Category readCategoryById(String categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        for (Category category : categoryList) {
            if (category.getCategoryId().equals(categoryId)) {
                return category;
            }
        }
        return null;
    }

    public void removeCategory(Category category) {
        if (category == null || !categoryList.contains(category)) {
            throw new IllegalArgumentException("Category not found or is null");
        }
        categoryList.remove(category);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryList);
    }


}
