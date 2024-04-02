package service;
import java.util.Scanner;
import daoservices.ItemRepositoryService;

public class ItemService
{
    private static ItemRepositoryService database;

    public ItemService()
    {
        this.database = new ItemRepositoryService();
    }

}
