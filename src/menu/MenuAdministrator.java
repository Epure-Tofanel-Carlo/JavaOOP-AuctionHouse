package menu;

import daoservices.ItemRepositoryService;
import daoservices.UserRepositoryService;
import models.Bid;
import service.BidService;
import service.CategoryService;
import service.ItemService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuAdministrator {
    private static UserService userService;
    private static ItemService itemService;
    private static BidService bidService;
    private static CategoryService categoryService;

    public MenuAdministrator() {
        userService = new UserService();
        itemService = new ItemService(userService);
        bidService = new BidService(new ItemRepositoryService(), new UserRepositoryService());
        categoryService = new CategoryService();
    }
    public static void menuAdministrator(Scanner scanner) {
        System.out.println("Administrator Menu");
        while (true) {
            System.out.println("Select an option:" +
                    "\n1) Manage Users" +
                    "\n2) Manage Items" +
                    "\n3) Manage Categories" +
                    "\n4) Manage Bids" +
                    "\n5) Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.manageUsers(scanner);
                    break;
                case "2":
                    itemService.manageItems(scanner);
                    break;
                case "3":
                    categoryService.manageCategories(scanner);
                    break;
                case "4":
                    bidService = new BidService(new ItemRepositoryService(), new UserRepositoryService());
                    manageBids(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private static void manageBids(Scanner scanner) {
        System.out.println("Bid Management:");
        System.out.println("1) View Bids for Item" +
                "\n2) View Highest Bid for Item" +
                "\n3) Return to Main Menu");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                viewBidsForItem(scanner);
                break;
            case "2":
                viewHighestBidForItem(scanner);
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void viewBidsForItem(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemIdString = scanner.nextLine();
        try {
            UUID itemId = UUID.fromString(itemIdString);
            List<Bid> bids = bidService.getBidsForItem(itemId);
            if (bids.isEmpty()) {
                System.out.println("No bids found for this item.");
            } else {
                System.out.println("Bids for Item " + itemIdString + ":");
                for (Bid bid : bids) {
                    System.out.println(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred while retrieving bids: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void viewHighestBidForItem(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemIdString = scanner.nextLine();
        try {
            UUID itemId = UUID.fromString(itemIdString);
            Bid highestBid = bidService.getHighestBid(itemId);
            if (highestBid == null) {
                System.out.println("No bids found for this item.");
            } else {
                System.out.println("Highest Bid for Item " + itemIdString + ":");
                System.out.println(highestBid);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred while retrieving the highest bid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}