package menu;

import service.UserService;
import service.ItemService;
import service.CategoryService;

import java.util.Scanner;

public class MenuAdministrator {
    private static UserService userService = new UserService();
    private static ItemService itemService = new ItemService();
    private static CategoryService categoryService = new CategoryService();

    public static void menuAdministrator(Scanner scanner) {
        System.out.println("Administrator Menu");
        while (true) {
            System.out.println("Select an option:" +
                    "\n1) Manage Users" +
                    "\n2) Manage Items" +
                    "\n3) Manage Categories" +
                    "\n4) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.manageUsers(scanner);
                    break;
                case "2":
                    itemService.manageItems(scanner);
                    break;
                case "3":
                    manageCategories(scanner);
                    break;
                case "4":
                    return; // return
                default:
                    System.out.println("Invalid option please try again");
            }
        }
    }

    private static void manageCategories(Scanner scanner) {
        System.out.println("Category Management:");
        System.out.println("1) Create Category" +
                "\n2) View Category" +
                "\n3) Delete Category" +
                "\n4) List All Categories" +
                "\n5) Return to Main Menu");
        String choice = scanner.nextLine();
        switch (choice)
        {
            case "1":
                categoryService.createCategory(scanner);
                break;
            case "2":
                categoryService.readCategoryById(scanner);
                break;
            case "3":
                categoryService.deleteCategory(scanner);
                break;
            case "4":
                categoryService.listAllCategories();
                break;
            case "5":
                return; // return
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

}
