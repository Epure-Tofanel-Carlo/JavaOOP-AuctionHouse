package dao;

import models.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();

    public void createCategory(Category category)
    {
        categoryList.add(category);
    }

    public Category readCategoryById(String categoryId)
    {
        for (Category category : categoryList)
        {
            if (category.getCategoryId().equals(categoryId))
            {
                return category;
            }
        }
        return null;
    }

    public void removeCategory(Category category)
    {
        categoryList.remove(category);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryList);
    }


}
