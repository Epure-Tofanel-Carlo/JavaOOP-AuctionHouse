package dao;

import daoservices.DatabaseConnection;
import models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemDao implements DaoInterface<Item> {
     private Connection connection;
     private BidDao bidDao;

     public ItemDao() throws SQLException {
          connection = DatabaseConnection.getConnection();
          bidDao = new BidDao();
     }

     @Override
     public void add(Item item) throws SQLException {
          String sql = "INSERT INTO items (item_id, name, description, start_price, current_bid, bid_end_time, is_sold, seller_id, category_id) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getItemId().toString());
               statement.setString(2, item.getName());
               statement.setString(3, item.getDescription());
               statement.setDouble(4, item.getStartPrice());
               statement.setDouble(5, item.getCurrentBid());
               statement.setTimestamp(6, new Timestamp(item.getBidEndTime()));
               statement.setBoolean(7, item.getIsSold());
               if (item.getUserSeller() != null) {
                    statement.setString(8, item.getUserSeller().getUserId());
               } else {
                    statement.setNull(8, Types.VARCHAR);
               }
               statement.setString(9, item.getCategoryId());
               statement.executeUpdate();
               if (item instanceof ItemArtCollectibles) {
                    addItemArtCollectibles((ItemArtCollectibles) item);
               } else if (item instanceof ItemClothes) {
                    addItemClothes((ItemClothes) item);
               } else if (item instanceof ItemElectronic) {
                    addItemElectronic((ItemElectronic) item);
               }
          }
     }

     private void addItemArtCollectibles(ItemArtCollectibles item) throws SQLException {
          String sql = "INSERT INTO item_art_collectibles (item_id, artist, medium, dimensions, year) VALUES (?, ?, ?, ?, ?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getItemId().toString());
               statement.setString(2, item.getArtist());
               statement.setString(3, item.getMedium());
               statement.setString(4, item.getDimensions());
               statement.setInt(5, item.getYear());
               statement.executeUpdate();
          }
     }

     private void addItemClothes(ItemClothes item) throws SQLException {
          String sql = "INSERT INTO item_clothes (item_id, brand, size, color, material, `condition`, sex) VALUES (?, ?, ?, ?, ?, ?, ?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getItemId().toString());
               statement.setString(2, item.getBrand());
               statement.setString(3, item.getSize());
               statement.setString(4, item.getColor());
               statement.setString(5, item.getMaterial());
               statement.setString(6, item.getCondition());
               statement.setString(7, item.getSex());
               statement.executeUpdate();
          }
     }

     private void addItemElectronic(ItemElectronic item) throws SQLException {
          String sql = "INSERT INTO item_electronics (item_id, brand, model, specifications, `condition`, manufacture_year) VALUES (?, ?, ?, ?, ?, ?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getItemId().toString());
               statement.setString(2, item.getBrand());
               statement.setString(3, item.getModel());
               statement.setString(4, item.getSpecifications());
               statement.setString(5, item.getCondition());
               statement.setInt(6, item.getManufactureYear());
               statement.executeUpdate();
          }
     }

     @Override
     public Item read(String itemId) throws SQLException {
          String sql = "SELECT * FROM items WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, itemId);
               ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()) {
                    return createItemFromResultSet(resultSet);
               }
          }
          return null;
     }

     private Item createItemFromResultSet(ResultSet resultSet) throws SQLException {
          String itemType = getItemType(resultSet.getString("item_id"));
          Item item;
          if (itemType.equals("unknown")) {
               // dau skip la ce e unknown, doamne sper sa nu regret
               return null;
          } else if (itemType.equals("art_collectibles")) {
               item = createItemArtCollectiblesFromResultSet(resultSet);
          } else if (itemType.equals("clothes")) {
               item = createItemClothesFromResultSet(resultSet);
          } else if (itemType.equals("electronics")) {
               item = createItemElectronicFromResultSet(resultSet);
          } else {
               throw new IllegalArgumentException("Unknown item type");
          }
          item.setItemId(UUID.fromString(resultSet.getString("item_id")));
          item.setName(resultSet.getString("name"));
          item.setDescription(resultSet.getString("description"));
          item.setStartPrice(resultSet.getInt("start_price"));
          item.setCurrentBid(resultSet.getInt("current_bid"));
          item.setBidEndTime(resultSet.getTimestamp("bid_end_time").getTime());
          item.setIsSold(resultSet.getBoolean("is_sold"));
          item.setCategoryId(resultSet.getString("category_id"));
          return item;
     }

     private ItemArtCollectibles createItemArtCollectiblesFromResultSet(ResultSet resultSet) throws SQLException {
          ItemArtCollectibles item = new ItemArtCollectibles();
          item.setItemId(UUID.fromString(resultSet.getString("item_id")));
          item.setName(resultSet.getString("name"));
          item.setDescription(resultSet.getString("description"));
          item.setStartPrice(resultSet.getInt("start_price"));
          item.setCurrentBid(resultSet.getInt("current_bid"));
          item.setBidEndTime(resultSet.getTimestamp("bid_end_time").getTime());
          item.setIsSold(resultSet.getBoolean("is_sold"));
          String artistValue = resultSet.getString("artist");
          item.setArtist(artistValue != null ? artistValue : "");
          item.setMedium(resultSet.getString("medium"));
          item.setDimensions(resultSet.getString("dimensions"));
          item.setYear(resultSet.getInt("year"));
          return item;
     }

     private ItemClothes createItemClothesFromResultSet(ResultSet resultSet) throws SQLException {
          ItemClothes item = new ItemClothes();
          item.setItemId(UUID.fromString(resultSet.getString("item_id")));
          item.setName(resultSet.getString("name"));
          item.setDescription(resultSet.getString("description"));
          item.setStartPrice(resultSet.getInt("start_price"));
          item.setCurrentBid(resultSet.getInt("current_bid"));
          item.setBidEndTime(resultSet.getTimestamp("bid_end_time").getTime());
          item.setIsSold(resultSet.getBoolean("is_sold"));

          String sql = "SELECT brand, size, color, material, `condition`, sex FROM item_clothes WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, resultSet.getString("item_id"));
               ResultSet clothesResultSet = statement.executeQuery();
               if (clothesResultSet.next()) {
                    item.setBrand(clothesResultSet.getString("brand"));
                    item.setSize(clothesResultSet.getString("size"));
                    item.setColor(clothesResultSet.getString("color"));
                    item.setMaterial(clothesResultSet.getString("material"));
                    item.setCondition(clothesResultSet.getString("condition"));
                    item.setSex(clothesResultSet.getString("sex"));
               }
          }
          return item;
     }

     private ItemElectronic createItemElectronicFromResultSet(ResultSet resultSet) throws SQLException {
          ItemElectronic item = new ItemElectronic();
          item.setItemId(UUID.fromString(resultSet.getString("item_id")));
          item.setName(resultSet.getString("name"));
          item.setDescription(resultSet.getString("description"));
          item.setStartPrice(resultSet.getInt("start_price"));
          item.setCurrentBid(resultSet.getInt("current_bid"));
          item.setBidEndTime(resultSet.getTimestamp("bid_end_time").getTime());
          item.setIsSold(resultSet.getBoolean("is_sold"));

          String sql = "SELECT brand, model, specifications, `condition`, manufacture_year FROM item_electronics WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, resultSet.getString("item_id"));
               ResultSet electronicsResultSet = statement.executeQuery();
               if (electronicsResultSet.next()) {
                    item.setBrand(electronicsResultSet.getString("brand"));
                    item.setModel(electronicsResultSet.getString("model"));
                    item.setSpecifications(electronicsResultSet.getString("specifications"));
                    item.setCondition(electronicsResultSet.getString("condition"));
                    item.setManufactureYear(electronicsResultSet.getInt("manufacture_year"));
               }
          }
          return item;
     }
     private String getItemType(String itemId) throws SQLException {
          String[] tables = {"item_art_collectibles", "item_clothes", "item_electronics"};
          for (String table : tables) {
               String sql = "SELECT * FROM " + table + " WHERE item_id = ?";
               try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, itemId);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                         return table.substring(5); //  scot "item_" prefix
                    }
               }
          }
          // daca nu fac asta da crash la get all items, sper sa nu regret ca am adaugat unknown in loc sa tratez eroarea
          return "unknown";
     }

     @Override
     public void update(Item item) throws SQLException {
          String sql = "UPDATE items SET name = ?, description = ?, start_price = ?, current_bid = ?, bid_end_time = ?, is_sold = ? " +
                  "WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getName());
               statement.setString(2, item.getDescription());
               statement.setDouble(3, item.getStartPrice());
               statement.setDouble(4, item.getCurrentBid());
               statement.setTimestamp(5, new Timestamp(item.getBidEndTime()));
               statement.setBoolean(6, item.getIsSold());
               statement.setString(7, item.getItemId().toString());
               statement.executeUpdate();
          }
     }

     @Override
     public void delete(Item item) throws SQLException {
          String sql = "DELETE FROM items WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, item.getItemId().toString());
               statement.executeUpdate();
          }
     }

     public List<Item> getAllItems() throws SQLException {
          List<Item> items = new ArrayList<>();
          String sql = "SELECT * FROM items";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               ResultSet resultSet = statement.executeQuery();
               while (resultSet.next()) {
                    Item item = createItemFromResultSet(resultSet);
                    if (item != null) {
                         items.add(item);
                    }
               }
          }
          return items;
     }

     public void placeBid(Item item, Bid bid) throws SQLException {
          bidDao.add(bid);

          if (bid.getBidAmount() > item.getCurrentBid()) {
               String sql = "UPDATE items SET current_bid = ?, is_sold = ? WHERE item_id = ?";
               try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, bid.getBidAmount());
                    statement.setBoolean(2, bid.getBidAmount() >= item.getStartPrice());
                    statement.setString(3, item.getItemId().toString());
                    statement.executeUpdate();

                    item.setCurrentBid((int) bid.getBidAmount());
                    item.setLeadingBidder(bid.getBidder());
                    item.setIsSold(bid.getBidAmount() >= item.getStartPrice());
               }
          }
     }

     public List<Bid> getBidsForItem(String itemId) throws SQLException {
          String sql = "SELECT * FROM bids WHERE item_id = ?";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, itemId);
               ResultSet resultSet = statement.executeQuery();
               List<Bid> bids = new ArrayList<>();
               while (resultSet.next()) {
                    Bid bid = fetchBidFromResultSet(resultSet);
                    bids.add(bid);
               }
               return bids;
          }
     }

     public Bid getHighestBid(String itemId) throws SQLException {
          List<Bid> bids = getBidsForItem(itemId);
          Bid highestBid = null;
          for (Bid bid : bids) {
               if (highestBid == null || bid.getBidAmount() > highestBid.getBidAmount()) {
                    highestBid = bid;
               }
          }
          return highestBid;
     }

     private Bid fetchBidFromResultSet(ResultSet resultSet) throws SQLException {
          UUID bidId = UUID.fromString(resultSet.getString("bid_id"));
          double bidAmount = resultSet.getDouble("bid_amount");
          Timestamp bidTimeStamp = resultSet.getTimestamp("bid_time");
          LocalDateTime bidTime = bidTimeStamp.toLocalDateTime();

          String bidderId = resultSet.getString("bidder_id");
          RegularUser bidder = fetchUserFromDatabase(bidderId);

          String itemId = resultSet.getString("item_id");
          Item item = read(itemId);

          return new Bid(bidAmount, bidder, item);
     }

     private RegularUser fetchUserFromDatabase(String userId) throws SQLException {
          UserDao userDao = new UserDao();
          User user = userDao.read(userId);
          if (user instanceof RegularUser) {
               return (RegularUser) user;
          }
          return null;
     }
}