package service;

import daoservices.ItemRepositoryService;
import daoservices.UserRepositoryService;
import models.Bid;
import models.Item;
import models.RegularUser;
import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BidService {
    private ItemRepositoryService itemRepositoryService;
    private UserRepositoryService userRepositoryService;

    public BidService(ItemRepositoryService itemRepositoryService, UserRepositoryService userRepositoryService) {
        this.itemRepositoryService = itemRepositoryService;
        this.userRepositoryService = userRepositoryService;
    }

    public void placeBid(String userId, String itemId, double bidAmount) throws SQLException {
        User user = userRepositoryService.readUserById(userId);
        Item item = itemRepositoryService.readItemById(UUID.fromString(itemId));

        if (!(user instanceof RegularUser)) {
            throw new IllegalArgumentException("Only regular users can place bids");
        }

        RegularUser regularUser = (RegularUser) user;

        if (bidAmount > regularUser.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance to place the bid");
        }

        if (bidAmount <= item.getCurrentBid()) {
            throw new IllegalArgumentException("Bid amount must be higher than the current bid");
        }

        if (item.getBidEndTime() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Auction for this item has ended");
        }

        Bid bid = new Bid(bidAmount, regularUser, item);
        itemRepositoryService.placeBid(item, bid);

        regularUser.setBalance(regularUser.getBalance() - (int) bidAmount);
        userRepositoryService.updateUser(regularUser);
    }

    public List<Bid> getBidsByUser(String userId) throws SQLException {
        return userRepositoryService.getBidsByUser(userId);
    }

    public List<Bid> getBidsForItem(UUID itemId) throws SQLException {
        return itemRepositoryService.getBidsForItem(itemId);
    }

    public Bid getHighestBid(UUID itemId) throws SQLException {
        return itemRepositoryService.getHighestBid(itemId);
    }
}