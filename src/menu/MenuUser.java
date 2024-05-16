package menu;
import daoservices.ItemRepositoryService;
import daoservices.UserRepositoryService;
import models.Bid;
import models.Item;
import models.RegularUser;
import service.BidService;
import service.ItemService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuUser {
    private static UserService userService;
    private static ItemService itemService;
    private static BidService bidService;

    public MenuUser() {
        userService = new UserService();
        itemService = new ItemService(userService);
        bidService = new BidService(new ItemRepositoryService(), new UserRepositoryService());
    }
    public static void menuUser(Scanner scanner) {
        System.out.println("User Menu");
        while (true) {
            System.out.println("Select an option:" +
                    "\n1) Create Regular User Account" +
                    "\n2) Login" +
                    "\n3) Create Item" +
                    "\n4) Place Bid" +
                    "\n5) View My Bids" +
                    "\n6) View All Items" +
                    "\n7) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    if (userService.getCurrentUser() == null) {
                        userService.createUser(scanner);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "2":
                    RegularUser user = login(scanner);
                    if (user != null) {
                        bidService = new BidService(new ItemRepositoryService(), new UserRepositoryService());
                    }
                    break;
                case "3":
                    itemService.createItem(scanner);
                    break;
                case "4":
                    placeBid(scanner);
                    break;
                case "5":
                    viewMyBids(scanner);
                    break;
                case "6":
                    itemService.viewAllItems();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private static RegularUser login(Scanner scanner) {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        return userService.login(username, password);
    }
    private static void placeBid(Scanner scanner) {
        if (bidService == null) {
            System.out.println("You need to log in first to place a bid.");
            return;
        }

        System.out.println("Enter item ID:");
        String itemId = scanner.nextLine();
        System.out.println("Enter bid amount:");
        double bidAmount = scanner.nextDouble();
        scanner.nextLine();

        try {
            bidService.placeBid(userService.getCurrentUser().getUserId(), itemId, bidAmount);
            System.out.println("Bid placed successfully.");
        } catch (SQLException e) {
            System.out.println("Database error occurred while placing the bid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void viewMyBids(Scanner scanner) {
        if (bidService == null) {
            System.out.println("You need to log in first to view your bids.");
            return;
        }

        try {
            List<Bid> bids = bidService.getBidsByUser(userService.getCurrentUser().getUserId());
            if (bids.isEmpty()) {
                System.out.println("You have not placed any bids yet.");
            } else {
                System.out.println("Your Bids:");
                for (Bid bid : bids) {
                    System.out.println(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred while retrieving your bids: " + e.getMessage());
        }
    }

    private static void addBalance(Scanner scanner) {
        if (userService.getCurrentUser() == null) {
            System.out.println("You need to log in first to add balance.");
            return;
        }

        System.out.println("Enter the amount to add:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            userService.addBalance(userService.getCurrentUser().getUserId(), amount);
        } catch (SQLException e) {
            System.out.println("Error adding balance: " + e.getMessage());
        }
    }

}