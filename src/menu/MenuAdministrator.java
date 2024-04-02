package menu;
import java.util.Scanner;
public class MenuAdministrator
{

    public static void menuAdmin(Scanner scanner)
    {
        System.out.println("--------------------------------------------" +
                "Bine ai venit in Interfata de Administrator" +
                "--------------------------------------------");
        while (true)
        {
            System.out.println("Ai de ales dintre urmatoarele actiuni" +
                    "1) Manage Categories" +
                    "2) Manage Users" +
                    "3) Manage Items");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1": // functie specifica separata, chiar aici in meniu
                case "2": // functie specifica separata, chiar aici in meniu
                case "3": // functie specifica separata, chiar aici in meniu
                case "4": // functie specifica separata, chiar aici in meniu
                default:
            }

        }
    }
}
