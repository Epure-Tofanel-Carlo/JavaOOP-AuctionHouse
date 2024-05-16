package service;

import daoservices.CategoryRepositoryService;
import models.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private CategoryRepositoryService categoryRepositoryService;

    public CategoryService() {
        try {
            this.categoryRepositoryService = new CategoryRepositoryService();
        } catch (SQLException e) {
            System.out.println("Error creating CategoryRepositoryService: " + e.getMessage());
        }
    }

    public void createCategory(Scanner scanner) {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        System.out.println("Enter category description:");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(categoryId, categoryName, categoryDescription);
        try {
            categoryRepositoryService.createCategory(category);
            System.out.println("Category created successfully.");
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Error creating category: " + e.getMessage());
        }
    }

    public void readCategoryById(Scanner scanner) {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        try {
            Category category = categoryRepositoryService.readCategoryById(categoryId);
            if (category != null) {
                System.out.println(category);
            } else {
                System.out.println("Category not found");
            }
        } catch (SQLException e) {
            System.out.println("Error reading category: " + e.getMessage());
        }
    }

    public void deleteCategory(Scanner scanner) {
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();
        try {
            categoryRepositoryService.deleteCategory(categoryId);
            System.out.println("Category deleted successfully.");
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Error deleting category: " + e.getMessage());
        }
    }

    public void listAllCategories() {
        try {
            List<Category> categories = categoryRepositoryService.getAllCategories();
            if (categories.isEmpty()) {
                System.out.println("No categories available.");
            } else {
                categories.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving categories: " + e.getMessage());
        }
    }

    public void manageCategories(Scanner scanner) {
        System.out.println("Category Management:");
        System.out.println("1) Create Category" +
                "\n2) View Category" +
                "\n3) Delete Category" +
                "\n4) List All Categories" +
                "\n5) Return to Main Menu");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createCategory(scanner);
                break;
            case "2":
                readCategoryById(scanner);
                break;
            case "3":
                deleteCategory(scanner);
                break;
            case "4":
                listAllCategories();
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}