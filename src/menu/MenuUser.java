package menu;

import service.UserService;
import service.ItemService;

import java.util.Scanner;

public class MenuUser {
    private static UserService userService = new UserService();
    private static ItemService itemService = new ItemService();

    public static void menuUser(Scanner scanner) {
        System.out.println("User Menu");
        while (true) {
            System.out.println("Select an option:" +
                    "\n1) Create Regular User Account" +
                    "\n2) Login" +
                    "\n3) Create Item" +
                    "\n4) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.createRegularUser(scanner);
                    break;
                case "2":
                    userService.login(scanner);
                    break;
                case "3":
                    itemService.createItem(scanner);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

}
