package service;

import daoservices.ItemRepositoryService;
import models.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ItemService {
    private ItemRepositoryService itemRepositoryService;

    public ItemService() {
        this.itemRepositoryService = new ItemRepositoryService();
    }

    public void createItem(Scanner scanner)
    {
        System.out.println("Enter type of item [artcollectibles/clothes/electronic]:");
        String typeOfItem = scanner.nextLine().toLowerCase();

        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        System.out.println("Enter item start price:");
        int startPrice = Integer.parseInt(scanner.nextLine());


        int currentBid = 0;
        RegularUser leadingBidder = null;
        RegularUser userSeller = null;
        long bidEndTime = System.currentTimeMillis();
        String categoryId = "";

        try
        {
            Item item = ItemFactory.createItem(typeOfItem, name, description, startPrice, currentBid, leadingBidder, userSeller, bidEndTime, categoryId, false);
            itemRepositoryService.createItem(item);
            System.out.println("Item created successfully: " + item);
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error creating item: " + e.getMessage());
        }
    }


    public void readItemById(Scanner scanner)
    {
        System.out.println("Enter item ID:");
        UUID itemId = UUID.fromString(scanner.nextLine());
        Item item = itemRepositoryService.readItemById(itemId);
        if (item != null)
        {
            System.out.println(item);
        } else
        {
            System.out.println("Item not found");
        }
    }

    public void deleteItem(Scanner scanner)
    {
        System.out.println("Enter item ID:");
        UUID itemId = UUID.fromString(scanner.nextLine());
        itemRepositoryService.deleteItem(itemId);
    }

    public void findItemsByCategory(Scanner scanner)
    {
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        List<Item> items = itemRepositoryService.findByCategory(category);
        items.forEach(System.out::println);
    }

    public void searchItemsByName(Scanner scanner)
    {
        System.out.println("Enter name to search:");
        String name = scanner.nextLine();
        List<Item> items = itemRepositoryService.searchByName(name);
        items.forEach(System.out::println);
    }

    public void findItemsByPriceRange(Scanner scanner)
    {
        System.out.println("Enter minimum price:");
        double min = scanner.nextDouble();
        System.out.println("Enter maximum price:");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Item> items = itemRepositoryService.findItemsByPriceRange(min, max);
        items.forEach(System.out::println);
    }

    public void manageItems(Scanner scanner) {
        while (true) {
            System.out.println("Item Management:" +
                    "\n1) Create Item" +
                    "\n2) View Item" +
                    "\n3) Delete Item" +
                    "\n4) Find Items by Category" +
                    "\n5) Search Items by Name" +
                    "\n6) Find Items by Price Range" +
                    "\n7) Return to Main Menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createItem(scanner);
                    break;
                case "2":
                    readItemById(scanner);
                    break;
                case "3":
                    deleteItem(scanner);
                    break;
                case "4":
                    findItemsByCategory(scanner);
                    break;
                case "5":
                    searchItemsByName(scanner);
                    break;
                case "6":
                    findItemsByPriceRange(scanner);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
