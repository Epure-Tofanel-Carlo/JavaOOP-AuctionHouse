package menu;
import java.util.Scanner;
public class MenuUser
{

    public static void menuUser(Scanner scanner)
    {
        System.out.println("--------------------------------------------" +
                "                Bine ai venit!" +
                "--------------------------------------------");
        while (true)
        {
            System.out.println("Ai de ales dintre urmatoarele actiuni" +
                    "1) Manage Payment Method" +
                    "2) Manage Personal Data" +
                    "3) My Auctions" +
                    "4) See Auction Categories");
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
