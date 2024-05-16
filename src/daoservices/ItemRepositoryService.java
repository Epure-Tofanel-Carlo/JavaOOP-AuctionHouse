package daoservices;

import dao.ItemDao;
import models.Bid;
import models.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ItemRepositoryService {
    private ItemDao itemDao;

    public ItemRepositoryService() {
        try {
            itemDao = new ItemDao();
        } catch (SQLException e) {

            throw new RuntimeException("Failed to create ItemDao", e);
        }
    }
    public void createItem(Item item) throws SQLException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (item.getUserSeller() == null) {
            throw new IllegalArgumentException("Item seller cannot be null");
        }
        itemDao.add(item);
    }

    public Item readItemById(UUID itemId) throws SQLException {
        if (itemId == null) {
            throw new IllegalArgumentException("Item ID cannot be null");
        }
        return itemDao.read(itemId.toString());
    }

    public void updateItem(Item item) throws SQLException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        itemDao.update(item);
    }

    public void deleteItem(Item item) throws SQLException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        itemDao.delete(item);
    }

    public List<Item> findByCategory(String category) throws SQLException {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        List<Item> items = itemDao.getAllItems();
        items.removeIf(item -> !item.getCategoryId().equals(category));
        return items;
    }

    public List<Item> searchByName(String name) throws SQLException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        List<Item> items = itemDao.getAllItems();
        items.removeIf(item -> !item.getName().toLowerCase().contains(name.toLowerCase()));
        return items;
    }

    public List<Item> findItemsByPriceRange(double min, double max) throws SQLException {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException("Invalid price range");
        }
        List<Item> items = itemDao.getAllItems();
        items.removeIf(item -> {
            int price = item.getCurrentBid() > 0 ? item.getCurrentBid() : item.getStartPrice();
            return price < min || price > max;
        });
        return items;
    }

    public List<Item> getAllItems() throws SQLException {
        return itemDao.getAllItems();
    }

    public void placeBid(Item item, Bid bid) throws SQLException {
        itemDao.placeBid(item, bid);
    }

    public List<Bid> getBidsForItem(UUID itemId) throws SQLException {
        return itemDao.getBidsForItem(itemId.toString());
    }

    public Bid getHighestBid(UUID itemId) throws SQLException {
        return itemDao.getHighestBid(itemId.toString());
    }
}