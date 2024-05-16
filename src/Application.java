import menu.MenuAdministrator;
import menu.MenuUser;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuAdministrator menuAdministrator = new MenuAdministrator();
        MenuUser menuUser = new MenuUser();

        while (true) {
            System.out.println("Select a menu:");
            System.out.println("1) Administrator Menu");
            System.out.println("2) User Menu");
            System.out.println("3) Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    menuAdministrator.menuAdministrator(scanner);
                    break;
                case "2":
                    menuUser.menuUser(scanner);
                    break;
                case "3":
                    System.out.println("afara");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}