package daoservices;

import dao.ItemDao;
import models.Item;
import models.ItemArtCollectibles;
import models.ItemClothes;
import models.ItemElectronic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemRepositoryService {

    private ItemDao itemDao;

    public ItemRepositoryService()
    {
        this.itemDao = new ItemDao();
    }

    public void createItem(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("Item cannot be null");
        }
        itemDao.createItem(item);
    }

    public Item readItemById(UUID itemId)
    {
        if (itemId == null)
        {
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }
        return itemDao.readItemById(itemId);
    }


    public void deleteItem(UUID itemId)
    {
        if (itemId == null)
        {
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }

        itemDao.removeItem(readItemById(itemId));
    }

    // Additional methods
    public List<Item> findByCategory(String category)
    {
        if (category == null || category.isEmpty())
        {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        List<Item> itemsInCategory = new ArrayList<>();
        for (Item item : itemDao.getAllItems())
        {
            if (item.getCategoryId().equals(category))
            {
                itemsInCategory.add(item);
            }
        }
        return itemsInCategory;
    }

    public List<Item> searchByName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        List<Item> matchedItems = new ArrayList<>();
        for (Item item : itemDao.getAllItems())
        {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {

                matchedItems.add(item);
            }
        }
        return matchedItems;
    }


    public List<Item> findItemsByPriceRange(double min, double max)
    {
        if (min < 0 || max < 0 || min > max)
        {
            throw new IllegalArgumentException("Invalid price range");
        }

        List<Item> itemsInPriceRange = new ArrayList<>();
        for (Item item : itemDao.getAllItems())
        {
            int price;
            if (item.getCurrentBid() > 0)
            {
                price = item.getCurrentBid();
            } else
            {
                price = item.getStartPrice();
            }

            if (price >= min && price <= max)
            {
                itemsInPriceRange.add(item);
            }
        }

        return itemsInPriceRange;
    }
}
