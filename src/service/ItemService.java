package service;

import daoservices.ItemRepositoryService;
import daoservices.UserRepositoryService;
import models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ItemService {
    private ItemRepositoryService itemRepositoryService;

    public ItemService() {
        try {
            this.itemRepositoryService = new ItemRepositoryService();
        } catch (RuntimeException e) {
            System.out.println("Error creating ItemRepositoryService: " + e.getMessage());
        }
    }

    public void createItem(Scanner scanner) {
        System.out.println("Enter type of item [artcollectibles/clothes/electronic]:");
        String typeOfItem = scanner.nextLine().toLowerCase();

        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        int startPrice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter item start price:");
                startPrice = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        int currentBid = 0;
        RegularUser leadingBidder = null;
        RegularUser userSeller = null;

        System.out.println("Enter seller's username or ID:");
        String sellerInput = scanner.nextLine();
        try {

            UserRepositoryService userRepositoryService = new UserRepositoryService();
            userSeller = (RegularUser) userRepositoryService.readUserByName(sellerInput);
            if (userSeller == null)
            {
                userSeller = (RegularUser) userRepositoryService.readUserById(sellerInput);
                if (userSeller == null)
                {
                    System.out.println("Seller not found. Item creation canceled.");
                    return;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving seller: " + e.getMessage());
            return;
        }
        long bidEndTime = System.currentTimeMillis();
        System.out.println("Enter category ID:");
        String categoryId = scanner.nextLine();

        if (typeOfItem.equals("artcollectibles")) {
            System.out.println("Enter artist:");
            String artist = scanner.nextLine();
            System.out.println("Enter medium:");
            String medium = scanner.nextLine();
            System.out.println("Enter dimensions:");
            String dimensions = scanner.nextLine();
            System.out.println("Enter year:");
            int year = Integer.parseInt(scanner.nextLine());
            try {
                Item item = new ItemArtCollectibles(name, description, startPrice, currentBid, leadingBidder, userSeller, bidEndTime, categoryId, false, artist, medium, dimensions, year);
                itemRepositoryService.createItem(item);
                System.out.println("Item created successfully: " + item);
            } catch (IllegalArgumentException | SQLException e) {
                System.out.println("Error creating item: " + e.getMessage());
            }
        } else if (typeOfItem.equals("clothes")) {
            System.out.println("Enter brand:");
            String brand = scanner.nextLine();
            System.out.println("Enter size:");
            String size = scanner.nextLine();
            System.out.println("Enter color:");
            String color = scanner.nextLine();
            System.out.println("Enter material:");
            String material = scanner.nextLine();
            System.out.println("Enter condition:");
            String condition = scanner.nextLine();
            System.out.println("Enter sex:");
            String sex = scanner.nextLine();
            try {
                Item item = new ItemClothes(name, description, startPrice, currentBid, leadingBidder, userSeller, bidEndTime, categoryId, false, brand, size, color, material, condition, sex);
                itemRepositoryService.createItem(item);
                System.out.println("Item created successfully: " + item);
            } catch (IllegalArgumentException | SQLException e) {
                System.out.println("Error creating item: " + e.getMessage());
            }
        } else if (typeOfItem.equals("electronic")) {
            System.out.println("Enter brand:");
            String brand = scanner.nextLine();
            System.out.println("Enter model:");
            String model = scanner.nextLine();
            System.out.println("Enter specifications:");
            String specifications = scanner.nextLine();
            System.out.println("Enter condition:");
            String condition = scanner.nextLine();
            System.out.println("Enter manufacture year:");
            int manufactureYear = Integer.parseInt(scanner.nextLine());
            try {
                Item item = new ItemElectronic(name, description, startPrice, currentBid, leadingBidder, userSeller, bidEndTime, categoryId, false, brand, model, specifications, condition, manufactureYear);
                itemRepositoryService.createItem(item);
                System.out.println("Item created successfully: " + item);
            } catch (IllegalArgumentException | SQLException e) {
                System.out.println("Error creating item: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid item type.");
        }
    }

    public void readItemById(Scanner scanner) {
        System.out.println("Enter item ID:");
        String input = scanner.nextLine();
        try {
            UUID itemId = UUID.fromString(input);
            Item item = itemRepositoryService.readItemById(itemId);
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("Item not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid item ID format. Please try again.");
        } catch (SQLException e) {
            System.out.println("Error reading item: " + e.getMessage());
        }
    }

    public void updateItem(Scanner scanner) {
        System.out.println("Enter item ID:");
        UUID itemId = UUID.fromString(scanner.nextLine());
        try {
            Item item = itemRepositoryService.readItemById(itemId);
            if (item != null) {
                System.out.println("Enter new name (leave blank to keep current value):");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    item.setName(newName);
                }

                System.out.println("Enter new description (leave blank to keep current value):");
                String newDescription = scanner.nextLine();
                if (!newDescription.isEmpty()) {
                    item.setDescription(newDescription);
                }

                System.out.println("Enter new start price (enter 0 to keep current value):");
                int newStartPrice = Integer.parseInt(scanner.nextLine());
                if (newStartPrice != 0) {
                    item.setStartPrice(newStartPrice);
                }

                itemRepositoryService.updateItem(item);
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Item not found");
            }
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    public void deleteItem(Scanner scanner) {
        System.out.println("Enter item ID:");
        UUID itemId = UUID.fromString(scanner.nextLine());
        try {
            Item item = itemRepositoryService.readItemById(itemId);
            if (item != null) {
                itemRepositoryService.deleteItem(item);
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Item not found");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    public void findItemsByCategory(Scanner scanner) {
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        try {
            List<Item> items = itemRepositoryService.findByCategory(category);
            items.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error finding items by category: " + e.getMessage());
        }
    }

    public void searchItemsByName(Scanner scanner) {
        System.out.println("Enter name to search:");
        String name = scanner.nextLine();
        try {
            List<Item> items = itemRepositoryService.searchByName(name);
            items.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error searching items by name: " + e.getMessage());
        }
    }

    public void findItemsByPriceRange(Scanner scanner) {
        System.out.println("Enter minimum price:");
        double min = scanner.nextDouble();
        System.out.println("Enter maximum price:");
        double max = scanner.nextDouble();
        scanner.nextLine();
        try {
            List<Item> items = itemRepositoryService.findItemsByPriceRange(min, max);
            items.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error finding items by price range: " + e.getMessage());
        }
    }

    public void viewAllItems() {
        try {
            List<Item> items = itemRepositoryService.getAllItems();
            if (items.isEmpty()) {
                System.out.println("No items found.");
            } else {
                System.out.println("All Items:");
                for (Item item : items) {
                    System.out.println(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving items: " + e.getMessage());
        }
    }

    public void manageItems(Scanner scanner) {
        while (true) {
            System.out.println("Item Management:" +
                    "\n1) Create Item" +
                    "\n2) View Item" +
                    "\n3) Update Item" +
                    "\n4) Delete Item" +
                    "\n5) Find Items by Category" +
                    "\n6) Search Items by Name" +
                    "\n7) Find Items by Price Range" +
                    "\n8) View all Items" +
                    "\n9) Return to Main Menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createItem(scanner);
                    break;
                case "2":
                    readItemById(scanner);
                    break;
                case "3":
                    updateItem(scanner);
                    break;
                case "4":
                    deleteItem(scanner);
                    break;
                case "5":
                    findItemsByCategory(scanner);
                    break;
                case "6":
                    searchItemsByName(scanner);
                    break;
                case "7":
                    findItemsByPriceRange(scanner);
                    break;
                case "8":
                    viewAllItems();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}