package daoservices;

import dao.UserDao;
import models.Administrator;
import models.Bid;
import models.RegularUser;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryService {
    private UserDao userDao;

    public UserRepositoryService() {
        try {
            userDao = new UserDao();
        } catch (SQLException e) {

            throw new RuntimeException("Failed to create UserDao", e);
        }
    }


    public void createUser(User user) throws SQLException {
        if (user == null ) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (userDao.read(user.getUserId()) != null) {
            throw new IllegalArgumentException("User with this ID already exists"); // probabil redundant ca am schimbat sa am UUID
        }
        userDao.add(user);
    }

    public User readUserById(String userId) throws SQLException {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userDao.read(userId);
    }

    public void updateUser(User user) throws SQLException {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("User or ID cannot be null");
        }
        userDao.update(user);
    }

    public void deleteUser(User user) throws SQLException {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("User or ID cannot be null");
        }
        userDao.delete(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public User readUserByName(String username) throws SQLException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return userDao.readUserByName(username);
    }

    public List<Bid> getBidsByUser(String userId) throws SQLException {
        return userDao.getBidsByUser(userId);
    }
}