package dao;

import daoservices.DatabaseConnection;
import models.Administrator;
import models.Bid;
import models.RegularUser;
import models.User;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao implements DaoInterface<User> {
    private Connection connection;
    private BidDao bidDao;

    public UserDao() throws SQLException {
        connection = DatabaseConnection.getConnection();
        bidDao = new BidDao();
    }

    public void add(User user) throws SQLException
    {

        String userId = UUID.randomUUID().toString();
        String userSql = "INSERT INTO users (user_id, email, username, password, user_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement userStatement = connection.prepareStatement(userSql)) {
            userStatement.setString(1, userId);
            userStatement.setString(2, user.getEmail());
            userStatement.setString(3, user.getUserName());
            userStatement.setString(4, user.getUserPassword());
            userStatement.setString(5, user instanceof Administrator ? "admin" : "regular");
            userStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e)
        {
            throw new IllegalArgumentException("Email or username already exists");
        }
        if (user instanceof RegularUser)
        {
            RegularUser regularUser = (RegularUser) user;
            String regularUserSql = "INSERT INTO regular_users (user_id, credit_card, balance) VALUES (?, ?, ?)";
            try (PreparedStatement regularUserStatement = connection.prepareStatement(regularUserSql))
            {
                regularUserStatement.setString(1, userId);
                regularUserStatement.setString(2, regularUser.getCreditCard());
                regularUserStatement.setDouble(3, regularUser.getBalance());
                regularUserStatement.executeUpdate();
            }
        }
    }

    public User readUserByName(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String userType = resultSet.getString("user_type");
                String userId = resultSet.getString("user_id");
                if (userType.equals("admin"))
                {
                    AuditService.logAction("Read admin user by id");
                    return readAdministratorById(userId);
                } else if (userType.equals("regular"))
                {
                    AuditService.logAction("Read regular user by id");
                    return readRegularUserById(userId);
                }
            }
        }
        AuditService.logAction("Read user by id but got null");
        return null;
    }

    private Administrator readAdministratorById(String userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Administrator admin = new Administrator();
                admin.setUserId(resultSet.getString("user_id"));
                admin.setEmail(resultSet.getString("email"));
                admin.setUserName(resultSet.getString("username"));
                admin.setUserPassword(resultSet.getString("password"));
                return admin;
            }
        }
        return null;
    }

    private RegularUser readRegularUserById(String userId) throws SQLException {
        String sql = "SELECT u.*, r.credit_card, r.balance " +
                "FROM users u " +
                "JOIN regular_users r ON u.user_id = r.user_id " +
                "WHERE u.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RegularUser regularUser = new RegularUser();
                regularUser.setUserId(resultSet.getString("user_id"));
                regularUser.setEmail(resultSet.getString("email"));
                regularUser.setUserName(resultSet.getString("username"));
                regularUser.setUserPassword(resultSet.getString("password"));
                regularUser.setCreditCard(resultSet.getString("credit_card"));
                regularUser.setBalance(resultSet.getInt("balance"));
                return regularUser;
            }
        }
        return null;
    }

    @Override
    public void update(User user) throws SQLException {
        String userSql = "UPDATE users SET email = ?, username = ?, password = ? WHERE user_id = ?";
        try (PreparedStatement userStatement = connection.prepareStatement(userSql))
        {
            userStatement.setString(1, user.getEmail());
            userStatement.setString(2, user.getUserName());
            userStatement.setString(3, user.getUserPassword());
            userStatement.setString(4, user.getUserId());
            userStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e)
        {
            AuditService.logAction("Tried to update user but username or email aready exists");
            throw new IllegalArgumentException("Email or username already exists");
        }

        if (user instanceof RegularUser) {
            RegularUser regularUser = (RegularUser) user;
            String regularUserSql = "UPDATE regular_users SET credit_card = ?, balance = ? WHERE user_id = ?";
            try (PreparedStatement regularUserStatement = connection.prepareStatement(regularUserSql))
            {
                regularUserStatement.setString(1, regularUser.getCreditCard());
                regularUserStatement.setDouble(2, regularUser.getBalance());
                regularUserStatement.setString(3, regularUser.getUserId());
                regularUserStatement.executeUpdate();
                AuditService.logAction("Updated user");
            }
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        String userSql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement userStatement = connection.prepareStatement(userSql))
        {
            userStatement.setString(1, user.getUserId());
            userStatement.executeUpdate();
        }

        if (user instanceof RegularUser) {
            String regularUserSql = "DELETE FROM regular_users WHERE user_id = ?";
            try (PreparedStatement regularUserStatement = connection.prepareStatement(regularUserSql))
            {
                regularUserStatement.setString(1, user.getUserId());
                regularUserStatement.executeUpdate();
            }
        }
        AuditService.logAction("Deleted user");
    }

    @Override
    public User read(String userId) throws SQLException
    {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                String userType = resultSet.getString("user_type");
                if (userType.equals("admin"))
                {
                    AuditService.logAction("Read admin user");
                    return readAdministratorById(userId);
                } else if (userType.equals("regular"))
                {
                    AuditService.logAction("Read regular user");
                    return readRegularUserById(userId);
                }
            }
        }
        AuditService.logAction("Tried to read user but got null");
        return null;
    }

    public List<User> getAllUsers() throws SQLException
    {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                String userId = resultSet.getString("user_id");
                String userType = resultSet.getString("user_type");
                if (userType.equals("admin"))
                {
                    users.add(readAdministratorById(userId));
                } else if (userType.equals("regular"))
                {
                    users.add(readRegularUserById(userId));
                }
            }
        }
        AuditService.logAction("Fetched all users");
        return users;
    }

    public List<Bid> getBidsByUser(String userId) throws SQLException
    {
        String sql = "SELECT * FROM bids WHERE bidder_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Bid> bids = new ArrayList<>();
            while (resultSet.next())
            {
                bids.add(bidDao.fetchBidFromResultSet(resultSet));
            }
            AuditService.logAction("Fetched bids by user Id");
            return bids;
        }
    }
}