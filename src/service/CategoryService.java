package service;

import daoservices.CategoryRepositoryService;
import models.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryService
{
    private CategoryRepositoryService categoryRepositoryService;

    public CategoryService()
    {
        this.categoryRepositoryService = new CategoryRepositoryService();
    }

    public void createCategory(Scanner scanner)
    {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        System.out.println("Enter category description:");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(categoryId, categoryName, categoryDescription);
        try
        {
            categoryRepositoryService.createCategory(category);
            System.out.println("Category created successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error creating category: " + e.getMessage());
        }
    }

    public void readCategoryById(Scanner scanner)
    {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        Category category = categoryRepositoryService.readCategoryById(categoryId);
        if (category != null) {
            System.out.println(category);
        } else {
            System.out.println("Category not found");
        }
    }

    public void deleteCategory(Scanner scanner)
    {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        try
        {
            categoryRepositoryService.deleteCategory(categoryId);
            System.out.println("Category deleted successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error deleting category: " + e.getMessage());
        }
    }

    public void listAllCategories()
    {
        List<Category> categories = categoryRepositoryService.getAllCategories();
        if (categories.isEmpty())
        {
            System.out.println("No categories available.");
        } else
        {
            categories.forEach(System.out::println);
        }
    }


}
