package dao;

import dao.DaoInterface;
import daoservices.DatabaseConnection;
import models.Bid;
import models.Item;
import models.RegularUser;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import daoservices.UserRepositoryService;
import models.RegularUser;
import models.User;

public class BidDao implements DaoInterface<Bid> {
    private Connection connection;
    private UserDao userDao;

    public BidDao() throws SQLException
    {
        this.connection = DatabaseConnection.getConnection();
        this.userDao = userDao;
    }

    @Override
    public void add(Bid bid) throws SQLException {
        String sql = "INSERT INTO bids (bid_id, bid_amount, bid_time, bidder_id, item_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bid.getBidId().toString());
            statement.setDouble(2, bid.getBidAmount());
            statement.setTimestamp(3, Timestamp.valueOf(bid.getBidTime()));
            statement.setString(4, bid.getBidder().getUserId());
            statement.setString(5, bid.getItem().getItemId().toString());
            statement.executeUpdate();
        }
    }

    @Override
    public Bid read(String bidId) throws SQLException {
        String sql = "SELECT * FROM bids WHERE bid_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bidId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createBidFromResultSet(resultSet);
            }
        }
        return null;
    }

    private Bid createBidFromResultSet(ResultSet resultSet) throws SQLException {
        UUID bidId = UUID.fromString(resultSet.getString("bid_id"));
        double bidAmount = resultSet.getDouble("bid_amount");
        LocalDateTime bidTime = resultSet.getTimestamp("bid_time").toLocalDateTime();
        RegularUser bidder = fetchUserFromDatabase(resultSet.getString("bidder_id"));
        Item item = fetchItemFromDatabase(UUID.fromString(resultSet.getString("item_id")));
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

    private Item fetchItemFromDatabase(UUID itemId) throws SQLException {
        ItemDao itemDao = new ItemDao();
        return itemDao.read(itemId.toString());
    }
    @Override
    public void delete(Bid bid) throws SQLException {
        String sql = "DELETE FROM bids WHERE bid_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bid.getBidId().toString());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Bid bid) throws SQLException {
        // Not necessary for the Bid entity
    }

    public List<Bid> getBidsByItem(String itemId) throws SQLException {
        String sql = "SELECT * FROM bids WHERE item_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            List<Bid> bids = new ArrayList<>();
            while (resultSet.next()) {
                bids.add(createBidFromResultSet(resultSet));
            }
            return bids;
        }
    }

    public Bid fetchBidFromResultSet(ResultSet resultSet) throws SQLException {
        UUID bidId = UUID.fromString(resultSet.getString("bid_id"));
        double bidAmount = resultSet.getDouble("bid_amount");
        LocalDateTime bidTime = resultSet.getTimestamp("bid_time").toLocalDateTime();
        RegularUser bidder = fetchUserFromDatabase(resultSet.getString("bidder_id"));
        UUID itemId = UUID.fromString(resultSet.getString("item_id"));
        Item item = fetchItemFromDatabase(itemId);
        return new Bid(bidAmount, bidder, item);
    }
}