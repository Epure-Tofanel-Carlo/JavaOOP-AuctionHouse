package service;

import daoservices.UserRepositoryService;
import models.Administrator;
import models.RegularUser;
import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private UserRepositoryService userRepositoryService;
    private RegularUser currentUser;

    public UserService() {
        try {
            this.userRepositoryService = new UserRepositoryService();
        } catch (RuntimeException e) {
            System.out.println("Error creating UserRepositoryService: " + e.getMessage());

        }
    }

    public void createUser(Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        RegularUser user = new RegularUser();
        user.setEmail(email);
        user.setUserName(username);
        user.setUserPassword(password);

        System.out.println("Enter credit card:");
        String creditCard = scanner.nextLine();
        System.out.println("Enter balance:");
        int balance = scanner.nextInt();
        scanner.nextLine();

        user.setCreditCard(creditCard);
        user.setBalance(balance);

        try {
            userRepositoryService.createUser(user);
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public void readUserById(Scanner scanner) {
        System.out.println("Enter user ID:");
        String userId = scanner.nextLine();

        try {
            User user = userRepositoryService.readUserById(userId);
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error reading user: " + e.getMessage());
        }
    }

    public void updateUser(Scanner scanner) {
        System.out.println("Enter user ID:");
        String userId = scanner.nextLine();

        try {
            User user = userRepositoryService.readUserById(userId);
            if (user != null) {
                System.out.println("Enter new email:");
                String email = scanner.nextLine();
                System.out.println("Enter new username:");
                String username = scanner.nextLine();
                System.out.println("Enter new password:");
                String password = scanner.nextLine();

                user.setEmail(email);
                user.setUserName(username);
                user.setUserPassword(password);

                if (user instanceof RegularUser) {
                    System.out.println("Enter new credit card:");
                    String creditCard = scanner.nextLine();
                    System.out.println("Enter new balance:");
                    int balance = scanner.nextInt();
                    scanner.nextLine(); // omor newline u

                    ((RegularUser) user).setCreditCard(creditCard);
                    ((RegularUser) user).setBalance(balance);
                }

                userRepositoryService.updateUser(user);
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void deleteUser(Scanner scanner) {
        System.out.println("Enter user ID:");
        String userId = scanner.nextLine();

        try {
            User user = userRepositoryService.readUserById(userId);
            if (user != null) {
                userRepositoryService.deleteUser(user);
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void listAllUsers() {
        try {
            List<User> users = userRepositoryService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                System.out.println("User List:");
                for (User user : users) {
                    System.out.println(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    public RegularUser login(String username, String password) {
        try {
            User user = userRepositoryService.readUserByName(username);
            if (user instanceof RegularUser && user.getUserPassword().equals(password)) {
                System.out.println("Login successful.");
                currentUser = (RegularUser) user;
                return currentUser;
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return null;
    }

    public RegularUser getCurrentUser() {
        return currentUser;
    }

    public void addBalance(String userId, double amount) throws SQLException
    {
        User user = userRepositoryService.readUserById(userId);
        if (user instanceof RegularUser)
        {
            RegularUser regularUser = (RegularUser) user;
            double newBalance = regularUser.getBalance() + amount;
            regularUser.setBalance((int) newBalance);
            userRepositoryService.updateUser(regularUser);
            System.out.println("Balance updated successfully. New balance: " + newBalance);
        } else
        {
            System.out.println("Invalid user type. Cannot add balance.");
        }
    }




    public void manageUsers(Scanner scanner) {
        while (true) {
            System.out.println("User Management:" +
                    "\n1) Create User" +
                    "\n2) View User" +
                    "\n3) Update User" +
                    "\n4) Delete User" +
                    "\n5) List All Users" +
                    "\n6) Return to Main Menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createUser(scanner);
                    break;
                case "2":
                    readUserById(scanner);
                    break;
                case "3":
                    updateUser(scanner);
                    break;
                case "4":
                    deleteUser(scanner);
                    break;
                case "5":
                    listAllUsers();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}