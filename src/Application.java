import menu.MenuAdministrator;
import menu.MenuUser;

import java.util.Scanner;

public class Application
{
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        MenuAdministrator.menuAdministrator(scanner);
        MenuUser.menuUser(scanner);

    }
}
