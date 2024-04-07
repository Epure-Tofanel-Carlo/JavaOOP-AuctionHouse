package service;

import daoservices.UserRepositoryService;
import models.Administrator;
import models.RegularUser;

import java.util.List;
import java.util.Scanner;

public class UserService
{
    private UserRepositoryService userRepositoryService;

    public UserService()
    {
        this.userRepositoryService = new UserRepositoryService();
    }

    public void createAdministrator(Scanner scanner)
    {
        System.out.println("Enter administrator details...");
        System.out.println("ID:");
        String id = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        Administrator admin = new Administrator();
        admin.setUserId(id);
        admin.setEmail(email);
        admin.setUserName(username);
        admin.setUserPassword(password);

        try
        {
            userRepositoryService.createAdministrator(admin);
            System.out.println("Administrator created successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void createRegularUser(Scanner scanner)
    {
        System.out.println("Enter regular user details...");
        System.out.println("ID:");
        String id = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        RegularUser user = new RegularUser(); // Adjust if constructor parameters are different
        user.setUserId(id);
        user.setEmail(email);
        user.setUserName(username);
        user.setUserPassword(password);

        try
        {
            userRepositoryService.createRegularUser(user);
            System.out.println("Regular user created successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void readAdministratorById(Scanner scanner)
    {
        System.out.println("Enter administrator ID:");
        String adminId = scanner.nextLine();
        Administrator admin = userRepositoryService.readAdministratorById(adminId);
        if (admin != null)
        {
            System.out.println(admin);
        } else
        {
            System.out.println("Administrator not found.");
        }
    }

    public void readRegularUserById(Scanner scanner)
    {
        System.out.println("Enter regular user ID:");
        String userId = scanner.nextLine();
        RegularUser user = userRepositoryService.readRegularUserById(userId);
        if (user != null)
        {
            System.out.println(user);
        } else
        {
            System.out.println("Regular user not found.");
        }
    }

    public void deleteAdministrator(Scanner scanner)
    {
        System.out.println("Enter administrator ID to delete:");
        String adminId = scanner.nextLine();
        try
        {
            userRepositoryService.deleteAdministrator(adminId);
            System.out.println("Administrator deleted successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteRegularUser(Scanner scanner)
    {
        System.out.println("Enter regular user ID to delete:");
        String userId = scanner.nextLine();
        try
        {
            userRepositoryService.deleteRegularUser(userId);
            System.out.println("Regular user deleted successfully.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listAllAdministrators()
    {
        List<Administrator> admins = userRepositoryService.getAllAdministrators();
        if (admins.isEmpty())
        {
            System.out.println("No administrators found.");
        } else
        {
            admins.forEach(System.out::println);
        }
    }

    public void listAllRegularUsers()
    {
        List<RegularUser> users = userRepositoryService.getAllRegularUsers();
        if (users.isEmpty())
        {
            System.out.println("No regular users found.");
        } else
        {
            users.forEach(System.out::println);
        }
    }

    public boolean login(Scanner scanner)
    {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        RegularUser user = userRepositoryService.readRegularUserByName(username);
        if (user != null && user.getUserPassword().equals(password))
        {
            System.out.println("Login successful.");
            return true;
        } else
        {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public void manageUsers(Scanner scanner) {
        while (true) {
            System.out.println("User Management:" +
                    "\n1) Create Administrator" +
                    "\n2) Create Regular User" +
                    "\n3) View Administrator" +
                    "\n4) View Regular User" +
                    "\n5) Delete Administrator" +
                    "\n6) Delete Regular User" +
                    "\n7) List All Administrators" +
                    "\n8) List All Regular Users" +
                    "\n9) Return to Main Menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createAdministrator(scanner);
                    break;
                case "2":
                    createRegularUser(scanner);
                    break;
                case "3":
                    readAdministratorById(scanner);
                    break;
                case "4":
                    readRegularUserById(scanner);
                    break;
                case "5":
                    deleteAdministrator(scanner);
                    break;
                case "6":
                    deleteRegularUser(scanner);
                    break;
                case "7":
                    listAllAdministrators();
                    break;
                case "8":
                    listAllRegularUsers();
                    break;
                case "9":
                    return; // return
                default:
                    System.out.println("Invalid option please try again");
            }
        }
    }


}
