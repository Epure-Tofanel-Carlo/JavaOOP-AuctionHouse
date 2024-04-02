package daoservices;

import dao.ItemDao;

public class ItemRepositoryService
{

    private ItemDao itemDao;

    public ItemRepositoryService()
    {
        this.itemDao = new ItemDao();
    }


}
